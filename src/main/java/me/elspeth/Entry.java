package me.elspeth;

import java.util.Map;

public class Entry implements Map.Entry<String, String> {

    private final String key;
    private String value;

    public Entry(String key) {
        this.key = key;
    };

    public Entry(String key, String value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public String setValue(String value) {
        String prev = this.value;
        this.value = value;
        return prev;
    }

    @Override
    public String toString() {
        return "Translation{" +
                "key='" + key + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
