package org.karaffe.compiler.parser;

import org.karaffe.compiler.lexer.CommonToken.Semi;
import org.karaffe.compiler.lexer.KeywordToken.Package;
import org.karaffe.compiler.lexer.Tokens;
import org.karaffe.compiler.parser.util.CParser;
import org.karaffe.compiler.parser.util.CParser.Action;
import org.karaffe.compiler.parser.util.MatchResult;
import org.karaffe.compiler.tree.PackageDef;
import org.karaffe.compiler.tree.Select;
import org.karaffe.compiler.tree.base.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PackageDefParser implements Parser {
    private static final Logger LOGGER = LoggerFactory.getLogger(PackageDef.class);

    @Override
    public MatchResult parse(final Tokens input) {
        PackageDefParser.LOGGER.debug("Input : {}", input);
        final CParser cp = new CParser(input);
        return cp.chain(nodes -> {
            Node path = nodes.get(1);
            return new PackageDef((Select) path);
        },
                Action.of(Package.class),
                Action.of(new PathParser()), // 1
                Action.of(Semi.class));
    }
}
