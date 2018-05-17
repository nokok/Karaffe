package org.karaffe.compiler.base.tree;

public class ClassDef extends AbstractTree implements Defs, NamedElement<String> {

    private String name;
    private Template body;

    public ClassDef(String name) {
        this.name = name;
    }

    public void setBody(Template body) {
        this.body = body;
    }

    public Template getBody() {
        return this.body;
    }

    public void add(Defs body) {
        this.body.add(body);
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public <T> T accept(TreeVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
