package org.karaffe.compiler.parser;

import java.util.ArrayList;
import java.util.List;

import org.karaffe.compiler.lexer.KeywordToken;
import org.karaffe.compiler.lexer.Tokens;
import org.karaffe.compiler.parser.util.CParser;
import org.karaffe.compiler.parser.util.MatchResult;
import org.karaffe.compiler.parser.util.TokenMatcher;
import org.karaffe.compiler.tree.CompileUnit;
import org.karaffe.compiler.tree.Name;
import org.karaffe.compiler.tree.PackageDef;
import org.karaffe.compiler.tree.Select;
import org.karaffe.compiler.tree.TypeDefs;
import org.karaffe.compiler.tree.base.Node;

public class KaraffeParser implements Parser {

    @Override
    public MatchResult parse(final Tokens tokens) {
        final CParser cp = new CParser(tokens);
        final boolean hasPackageKeyword = cp.testNext(TokenMatcher.create(KeywordToken.Package.class), false);
        if (hasPackageKeyword) {
            if (!cp.testNext(new PackageDefParser())) {
                return cp.toFailure();
            }
        }

        final PackageDef packageDef = hasPackageKeyword ? cp.lastMatch() : new PackageDef(new Select(new Name("<root>")));

        final List<Node> typeDefs = new ArrayList<>();
        if (cp.testNext(new MainClassDeclParser())) {
            typeDefs.add(cp.lastMatch());
        }

        while (cp.testNext(new ClassDefParser())) {
            typeDefs.add(cp.lastMatch());
        }

        if (!cp.testNext(new EOFParser())) {
            return cp.toFailure();
        }

        return new MatchResult.Success(cp.next(), cp.matched(), new CompileUnit(packageDef, new TypeDefs(typeDefs)));
    }

}
