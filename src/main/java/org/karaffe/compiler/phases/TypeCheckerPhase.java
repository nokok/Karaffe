package org.karaffe.compiler.phases;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.karaffe.compiler.tree.CompileUnit;
import org.karaffe.compiler.tree.NodeType;
import org.karaffe.compiler.tree.ValDef;
import org.karaffe.compiler.tree.VarDef;
import org.karaffe.compiler.tree.base.Node;

public class TypeCheckerPhase extends AbstractCompileUnitTransformer {

    @Override
    public Optional<CompileUnit> transform(CompileUnit input) {
        TypeEnv typeEnv = buildTypeEnv(input);
        return null;
    }
    private TypeEnv buildTypeEnv(Node compileUnit) {
        TypeEnv typeEnv = new TypeEnv();
        List<? extends Node> children = compileUnit.getChildren();
        for (Node child : children) {
            if (child.getNodeType().equals(NodeType.DEFVAL)) {
                ValDef valDef = (ValDef) child;
                typeEnv.put(valDef.getName(), valDef.getTypeName());
            } else if (child.getNodeType().equals(NodeType.DEFVAR)) {
                VarDef varDef = (VarDef) child;
                typeEnv.put(varDef.getName(), varDef.getTypeName());
            } else {
                typeEnv = typeEnv.merge(buildTypeEnv(child));
            }
        }
        return typeEnv;
    }
}

class TypeEnv {
    private final Map<String /* id */, String/* Type */> vTable;

    public TypeEnv() {
        this.vTable = new HashMap<>();
    }

    public TypeEnv(Map<String, String> map) {
        this.vTable = new HashMap<>(map);
    }

    public void put(String id, String type) {
        vTable.put(id, type);
    }

    public TypeEnv merge(TypeEnv other) {
        Map<String, String> merged = new HashMap<>(this.vTable);
        for (Map.Entry<String, String> entry : other.vTable.entrySet()) {
            if (merged.containsKey(entry.getKey())) {
                // Conflict
                System.out.println("Conflict");
            } else {
                merged.put(entry.getKey(), entry.getValue());
            }
        }
        return new TypeEnv(merged);
    }

    @Override
    public String toString() {
        return this.vTable.toString();
    }
}
