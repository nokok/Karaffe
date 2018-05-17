package org.karaffe.compiler.base.tree;

import java.util.List;

public interface BodyList<T> {

    public void add(T body);

    public List<T> getBody();
}
