package org.karaffe.compiler.base.tree;

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
        Template() {
            this(null);
        }

        Template(Tree parent) {
            super(parent, TreeKind.TEMPLATE);
        }

        @Override
        public <R, P> R accept(TreeVisitor<R, P> visitor, P p) {
            return visitor.visitTemplate(this, p);
        }
    }
}
