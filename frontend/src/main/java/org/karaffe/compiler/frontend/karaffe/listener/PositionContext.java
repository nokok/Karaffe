package org.karaffe.compiler.frontend.karaffe.listener;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.karaffe.compiler.base.pos.Position;

public interface PositionContext {

    public default Position getPosition(TerminalNode node) {
        Token sym = node.getSymbol();
        return Position.of(sym.getTokenSource().getSourceName(), sym.getLine(), sym.getCharPositionInLine());
    }

    public default Position getPosition(ParserRuleContext context) {
        Token startToken = context.getStart();
        return Position.of(startToken.getTokenSource().getSourceName(), startToken.getLine(), startToken.getCharPositionInLine());
    }

}
