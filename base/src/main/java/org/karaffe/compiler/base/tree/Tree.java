package org.karaffe.compiler.base.tree;

public interface Tree extends LocatableElement, NameableElement, ModifiableElement {
    <R, P> R accept(TreeVisitor<R, P> visitor, P p);

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
}
