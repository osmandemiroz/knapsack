package edu.estu;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.IntStream;

/**
 * Hello world!
 */
public class App {

    static private final int ITER_NUM = 10_000;

    private static void processSingleFile(Path path) {

        final String tag = path.getParent().getFileName().toString();

        KP01 kp01 = KP01.fromFile(path);

        IKS greedy = kp01.greedy();

        final long greedyScore = greedy.totalValue();

        AtomicReference<IKS> random = new AtomicReference<>(kp01.random());
        AtomicInteger accumulator = new AtomicInteger(0);

        if (random.get().totalValue() > greedyScore) {
            accumulator.incrementAndGet();
        }
        IntStream.range(0, ITER_NUM).boxed().parallel().forEach(index -> {

            IKS aRandomSolution = kp01.random();
            final long randomScore = aRandomSolution.totalValue();
            random.updateAndGet(prev -> randomScore > prev.totalValue() ? aRandomSolution : prev);
            accumulator.accumulateAndGet(randomScore > greedyScore ? 1 : 0, Math::addExact);
        });

        System.out.printf("%s\t%s\t%s\t%d/%d\n", tag, greedy.ToString(), random.getPlain().ToString(), accumulator.get(), ITER_NUM);


    }

    public static void main(String[] args) {

        if (args.length == 0) {
            System.out.println("Usage: java -jar target/KP01.jar problemInstances/*/test.in");
            System.exit(1);
        }


        for (String testFilePath : args) {

            final Path path = Paths.get(testFilePath);

            if (Files.exists(path) && Files.isRegularFile(path)) {
                processSingleFile(path);
            } else
                System.err.println("cannot read/find file: " + testFilePath);

        }
    }
}
