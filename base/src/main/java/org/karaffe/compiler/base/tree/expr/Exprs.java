package org.karaffe.compiler.base.tree.expr;

import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.karaffe.compiler.base.pos.Position;
import org.karaffe.compiler.base.tree.Tree;
import org.karaffe.compiler.base.tree.term.Terms;

import java.util.List;
import java.util.Objects;

public interface Exprs {

    static Tree intValue(TerminalNode node) {
        Tree tree = intValue(node.getText());
        tree.setPos(Position.of(node.getSymbol()));
        return tree;
    }

    static Tree intValue(String value) {
        IntegerLiteral integerLiteral = new IntegerLiteral(value);
        return integerLiteral;
    }

    static Tree apply(Position position, Tree target, Tree methodName, Tree arg) {
        Apply apply = new Apply();
        apply.setPos(Objects.requireNonNull(position));
        apply.setName(Objects.requireNonNull(methodName));
        apply.addChild(Objects.requireNonNull(target));
        apply.addChild(Objects.requireNonNull(arg));
        return apply;
    }

    static Tree tuple(List<Tree> trees) {
        Tuple tuple = new Tuple();
        for (Tree tree : trees) {
            tuple.addChild(Objects.requireNonNull(tree));
        }
        return tuple;
    }

    static Tree stringValue(Position position, String value) {
        StringLiteral stringLiteral = new StringLiteral(value);
        stringLiteral.setPos(position);
        return stringLiteral;
    }

    static Tree stringValue(TerminalNode stringLiteral) {
        return stringValue(Position.of(stringLiteral.getSymbol()), stringLiteral.getText());
    }

    static Tree ifExpr(Position position, Tree condition, Tree thenBlock, Tree elseBlock) {
        IfExpr ifExpr = new IfExpr();
        ifExpr.setPos(position);
        Block condBlock = new Block();
        condBlock.addChild(condition);
        ifExpr.addChild(condBlock);
        ifExpr.addChild(thenBlock);
        ifExpr.addChild(elseBlock);
        return ifExpr;
    }

    static Tree whileExpr(Position position, Tree condition, Tree body) {
        WhileExpr whileExpr = new WhileExpr();
        whileExpr.setPos(position);
        // パーサー側の都合でBlockではなくそのままの値が来るため
        Block conditionBlock = new Block();
        conditionBlock.addChild(condition);
        whileExpr.addChild(conditionBlock);

        Block bodyBlock = new Block();
        bodyBlock.addChild(body);
        whileExpr.addChild(bodyBlock);
        return whileExpr;
    }

    static Tree block(List<Tree> trees) {
        Block block = new Block();
        for (Tree tree : trees) {
            block.addChild(tree);
        }
        return block;
    }

    static Tree unaryApply(Position position, Tree operator, Tree expr) {
        Apply apply = new Apply();
        apply.setPos(position);
        apply.setName(Terms.varName(operator.getPos(), operator.toString()));
        apply.addChild(expr);
        return apply;
    }

    static Tree cast(Position position, Tree tree, Tree typeName) {
        Cast cast = new Cast();
        cast.setPos(position);
        cast.addChild(tree);
        cast.setTypeName(typeName);
        return cast;
    }

    static Tree newInstance(Position position, Tree typeName, Tree args) {
        Apply apply = new Apply();
        apply.setPos(position);
        apply.setName(Terms.varName(Position.noPos(), "<init>"));
        apply.setTypeName(typeName);
        apply.addChild(args);
        return apply;
    }

    static Tuple tuple() {
        return new Tuple();
    }

    static Tree exprName(Token exprName) {
        Identifier identifier = new Identifier();
        identifier.setPos(Position.of(exprName));
        identifier.setName(Terms.varName(Position.of(exprName), exprName.getText()));
        return identifier;
    }

    static Tree exprName(Position position, String name) {
        Identifier identifier = new Identifier();
        identifier.setPos(position);
        identifier.setName(Terms.varName(position, name));
        return identifier;
    }

    static Tree superInstance(Position position) {
        return Terms.superName(position);
    }

    static Tree thisInstance(Position position) {
        return Terms.thisName(position);
    }

    static Tree clone(Apply apply) {
        return Exprs.apply(apply.getPos(), apply.getChild(0), apply.getName(), apply.getChild(1));
    }
}
