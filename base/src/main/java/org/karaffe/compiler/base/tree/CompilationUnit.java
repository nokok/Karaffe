package org.karaffe.compiler.base.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CompilationUnit extends AbstractTree implements BodyList<TopLevelTree> {

    private List<TopLevelTree> body = new ArrayList<>();

    @Override
    public void add(TopLevelTree t) {
        this.body.add(Objects.requireNonNull(t));
    }

    @Override
    public List<TopLevelTree> getBody() {
        return this.body;
    }

    @Override
    public <T> T accept(TreeVisitor<T> visitor) {
        return visitor.visit(this);
    }

}
