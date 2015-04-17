/**
 * Karaffe Programming Language
 */
package karaffe.compiler.tree;

import java.util.Optional;
import karaffe.compiler.visitor.Visitor;

public class AnnotationList extends AbstractNode {

    private final AST annotation;
    private final Optional<AST> annotationList;

    public AnnotationList(Object a, Object l) {
        this.annotation = (AST) a;
        this.annotationList = Optional.ofNullable((AST) l);
        addChildren(annotation);
        addChildren(annotationList);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.annotationList(this);
    }

}
