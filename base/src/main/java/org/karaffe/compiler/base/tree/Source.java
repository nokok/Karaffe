package org.karaffe.compiler.base.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Source extends AbstractTree implements TopLevelTree, NamedElement<String>, BodyList<Defs> {

    private String name;
    private List<Defs> body;

    public Source(String name) {
        this.name = Objects.requireNonNull(name);
        this.body = new ArrayList<>();
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

    @Override
    public void add(Defs body) {
        this.body.add(Objects.requireNonNull(body));
    }

    @Override
    public List<Defs> getBody() {
        return this.body;
    }
}
