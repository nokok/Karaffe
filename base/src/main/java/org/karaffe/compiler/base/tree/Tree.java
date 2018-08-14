package org.karaffe.compiler.base.tree;

import org.karaffe.compiler.base.attr.Attributes;
import org.karaffe.compiler.base.pos.Position;
import org.karaffe.compiler.base.tree.term.Path;
import org.karaffe.compiler.base.tree.term.Terms;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public interface Tree extends LocatableElement, NameableElement, ModifiableElement, TypedElement, NodeOperator, Attributes {
    default <R> R accept(TreeVisitor<R, ?> visitor) {
        return this.accept(visitor, null);
    }

    <R, P> R accept(TreeVisitor<R, P> visitor, P p);

    default <R, P> List<R> acceptChildren(long skip, TreeVisitor<R, P> visitor, P p) {
        return this.getChildren().stream().skip(skip).map(child -> child.accept(visitor, p)).collect(Collectors.toList());
    }

    default <R, P> List<R> acceptChildren(TreeVisitor<R, P> visitor, P p) {
        return acceptChildren(0, visitor, p);
    }

    TreeKind getKind();

    void setKind(TreeKind kind);

    Tree getParent();

    void setParent(Tree tree);

    class CompilationUnit extends AbstractTree implements Tree {

        CompilationUnit() {
            this(null);
        }

        CompilationUnit(Tree parent) {
            super(parent, TreeKind.COMPILE_UNIT);
        }

        @Override
        public <R, P> R accept(TreeVisitor<R, P> visitor, P p) {
            return visitor.visitCompileUnit(this, p);
        }

        public void addTopLevel(Tree tree) {
            this.addChild(tree);
        }
    }

    class Template extends AbstractTree {

        private Path superClass;
        private List<Path> interfaces;

        Template() {
            this(null);
        }

        Template(Tree parent) {
            super(parent, TreeKind.TEMPLATE);
            this.superClass = Terms.typeName(Position.noPos(), "Any");
            this.interfaces = new ArrayList<>();
        }

        public Path getSuperClass() {
            return superClass;
        }

        public void setSuperClass(Path superClass) {
            this.superClass = Objects.requireNonNull(superClass);
        }

        public void addInterface(Path type) {
            this.interfaces.add(Objects.requireNonNull(type));
        }

        public List<Path> getInterfaces() {
            return interfaces;
        }

        @Override
        public <R, P> R accept(TreeVisitor<R, P> visitor, P p) {
            return visitor.visitTemplate(this, p);
        }
    }
}
