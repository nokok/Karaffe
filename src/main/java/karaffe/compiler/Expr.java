package karaffe.compiler;

import java.util.List;

class Expr {

    static Expression add(Expression e1, Expression e2, int e1Line, int e1Column, int e2Line, int e2Column) {
        return new AddExpr(e1, e2, e1Line, e1Column, e2Line, e2Column);
    }

    static Expression andExpr(Expression e1, Expression e2) {
        return new AndExpr(e1, e2);
    }

    static Expression assignmentExpr(Identifier target, Expression e) {
        return new AssignmentExpr(target, e);
    }

    static Expression bangExpr(Expression e) {
        return new BangExpr(e);
    }

    static Expression beqExpr(Expression e1, Expression e2) {
        return new BangExpr(new EqeqExpr(e1, e2));
    }

    static Expression castExpr(Expression e, Identifier to) {
        return new CastExpr(e, to);
    }

    static Expression div(Expression e1, Expression e2, int e1left, int e1right, int e2left, int e2right) {
        return new DivExpr(e1, e2, e1left, e1right, e2left, e2right);
    }

    static Expression eqeqExpr(Expression e1, Expression e2) {
        return new EqeqExpr(e1, e2);
    }

    static Expression falseLiteral() {
        return new FalseLiteral();
    }

    static Expression gtExpr(Expression e1, Expression e2) {
        return new GtExpr(e1, e2);
    }

    static Expression gteqExpr(Expression e1, Expression e2) {
        return new GtEqExpr(e1, e2);
    }

    static Identifier ident(String id, int idleft, int idright) {
        return new Identifier(id, idleft, idright);
    }

    static Expression ifExpr(Expression e, List<NodeGeneratable<?>> b1) {
        return new IfExpr(e, b1);
    }

    static Expression ifExpr(Expression e, List<NodeGeneratable<?>> b1, List<NodeGeneratable<?>> b2) {
        return new IfExpr(e, b1, b2);
    }

    static Expression ifExpr(Expression e, Expression b1) {
        return new IfExpr(e, b1);
    }

    static Expression ifExpr(Expression e, Expression b1, Expression b2) {
        return new IfExpr(e, b1, b2);
    }

    static Expression intLiteral(String i, int ileft, int iright) {
        return new IntLiteral(i, ileft, iright);
    }

    static Expression ltExpr(Expression e1, Expression e2) {
        return new LtExpr(e1, e2);
    }

    static Expression lteqExpr(Expression e1, Expression e2) {
        return new LtEqExpr(e1, e2);
    }

    static Expression methodInvocation(Expression target, Identifier methodName, List<Argument> e) {
        return new MethodInvocation(target, methodName, e);
    }

    static Expression minusAssignmentExpr(Identifier target, Expression e) {
        return new MinusAsgnExpr(target, e);
    }

    static Expression mul(Expression e1, Expression e2, int e1left, int e1right, int e2left, int e2right) {
        return new MulExpr(e1, e2, e1left, e1right, e2left, e2right);
    }

    static Expression orExpr(Expression e1, Expression e2) {
        return new OrExpr(e1, e2);
    }

    static Expression plusAssignmentExpr(Identifier target, Expression e) {
        return new PlusAsgnExpr(target, e);
    }

    static Expression safeCastExpr(Expression e, Identifier to) {
        return new SafeCastExpr(e, to);
    }

    static Expression stringLiteral(String s) {
        return new StringLiteral(s);
    }

    static Expression sub(Expression e1, Expression e2, int e1left, int e1right, int e2left, int e2right) {
        return new SubExpr(e1, e2, e1left, e1right, e2left, e2right);
    }

    static Expression trueLiteral() {
        return new TrueLiteral();
    }

    static Expression unaryMinus(Expression e) {
        return new UnaryMinus(e);
    }

    static Expression whileExpr(Expression e, Expression l) {
        return new WhileExpr(e, l);
    }

    static Expression whileExpr(Expression e, List<NodeGeneratable<?>> l) {
        return new WhileExpr(e, l);
    }

}
