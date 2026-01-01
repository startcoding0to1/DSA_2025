package tester;

import java.util.Map;
import java.util.TreeMap;

public class Tester {
    public static void main(String[] args) {
        Map<Integer, Integer> map = new TreeMap<>((a,b)->b.compareTo(a));
        map.put(2, 1);
        map.put(1, 2);
        map.put(5, 3);
        map.put(4, 4);
        map.put(3, 5);
        map.putIfAbsent(6, 6);
        System.out.println(map);
    }
}
