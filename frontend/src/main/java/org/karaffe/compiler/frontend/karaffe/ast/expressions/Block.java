package org.karaffe.compiler.frontend.karaffe.ast.expressions;

import org.karaffe.compiler.base.pos.Position;
import org.karaffe.compiler.frontend.karaffe.ast.api.ASTVisitor;
import org.karaffe.compiler.frontend.karaffe.ast.api.AbstractExpression;
import org.karaffe.compiler.frontend.karaffe.ast.api.Expression;
import org.karaffe.compiler.frontend.karaffe.ast.api.Statement;
import org.karaffe.compiler.frontend.karaffe.ast.api.StatementType;
import org.karaffe.compiler.frontend.karaffe.ast.api.Tree;
import org.karaffe.compiler.frontend.karaffe.ast.statements.LetLocalDef;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class Block extends AbstractExpression {

    private List<Statement> body;

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

    public void replaceBody(List<Statement> body) {
        this.body = body;
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
        return this.body.stream().anyMatch(Tree::isNormalizable);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }

    public boolean hasNestedBlock() {
        return this
                .getBody()
                .stream()
                .filter(t -> t.getStatementType().equals(StatementType.EXPRESSION))
                .map(Expression.class::cast)
                .anyMatch(e -> e.getExpressionType().equals(ExpressionType.BLOCK));
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
        Statement lastStatement = this.getBody().get(this.getBody().size() - 1);
        switch (lastStatement.getStatementType()) {
        case LOCAL_LET_DEF:
            LetLocalDef def = (LetLocalDef) lastStatement;
            return Optional.of(new ExpressionName(def.getName().toString()));
        case EXPRESSION: {
            Expression expr = (Expression) lastStatement;
            switch (expr.getExpressionType()) {
            case RETURN: {
                Return ret = (Return) expr;
                if (ret.getExpr().getExpressionType().equals(ExpressionType.NAME)) {
                    return Optional.ofNullable((ExpressionName) ret.getExpr());
                }
                return Optional.empty();
            }
            default:
                return Optional.empty();
            }
        }
        default:
            return Optional.empty();
        }
    }

    @Override
    public String toString() {
        return String.format("{\n%s}", this.getBody().stream().map(Object::toString).map(s -> s + ";\n").reduce((l, r) -> l + r).orElse(""));
    }

}
