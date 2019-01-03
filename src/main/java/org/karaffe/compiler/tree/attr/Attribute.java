package org.karaffe.compiler.tree.attr;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class Attribute {
    private AttributeType attributeType;
    private Map<String, Object> data = new LinkedHashMap<>();

    public Attribute(AttributeType attributeType) {
        this.attributeType = Objects.requireNonNull(attributeType);
    }

    public void put(String key, Object data) {
        this.data.put(Objects.requireNonNull(key), Objects.requireNonNull(data));
    }

    @SuppressWarnings("unchecked")
    public <T> T get(String key) {
        return (T) this.data.get(key);
    }

    @Override
    public String toString() {
        return String.format("%s=%s", this.attributeType.name(), data);
    }
}
