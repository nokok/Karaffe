package org.karaffe.compiler.base.ir;

import org.karaffe.compiler.base.attr.Attributes;

public interface Element extends Attributes {
    String getElementID();

    void setElementID(String elementID);

    @Override String toString();
}
