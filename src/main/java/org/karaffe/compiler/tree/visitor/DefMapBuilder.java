package org.karaffe.compiler.tree.visitor;

import java.util.HashMap;
import java.util.Map;

import org.karaffe.compiler.tree.Block;
import org.karaffe.compiler.tree.LetDef;
import org.karaffe.compiler.tree.PackageDef;
import org.karaffe.compiler.tree.TypeDef.ClassDef;
import org.karaffe.compiler.tree.VarDef;

public class DefMapBuilder extends KaraffeTreeVisitorAdapter {
    private final Map<String, String> map = new HashMap<>();
    private PackageDef packageDef;
    private ClassDef parent;
    private Block block;

    @Override
    public void visit(final Block node) {
        this.block = node;
        super.visit(node);
    }

    @Override
    public void visit(final ClassDef node) {
        this.parent = node;
        super.visit(node);
    }

    @Override
    public void visit(final LetDef node) {
        System.out.println(String.format("VarName Hash: %14d, name: %s, type: %s",
                String.format("%s.%s/%s/%s", this.packageDef.getRawPackageName(), this.parent.getClassName(), this.block.hashCode(), node.getName()).hashCode(),
                node.getName(),
                node.getTypeName()));
    }

    @Override
    public void visit(final PackageDef node) {
        this.packageDef = node;
        super.visit(node);
    }

    @Override
    public void visit(final VarDef node) {
        System.out.println(String.format("VarName Hash: %14d, name: %s, type: %s",
                String.format("%s.%s/%s/%s", this.packageDef.getRawPackageName(), this.parent.getClassName(), this.block.hashCode(), node.getName()).hashCode(),
                node.getName(),
                node.getTypeName()));
    }
}
