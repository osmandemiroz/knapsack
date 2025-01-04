package edu.estu;

import org.junit.Assert;

import java.nio.file.Path;

/**
 * Unit test for simple App.
 */
public class AppTest {


    public static void testAgainstTheOptimal(String run, long optimal) {
        Path path = Path.of("problemInstances/" + run + "/test.in");

        KP01 kp01 = KP01.fromFile(path);


        Assert.assertEquals("experiment tag", run, kp01.tag());
        Assert.assertEquals("solution type", "KP01", kp01.name());

        IKS greedy = kp01.greedy();

        Assert.assertEquals("experiment tag", run, greedy.tag());
        Assert.assertEquals("solution type", "Greedy", greedy.name());

        Assert.assertFalse("totalValue", greedy.totalValue() > optimal);
        Assert.assertFalse("totalWeight", greedy.totalWeight() > kp01.totalWeight());

        Assert.assertEquals("capacity", kp01.capacity(), greedy.capacity());

        for (int i = 0; i < 1000; i++) {

            IKS random = kp01.random();

            Assert.assertEquals("experiment tag", run, random.tag());
            Assert.assertEquals("solution type", "Random", random.name());

            Assert.assertFalse("totalValue", random.totalValue() > optimal);
            Assert.assertFalse("totalWeight", random.totalWeight() > kp01.totalWeight());

            Assert.assertFalse("numItems", random.numItems() > kp01.numItems());
            Assert.assertEquals("capacity", kp01.capacity(), random.capacity());
        }
    }
}
