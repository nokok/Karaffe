/**
 * Karaffe Programming Language
 */
package karaffe.compiler.phase.parser;

import java.util.stream.Stream;
import karaffe.compiler.phase.Phase;
import karaffe.compiler.phase.scope.ScopePhase;
import karaffe.compiler.tree.AST;

public class GenASTPhase extends Phase<String, AST> {

    @Override
    public AST apply(String t) {
        return Stream.of(t)
                .map(source -> new ParserPhase().apply(source))
                .map(ast -> new ScopePhase().apply(ast))
                .findFirst()
                .get();
    }

}
