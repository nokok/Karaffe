package org.karaffe.compiler.base.ir;

import org.karaffe.compiler.base.attr.Attribute;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AbstractElement implements Element {

    private String elementID;
    private List<Attribute> attributes;

    public AbstractElement() {
        this.elementID = "";
        this.attributes = new ArrayList<>();
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
    public void setAttributes(List<Attribute> attributes) {
        this.attributes = Objects.requireNonNull(attributes);
    }

    @Override
    public List<Attribute> getAttributes() {
        return this.attributes;
    }
}
