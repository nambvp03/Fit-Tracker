package com.codingminds.fittracker;

import java.util.HashMap;
import java.util.Map;

public class FoodDB {
    public static Map<String, Integer> DB = new HashMap<String, Integer>() {{
        put("apples", 100);
        put("pretzels", 120);
        put("bananas", 130);
        put("bread", 80);
    }};
}
