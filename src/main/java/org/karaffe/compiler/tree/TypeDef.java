package org.karaffe.compiler.tree;

import java.util.ArrayList;
import java.util.List;

import org.karaffe.compiler.lexer.IdentifierToken;
import org.karaffe.compiler.tree.base.AbstractNode;
import org.karaffe.compiler.tree.base.Name;
import org.karaffe.compiler.tree.base.NodeType;

public abstract class TypeDef extends AbstractNode {

    private final Name className;

    public TypeDef(final NodeType type, final Name className) {
        super(type, className.getPosition());
        this.className = className;
    }

    public String getClassName() {
        return this.className.getText();
    }

    public static class ClassDef extends TypeDef {
        private final Name superName;
        private final List<VarDef> fields;
        private final List<MethodDef> methodDefs;

        public ClassDef(final Name className) {
            this(className, null, null, null);
        }

        public ClassDef(final Name className, final Name superClassName, final List<VarDef> fieldDefs, final List<MethodDef> methodDefs) {
            super(NodeType.DEFCLASS, className);
            this.superName = superClassName == null ? new Name(new IdentifierToken.TypeName("Object")) : superClassName;
            this.fields = fieldDefs == null ? new ArrayList<>(0) : fieldDefs;
            this.methodDefs = methodDefs == null ? new ArrayList<>(0) : methodDefs;
        }

        public String getSuperName() {
            return this.superName.getText();
        }

        public List<VarDef> getFields() {
            return new ArrayList<>(this.fields);
        }

        public List<MethodDef> getMethods() {
            return new ArrayList<>(this.methodDefs);
        }
    }

}
