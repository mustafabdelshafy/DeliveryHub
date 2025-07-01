package org.swaglabs.utils;

import java.util.HashMap;
import java.util.Map;

public class RuntimeData {
    private static final Map<String, String> data = new HashMap<>();

    public static void set(String key, String value) {
        data.put(key, value);
    }

    public static String get(String key) {
        return data.get(key);
    }

    public static boolean contains(String key) {
        return data.containsKey(key);
    }

    public static void clear() {
        data.clear();
    }
}
