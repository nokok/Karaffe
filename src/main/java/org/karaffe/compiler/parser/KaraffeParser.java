package org.karaffe.compiler.parser;

import org.karaffe.compiler.lexer.KeywordToken;
import org.karaffe.compiler.lexer.Tokens;
import org.karaffe.compiler.parser.util.CParser;
import org.karaffe.compiler.parser.util.MatchResult;
import org.karaffe.compiler.parser.util.TokenMatcher;
import org.karaffe.compiler.tree.Name;
import org.karaffe.compiler.tree.PackageDef;
import org.karaffe.compiler.tree.Select;

public class KaraffeParser implements Parser {

    @Override
    public MatchResult parse(final Tokens input) {
        final CParser cp = new CParser(input);
        final boolean hasPackageKeyword = cp.testNext(TokenMatcher.create(KeywordToken.Package.class), false);
        if (hasPackageKeyword) {
            if (!cp.testNext(new PackageDefParser())) {
                return cp.toFailure();
            }
        }

        final PackageDef packageDef = hasPackageKeyword ? cp.lastMatch() : new PackageDef(new Select(new Name("<root>")));

        while (cp.selectFirst(new MainClassDeclParser(), new ClassDefParser())) {
            packageDef.addTypeDef(cp.lastMatch());
        }

        if (!cp.testNext(new EOFParser())) {
            return cp.toFailure();
        }

        return new MatchResult.Success(cp.next(), cp.matched(), packageDef);
    }

}
