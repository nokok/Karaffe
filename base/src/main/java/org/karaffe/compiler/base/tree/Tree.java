package org.karaffe.compiler.base.tree;

import org.antlr.v4.runtime.Token;
import org.karaffe.compiler.base.attr.Attributes;
import org.karaffe.compiler.base.pos.Position;
import org.karaffe.compiler.base.tree.term.Terms;

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

    class CompilationUnit extends AbstractTree implements Tree {

        @Override
        public <R, P> R accept(TreeVisitor<R, P> visitor, P p) {
            return visitor.visit(this, p);
        }

        public void addTopLevel(Tree tree) {
            this.addChild(tree);
        }
    }

    class Template extends AbstractTree {

        private Tree superClass;
        private List<Tree> interfaces;

        public Template() {
            this.superClass = Terms.typeName(Position.noPos(), "Any");
            this.interfaces = new TreeList();
        }

        public Tree getSuperClass() {
            return superClass;
        }

        public void setSuperClass(Tree superClass) {
            this.superClass = Objects.requireNonNull(superClass);
        }

        public void addInterface(Tree type) {
            this.interfaces.add(Objects.requireNonNull(type));
        }

        public List<Tree> getInterfaces() {
            return interfaces;
        }

        @Override
        public <R, P> R accept(TreeVisitor<R, P> visitor, P p) {
            return visitor.visit(this, p);
        }

    }

    default Tree withPos(Position pos) {
        this.setPos(Objects.requireNonNull(pos));
        return this;
    }

    default Tree withPos(Token token) {
        this.setPos(Position.of(token));
        return this;
    }

    default Tree withName(Tree name) {
        this.setName(Objects.requireNonNull(name));
        return this;
    }

    String toString(String indent);
}
