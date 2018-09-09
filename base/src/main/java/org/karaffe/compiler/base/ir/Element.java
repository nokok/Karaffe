package org.karaffe.compiler.base.ir;

import org.karaffe.compiler.base.attr.Attributes;

import java.util.List;

public interface Element extends Attributes, IR {
    String getElementID();

    void setElementID(String elementID);

    ElementType getElementType();

    void add(Element element);

    void addFirst(Element element);

    List<Element> getElements();

}
