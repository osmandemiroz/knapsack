package edu.estu;

import java.io.IOException;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;


public class KP01 implements IKS {

    private final long capacity;
    private final HashSet<Item> items = new HashSet<>();

    @Override
    public Set<Item> items() {
        return items;
    }

    @Override
    public long capacity() {
        return capacity;
    }
    private String tag;

    @Override
    public String tag() {
        return tag;
    }

    @Override
    public String name() {
        return this.getClass().getSimpleName();
    }

    public KP01(long capacity, Collection<Item> items) {
        this.capacity = capacity;
        this.items.addAll(items);
    }

    public KP01(long capacity) {
        this.capacity = capacity;
    }

    public static KP01 fromFile(Path path) {

        try (Scanner scanner = new Scanner(path)) {

            int numberOfItems = scanner.nextInt();
            HashSet<Item> items = new HashSet<>(numberOfItems);

            for (int i = 0; i < numberOfItems; i++) {
                items.add(new Item(scanner.nextInt(), scanner.nextLong(), scanner.nextLong()));
            }

            long capacity = scanner.nextLong();
            KP01 kp01 = new KP01(capacity, items);
            kp01.tag = path.getParent().getFileName().toString();
            items.clear();
            return kp01;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public String toString() {
        return "KP01{" +
                "capacity=" + capacity +
                ", numItems=" + items.size() +
                '}';
    }

    boolean trivial() {
        return items.stream().mapToLong(Item::weight).sum() <= capacity;
    }
    
     IKS random() {
        Set<Item> selectedItems = new HashSet<>();
        long currentWeight = 0;
        
        // Create a list of items to shuffle
        List<Item> itemList = new ArrayList<>(items);
        Collections.shuffle(itemList);
        
        // Add items randomly while respecting capacity constraint
        for (Item item : itemList) {
            if (currentWeight + item.weight() <= capacity && Math.random() < 0.5) {
                selectedItems.add(item);
                currentWeight += item.weight();
            }
        }
        
        return new IKS() {
            @Override
            public Collection<Item> items() {
                return selectedItems;
            }

            @Override
            public long capacity() {
                return capacity;
            }

            @Override
            public String name() {
                return "Random";
            }

            @Override
            public String tag() {
                return tag;
            }

            @Override
            public String toString() {
                return String.format("%s{value=%d, weight=%d}", 
                    name(),
                    totalValue(),
                    totalWeight()
                );
            }
        };
    }

    IKS greedy() {
        Set<Item> selectedItems = new HashSet<>();
        List<Item> sortedItems = items.stream()
                .sorted(Comparator.comparingDouble(Item::unitValue).reversed())
                .toList();
                
        long remainingCapacity = capacity;
        
        for (Item item : sortedItems) {
            if (item.weight() <= remainingCapacity) {
                selectedItems.add(item);
                remainingCapacity -= item.weight();
            }
        }

        return new IKS() {
            @Override
            public Collection<Item> items() {
                return selectedItems;
            }

            @Override
            public long capacity() {
                return capacity;
            }

            @Override
            public String name() {
                return "Greedy";
            }

            @Override
            public String tag() {
                return tag;
            }

            @Override
            public long totalValue() {
                return items().stream()
                        .mapToLong(Item::profit)
                        .sum();
            }

            @Override
            public String toString() {
                return String.format("%s{value=%d, weight=%d}", 
                    name(),
                    totalValue(),
                    items().stream().mapToLong(Item::weight).sum()
                );
            }
        };
    }
}
