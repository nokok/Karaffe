package org.karaffe.compiler.base.attr;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public interface Attributes {

    void addAttribute(Attribute attribute);

    void setAttributes(List<Attribute> attributes);

    List<Attribute> getAttributes();

    default List<Attribute> getAttributes(Predicate<Attribute> p) {
        return this.getAttributes().stream().filter(p).collect(Collectors.toList());
    }

    default <T extends Attribute> Optional<T> getAttribute(Class<T> clazz) {
        return this.getAttributes().stream().filter(clazz::isInstance).map(clazz::cast).findFirst();
    }

    default boolean hasAttribute(Class<?> clazz) {
        return this.getAttributes().stream().anyMatch(clazz::isInstance);
    }

    default boolean hasAttribute() {
        return !getAttributes().isEmpty();
    }
}
