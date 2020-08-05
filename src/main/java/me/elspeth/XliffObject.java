package me.elspeth;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class XliffObject {

    private String source;
    private String product;
    private final List<String> targets = new LinkedList<>();
    private final HashMap<String, HashMap<String, String>> translationHashMap = new HashMap<>();

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        source = source.replaceAll("[\\W]","");
        this.source = source;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public void addTarget(String target) {
        if(!source.equals(target) && !targets.contains(target)) {
            targets.add(target);
        }
    }

    public void addTranslation(String key, String lang, String value) {
        translationHashMap.putIfAbsent(key, new HashMap<>());
        translationHashMap.get(key).put(lang, value);
        addTarget(lang);
    }

    public void addTranslation(String key, Entry translation) {
        addTranslation(key, translation.getKey(), translation.getValue());
    }

    public List<String> getTargets() {
        return targets;
    }

    public HashMap<String, HashMap<String, String>> getTranslationHashMap() {
        return translationHashMap;
    }

    @Override
    public String toString() {
        return "XliffObject{" +
                "source='" + source + '\'' +
                ", product='" + product + '\'' +
                ", targets=" + targets +
                ", translationHashMap=" + translationHashMap +
                '}';
    }
}
