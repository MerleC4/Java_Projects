import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

public class testCases {
	@Test
    public void testGet() {
        MyHashMap<String, Integer> test = new MyHashMap<String, Integer>();
        System.out.println(test.isEmpty());
        test.put("a", 11);
        test.put("b", -1);
        test.put("aa", -2);
        test.put("bb", 1231);
        test.put("cc", 421);
        test.put("dd", 123);
        test.put("ee", 10);
        test.put("Cusd", 12312);
        test.put("AOHFD", 1);
        test.put("OIUOI", 0);
        test.put("asdfsdaf", 12312);
        test.put("iuowr", 1);
        test.put("1231", 0);
        test.put("r2424", 121321);
        test.put("dfwerwd", 15441);
        test.put("8htj", 5133);
        test.printTable();
        System.out.println(test.get("dd"));
        System.out.println(test.get("adfsafsad"));
        System.out.println(test.size());
        System.out.println(test.remove("Cusd"));
        test.remove("AOHFD");
        test.remove("OIUOI");
        test.remove("cc");
        System.out.println(test.remove("aa"));
        System.out.println(test.keySet());
        System.out.println(test.put("a", 12));
        test.printTable();
        System.out.println(test.containsValue(11));
        System.out.println(test.containsValue(0));
        System.out.println(test.size());
        System.out.println(test.isEmpty());
        test.clear();
        System.out.println(test.isEmpty());
        System.out.println(test.size());
        System.out.println(test.remove("cum"));
        System.out.println(test.keySet());
        test.printTable();
    }
	/*
	@Test
    public void testGet1() {
        MyHashMap<String, Integer> test = new MyHashMap<String, Integer>();
        System.out.println(test.isEmpty());
        test.put("a", 11);
        test.put("b", -1);
        test.put("aa", -2);
        test.put("bb", 1231);
        test.put("cc", 421);
        test.put("dd", 123);
        test.put("ee", 10);
        test.put("Cusd", 12312);
        test.put("AOHFD", 1);
        test.put("OIUOI", 0);
        test.printTable();
        System.out.println(test.size());
        System.out.println(test.remove("Cusd"));
        test.remove("AOHFD");
        test.remove("OIUOI");
        test.remove("cc");
        test.remove("aa");
        System.out.println(test.keySet());
        test.put("a", 12);
        test.printTable();
        System.out.println(test.containsValue(11));
        System.out.println(test.containsValue(0));
        System.out.println(test.size());
        System.out.println(test.isEmpty());
        test.clear();
        System.out.println(test.isEmpty());


    }
    
    @Test
    public void testGet2() {
        MyHashMap<Integer, String> test = new MyHashMap<Integer, String>();
        test.put(1, "a");
        Assert.assertEquals(test.get(1), "a");
        test.put(-2, "b");
        Assert.assertEquals(test.get(-2), "b");
        test.printTable();
    }

    @Test
    public void testContainsKey() {
        MyHashMap<String, Integer> test = new MyHashMap<String, Integer>();
        test.put("a", 1);
        Assert.assertTrue(test.containsKey("a"));
        Assert.assertFalse(test.containsKey("b"));
        test.printTable();
    }

    @Test
    public void testContainsKey2() {
        MyHashMap<String, Integer> test = new MyHashMap<String, Integer>();
        Assert.assertFalse(test.containsKey("a"));
        test.printTable();
    }

    @Test
    public void testPut() {
        MyHashMap<String, Integer> test = new MyHashMap<String, Integer>();
        test.put("a", 1);
        Assert.assertEquals((int) test.get("a"), 1);
        test.put("b", 2);
        Assert.assertEquals((int) test.get("b"), 2);
        test.printTable();
    }

    @Test
    public void testKeySet() {
        MyHashMap<String, Integer> test = new MyHashMap<String, Integer>();
        test.put("a", 1);
        Assert.assertEquals(test.keySet().size(), 1);
        test.put("b", 2);
        Assert.assertEquals(test.keySet().size(), 2);
        test.printTable();
    }

    @Test
    public void testKeySet2() {
        MyHashMap<String, Integer> test = new MyHashMap<String, Integer>();
        Assert.assertEquals(test.keySet().size(), 0);
        test.printTable();
    }
    */
}


