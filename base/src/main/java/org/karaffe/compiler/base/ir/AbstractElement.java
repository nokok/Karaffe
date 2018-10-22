package org.karaffe.compiler.base.ir;

import org.karaffe.compiler.base.attr.Attribute;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class AbstractElement implements Element {

    private String elementID;
    private List<Attribute> attributes;
    private List<Element> elements;

    public AbstractElement() {
        this.elementID = "";
        this.attributes = new ArrayList<>();
        this.elements = new ArrayList<>();
    }

    @Override
    public void add(Element element) {
        this.elements.add(Objects.requireNonNull(element));
    }

    @Override
    public void addFirst(Element element) {
        this.elements.add(0, Objects.requireNonNull(element));
    }

    @Override
    public List<Element> getElements() {
        return this.elements;
    }

    @Override
    public String getElementID() {
        return this.elementID;
    }

    @Override
    public void setElementID(String elementID) {
        this.elementID = Objects.requireNonNull(elementID);
    }

    @Override
    public void addAttribute(Attribute attribute) {
        this.attributes.add(Objects.requireNonNull(attribute));
    }

    @Override
    public List<Attribute> getAttributes() {
        return this.attributes;
    }

    @Override
    public void setAttributes(List<Attribute> attributes) {
        this.attributes = Objects.requireNonNull(attributes);
    }
}
