package edu.estu;

import org.junit.Assert;
import org.junit.Test;

import java.nio.file.Path;

public class KP800Test {


    @Test
    public void testGreedy800() {
        Path path = Path.of("problemInstances/n_800_c_1000000_g_6_f_0.3_eps_1e-05_s_300/test.in");

        KP01 kp01 = KP01.fromFile(path);

        Assert.assertEquals("numItems", 800, kp01.numItems());
        Assert.assertEquals("capacity", 1000000L, kp01.capacity());
        Assert.assertEquals("experiment tag", "n_800_c_1000000_g_6_f_0.3_eps_1e-05_s_300", kp01.tag());
        Assert.assertEquals("solution type", "KP01", kp01.name());

        IKS greedy = kp01.greedy();


        Assert.assertEquals("experiment tag", "n_800_c_1000000_g_6_f_0.3_eps_1e-05_s_300", greedy.tag());
        Assert.assertEquals("solution type", "Greedy", greedy.name());

        Assert.assertEquals("totalValue", 1008989L, greedy.totalValue());
        Assert.assertEquals("totalWeight", 999994L, greedy.totalWeight());

        Assert.assertEquals("numItems", 235, greedy.numItems());
        Assert.assertEquals("capacity", 1000000L, greedy.capacity());
        Assert.assertEquals("capacity", kp01.capacity(), greedy.capacity());

    }

    @Test
    public void testRandom800() {
        Path path = Path.of("problemInstances/n_800_c_1000000_g_6_f_0.3_eps_1e-05_s_300/test.in");

        KP01 kp01 = KP01.fromFile(path);

        Assert.assertEquals("numItems", 800, kp01.numItems());
        Assert.assertEquals("capacity", 1000000L, kp01.capacity());
        Assert.assertEquals("experiment tag", "n_800_c_1000000_g_6_f_0.3_eps_1e-05_s_300", kp01.tag());
        Assert.assertEquals("solution type", "KP01", kp01.name());

        IKS random = kp01.random();


        Assert.assertEquals("experiment tag", "n_800_c_1000000_g_6_f_0.3_eps_1e-05_s_300", random.tag());
        Assert.assertEquals("solution type", "Random", random.name());

        Assert.assertFalse("totalValue", random.totalValue() > 1009765L);
        Assert.assertFalse("totalWeight", random.totalWeight() > kp01.totalWeight());

        Assert.assertFalse("numItems", random.numItems() > kp01.numItems());
        Assert.assertEquals("capacity", 1000000L, random.capacity());
        Assert.assertEquals("capacity", kp01.capacity(), random.capacity());

    }
}
