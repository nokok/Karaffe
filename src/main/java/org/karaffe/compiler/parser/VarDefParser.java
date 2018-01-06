package org.karaffe.compiler.parser;

import org.karaffe.compiler.lexer.CommonToken.Semi;
import org.karaffe.compiler.lexer.OperatorToken.Equals;
import org.karaffe.compiler.lexer.Tokens;
import org.karaffe.compiler.parser.util.CParser;
import org.karaffe.compiler.parser.util.CParser.Action;
import org.karaffe.compiler.parser.util.MatchResult;
import org.karaffe.compiler.tree.Modifiers;
import org.karaffe.compiler.tree.Name;
import org.karaffe.compiler.tree.TypeName;
import org.karaffe.compiler.tree.VarDef;
import org.karaffe.compiler.tree.base.Node;

public class VarDefParser implements Parser {

    @Override
    public MatchResult parse(final Tokens input) {
        CParser cp = new CParser(input);
        if (!cp.testNext(new TypeParser())) {
            return cp.toFailure();
        }
        Node typeNode = cp.lastMatch();
        if (!cp.testNext(new IdentifierParser())) {
            return cp.toFailure();
        }
        Node nameNode = cp.lastMatch();

        if (!cp.testNext(Semi.class)) {
            return cp.chain(nodes -> {
                Node initializer = nodes.get(1);
                return new VarDef(new Modifiers(), (Name) nameNode, (TypeName) typeNode, initializer);
            },
                    Action.of(Equals.class),
                    Action.of(new ExprParser()), // 1
                    Action.of(Semi.class));
        }
        return new MatchResult.Success(cp.next(), cp.matched(), new VarDef(new Modifiers(), (Name) nameNode, (TypeName) typeNode));
    }

}
