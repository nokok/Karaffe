package karaffe.compiler;

import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.LdcInsnNode;

class StringLiteral implements Expression {

    private final String s;

    public StringLiteral(String s) {
        this.s = s;
    }

    @Override
    public InsnList toNode() {
        InsnList list = new InsnList();
        list.add(new LdcInsnNode(s));
        return list;
    }

    @Override
    public Class<String> inferredType() {
        return String.class;
    }

    @Override
    public String toString() {
        return "(string-literal " + s + ")";
    }

}
