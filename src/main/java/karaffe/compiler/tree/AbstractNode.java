/**
 * Karaffe Programming Language
 */
package karaffe.compiler.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public abstract class AbstractNode implements AST {

    private final List<AST> children = new ArrayList<>();

    @Override
    public List<AST> children() {
        return children;
    }

    public void addChildren(AST ast) {
        children.add(Objects.requireNonNull(ast));
    }

    public void addChildren(Optional<AST> ast) {
        Objects.requireNonNull(ast).ifPresent(children::add);
    }

}
