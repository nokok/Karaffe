package org.karaffe.compiler.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.karaffe.compiler.lexer.KeywordToken.Extends;
import org.karaffe.compiler.lexer.OperatorToken.LeftBrace;
import org.karaffe.compiler.lexer.OperatorToken.RightBrace;
import org.karaffe.compiler.lexer.Tokens;
import org.karaffe.compiler.parser.util.ChainParser;
import org.karaffe.compiler.parser.util.MatchResult;
import org.karaffe.compiler.parser.util.TokenMatcher;
import org.karaffe.compiler.tree.MethodDef;
import org.karaffe.compiler.tree.TypeDef.ClassDef;
import org.karaffe.compiler.tree.VarDef;
import org.karaffe.compiler.tree.base.Name;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClassDefParser implements Parser {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClassDefParser.class);

    @Override
    public MatchResult parse(final Tokens input) {
        ClassDefParser.LOGGER.debug("Input : {}", input);
        final ChainParser parser = new ChainParser(input);
        parser.nextMatch(TokenMatcher.classKeyword(), ChainParser.NO_OP);
        final Optional<Name> c = parser.nextMatch(TokenMatcher.identifier(), ChainParser.ID_NAME);
        if (!c.isPresent()) {
            ClassDefParser.LOGGER.debug("className not found.");
            return parser.toFailure();
        }
        final Name className = c.get();

        final boolean hasExtends = parser.testNext(TokenMatcher.create(Extends.class), ChainParser.NO_OP);
        final boolean hasSuperClass = parser.testNext(TokenMatcher.identifier(), ChainParser.ID_NAME);

        if (hasExtends != hasSuperClass) {
            ClassDefParser.LOGGER.debug("superClass not found. 1");
            return parser.toFailure();
        }

        final Name superClassName = hasExtends ? parser.lastMatch() : null;

        parser.testNext(TokenMatcher.create(LeftBrace.class), ChainParser.NO_OP);

        final List<VarDef> fieldDefs = new ArrayList<>();
        while (parser.testNext(new VarDefParser(), ChainParser.VARDEF)) {
            final VarDef varDef = parser.lastMatch();
            fieldDefs.add(varDef);
        }

        final List<MethodDef> methodDefs = new ArrayList<>();
        while (parser.testNext(new MethodDefParser(), ChainParser.METHODDEF)) {
            final MethodDef varDef = parser.lastMatch();
            methodDefs.add(varDef);
        }

        parser.testNext(TokenMatcher.create(RightBrace.class), ChainParser.NO_OP);

        if (parser.hasError()) {
            ClassDefParser.LOGGER.debug("gen error");
            return parser.toFailure();
        }
        return new MatchResult.Success(parser.next(), parser.matched(), new ClassDef(className, superClassName, fieldDefs, methodDefs));
    }

}
