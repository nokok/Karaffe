package org.karaffe.compiler.parser;

import java.util.ArrayList;
import java.util.List;

import org.karaffe.compiler.lexer.Tokens;
import org.karaffe.compiler.parser.util.ChainParser;
import org.karaffe.compiler.parser.util.MatchResult;
import org.karaffe.compiler.tree.CompileUnit;
import org.karaffe.compiler.tree.PackageDef;
import org.karaffe.compiler.tree.TypeDef;
import org.karaffe.compiler.tree.TypeDef.ClassDef;
import org.karaffe.compiler.tree.TypeDefs;

public class KaraffeParser implements Parser {

    @Override
    public MatchResult parse(final Tokens tokens) {
        final ChainParser parser = new ChainParser(tokens);
        final boolean hasPackageDef = parser.testNext(new PackageDefParser(), PackageDef.class);
        final PackageDef packageDef = hasPackageDef ? parser.lastMatch() : null;

        final List<TypeDef> typeDefs = new ArrayList<>();
        if (parser.testNext(new MainClassDeclParser(), TypeDef.class)) {
            typeDefs.add(parser.lastMatch());
        }

        while (parser.testNext(new ClassDefParser(), ClassDef.class)) {
            final ClassDef classDef = parser.lastMatch();
            typeDefs.add(classDef);
        }

        if (!parser.nextMatch(new EOFParser())) {
            return parser.toFailure();
        }

        return new MatchResult.Success(parser.next(), parser.matched(), new CompileUnit(packageDef, new TypeDefs(typeDefs)));
    }

}
