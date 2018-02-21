package org.karaffe.compiler.tree.v2.expressions;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.karaffe.compiler.pos.Position;
import org.karaffe.compiler.tree.v2.api.AbstractTree;
import org.karaffe.compiler.tree.v2.api.Expression;
import org.karaffe.compiler.tree.v2.api.Statement;
import org.karaffe.compiler.tree.v2.api.TreeVisitor;

public class Block extends AbstractTree implements Expression {

    private final List<Statement> body;

    public Block() {
        this(Position.noPos());
    }

    public Block(Position position) {
        super(position);
        this.body = new ArrayList<>();
    }

    public Block(Position position, List<? extends Statement> body) {
        super(position);
        this.body = new ArrayList<>(body);
    }

    public void add(Statement tree) {
        this.body.add(Objects.requireNonNull(tree));
    }

    public List<? extends Statement> getBody() {
        return new ArrayList<>(this.body);
    }

    @Override
    public void accept(TreeVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public ExpressionType getExpressionType() {
        return ExpressionType.BLOCK;
    }

}
