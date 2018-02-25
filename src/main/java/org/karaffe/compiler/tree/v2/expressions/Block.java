package org.karaffe.compiler.tree.v2.expressions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.karaffe.compiler.pos.Position;
import org.karaffe.compiler.tree.v2.api.AbstractTree;
import org.karaffe.compiler.tree.v2.api.Expression;
import org.karaffe.compiler.tree.v2.api.Statement;
import org.karaffe.compiler.tree.v2.api.StatementType;
import org.karaffe.compiler.tree.v2.api.Tree;
import org.karaffe.compiler.tree.v2.statements.LetLocalDef;
import org.karaffe.compiler.types.v2.TypeConstraint;
import org.karaffe.compiler.types.v2.TypeConstraints;

public class Block extends AbstractTree implements Expression {

    private final List<Statement> body;

    public Block() {
        this(Position.noPos());
    }

    public Block(Position position) {
        super(position);
        this.body = new ArrayList<>();
    }

    public Block(Statement... body) {
        this(Position.noPos(), Arrays.asList(body));
    }

    public Block(List<? extends Statement> body) {
        this(Position.noPos(), body);
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
    public ExpressionType getExpressionType() {
        return ExpressionType.BLOCK;
    }

    @Override
    public boolean isNormalizable() {
        return this.body.stream().filter(Tree::isNormalizable).findAny().isPresent();
    }

    public boolean hasNestedBlock() {
        return this
                .getBody()
                .stream()
                .filter(t -> t.getStatementType().equals(StatementType.EXPRESSION))
                .map(Expression.class::cast)
                .filter(e -> e.getExpressionType().equals(ExpressionType.BLOCK)).findAny().isPresent();
    }

    public Block flatten() {
        if (!this.hasNestedBlock()) {
            return this;
        }
        List<Statement> newBody = new ArrayList<>();
        for (Statement stmt : this.body) {
            if (stmt.getStatementType().equals(StatementType.EXPRESSION) &&
                    ((Expression) stmt).getExpressionType().equals(ExpressionType.BLOCK)) {
                Block innerStmt = (Block) stmt;
                newBody.addAll(innerStmt.flatten().body);
                continue;
            }
            newBody.add(stmt);
        }
        return new Block(this.getPosition(), newBody);
    }

    @Override
    public Optional<ExpressionName> asExprName() {
        if (this.body.isEmpty()) {
            return Optional.empty();
        }
        Statement statement = this.getBody().get(this.getBody().size() - 1);
        switch (statement.getStatementType()) {
        case LOCAL_LET_DEF:
            LetLocalDef def = (LetLocalDef) statement;
            return Optional.of(new ExpressionName(def.getName().toString()));
        default:
            return Optional.empty();
        }
    }

    @Override
    public String toString() {
        return String.format("{\n%s}", this.getBody().stream().map(Object::toString).map(s -> s + ";\n").reduce((l, r) -> l + r).orElse(""));
    }

    @Override
    public TypeConstraint getTypeConstraint() {
        return TypeConstraints.infer(this.getBody().get(this.getBody().size() - 1));
    }
}
