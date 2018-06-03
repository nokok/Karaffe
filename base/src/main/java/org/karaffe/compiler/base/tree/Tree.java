package org.karaffe.compiler.base.tree;

import org.karaffe.compiler.base.tree.term.Terms;
import org.karaffe.compiler.base.tree.type.Type;
import org.karaffe.compiler.base.tree.type.Types;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public interface Tree extends LocatableElement, NameableElement, ModifiableElement, TypedElement, NodeOperator {
    <R, P> R accept(TreeVisitor<R, P> visitor, P p);

    TreeKind getKind();

    void setKind(TreeKind kind);

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

        private Type superClass;
        private List<Type> interfaces;

        Template() {
            this(null);
        }

        Template(Tree parent) {
            super(parent, TreeKind.TEMPLATE);
            this.superClass = Types.simple(Terms.typeName("Any"));
            this.interfaces = new ArrayList<>();
        }

        public void setSuperClass(Type superClass) {
            this.superClass = Objects.requireNonNull(superClass);
        }

        public Type getSuperClass() {
            return superClass;
        }

        public void addInterface(Type type) {
            this.interfaces.add(Objects.requireNonNull(type));
        }

        public List<Type> getInterfaces() {
            return interfaces;
        }

        @Override
        public <R, P> R accept(TreeVisitor<R, P> visitor, P p) {
            return visitor.visitTemplate(this, p);
        }
    }
}
