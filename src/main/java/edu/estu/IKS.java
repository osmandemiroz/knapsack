package edu.estu;

import java.util.Collection;
import java.util.Set;

/**
 * Interface for Knapsack Solution
 */
public interface IKS {

    Collection<Item> items();

    default long totalValue() {
        return items().stream().mapToLong(Item::profit).sum();

    }

    default long totalWeight() {
        return items().stream().mapToLong(Item::weight).sum();
    }

    default int numItems() {
        return items().size();
    }


    long capacity();

    String name();

    String tag();


    default String ToString() {
        return name() + "[" +
                "numItems=" + numItems() +
                ", totalWeight=" + totalWeight() +
                ", totalValue=" + totalValue() +
                //   ", occupancyRate=" + String.format("%.1f", 100D * totalWeight() / capacity()) + "%" +
                ']';
    }
}
