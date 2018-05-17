package org.karaffe.compiler.base.tree;

import java.util.ArrayList;
import java.util.List;

public class MethodDef extends AbstractTree implements BodyList<Defs>, Defs {

    private String name;
    private List<Defs> body;

    public MethodDef(String name) {
        this.name = name;
        this.body = new ArrayList<>();
    }

    @Override
    public void add(Defs body) {
        this.body.add(body);
    }

    @Override
    public List<Defs> getBody() {
        return this.body;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public <T> T accept(TreeVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
