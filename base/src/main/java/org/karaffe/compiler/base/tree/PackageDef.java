package org.karaffe.compiler.base.tree;

public class PackageDef extends AbstractTree implements Defs, NamedElement<String> {
    private String name;

    public PackageDef(String packageName) {
        this.name = packageName;
    }

    @Override
    public <T> T accept(TreeVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
