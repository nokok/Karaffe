/**
 * Karaffe Programming Language
 */
package karaffe.compiler.phase.checker;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import karaffe.compiler.phase.Phase;
import karaffe.compiler.tree.AST;

public class Warnings extends Phase<AST, AST> {

    private final List<Consumer<AST>> consumers = new ArrayList<>();

    {
        consumers.add(new EmptyFileChecker());
    }

    public Warnings() {
        super("warnings");
    }

    @Override
    public AST apply(AST t) {
        consumers.forEach(c -> c.accept(t));
        return t;
    }
}
