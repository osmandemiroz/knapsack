package edu.estu;

import org.junit.Assert;
import org.junit.Test;

import java.nio.file.Path;

public class KP1000Test {


    @Test
    public void testRandom1000() {
        Path path = Path.of("problemInstances/n_1000_c_100000000_g_14_f_0.1_eps_0_s_200/test.in");

        KP01 kp01 = KP01.fromFile(path);

        Assert.assertEquals("numItems", 1000, kp01.numItems());
        Assert.assertEquals("capacity", 100000000L, kp01.capacity());
        Assert.assertEquals("experiment tag", "n_1000_c_100000000_g_14_f_0.1_eps_0_s_200", kp01.tag());
        Assert.assertEquals("solution type", "KP01", kp01.name());

        IKS random = kp01.random();

        Assert.assertEquals("experiment tag", "n_1000_c_100000000_g_14_f_0.1_eps_0_s_200", random.tag());
        Assert.assertEquals("solution type", "Random", random.name());

        Assert.assertFalse("totalValue", random.totalValue() > 100023535L);
        Assert.assertFalse("totalWeight", random.totalWeight() > kp01.totalWeight());

        Assert.assertFalse("numItems", random.numItems() > kp01.numItems());
        Assert.assertEquals("capacity", 100000000L, random.capacity());
        Assert.assertEquals("capacity", kp01.capacity(), random.capacity());

    }

    @Test
    public void testGreedy1000() {
        Path path = Path.of("problemInstances/n_1000_c_100000000_g_14_f_0.1_eps_0_s_200/test.in");

        KP01 kp01 = KP01.fromFile(path);

        Assert.assertEquals("numItems", 1000, kp01.numItems());
        Assert.assertEquals("capacity", 100000000L, kp01.capacity());
        Assert.assertEquals("experiment tag", "n_1000_c_100000000_g_14_f_0.1_eps_0_s_200", kp01.tag());
        Assert.assertEquals("solution type", "KP01", kp01.name());

        IKS greedy = kp01.greedy();


        Assert.assertEquals("experiment tag", "n_1000_c_100000000_g_14_f_0.1_eps_0_s_200", greedy.tag());
        Assert.assertEquals("solution type", "Greedy", greedy.name());

        Assert.assertFalse("totalValue", greedy.totalValue() > 100023535L);
        Assert.assertEquals("totalValue", 100022139L, greedy.totalValue());
        Assert.assertEquals("totalWeight", 99999988L, greedy.totalWeight());

        Assert.assertEquals("numItems", 342, greedy.numItems());
        Assert.assertEquals("capacity", 100000000L, greedy.capacity());
        Assert.assertEquals("capacity", kp01.capacity(), greedy.capacity());

    }
}
