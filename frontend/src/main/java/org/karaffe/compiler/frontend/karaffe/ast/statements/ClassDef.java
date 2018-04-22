package org.karaffe.compiler.frontend.karaffe.ast.statements;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.karaffe.compiler.base.pos.Position;
import org.karaffe.compiler.frontend.karaffe.ast.api.StatementType;
import org.karaffe.compiler.frontend.karaffe.ast.api.TypeDefMember;
import org.karaffe.compiler.frontend.karaffe.ast.api.TypeDefStatement;
import org.karaffe.compiler.frontend.karaffe.ast.names.SimpleName;
import org.karaffe.compiler.frontend.karaffe.ast.names.TypeName;

public class ClassDef extends AbstractTypeDefStatement {

    public ClassDef(SimpleName typeName, TypeName superClassName) {
        super(typeName, superClassName);
    }

    public ClassDef(SimpleName typeName, TypeDefMember... members) {
        super(typeName, TypeName.rootClass(), new ArrayList<>(0), new ArrayList<>(Arrays.asList(members)));
    }

    public ClassDef(Position position, SimpleName typeName, TypeName superClassName, List<? extends TypeName> interfaces, List<? extends TypeDefMember> members) {
        super(position, typeName, superClassName, interfaces, members);
    }

    @Override
    public StatementType getStatementType() {
        return StatementType.CLASS_DEF;
    }

    @Override
    public String toString() {
        return String.format("class %s extends %s%s {\n"
                + "%s\n"
                + "}",
                this.getName(),
                this.getSuperClassName(),
                this.getInterfaceNames().isEmpty() ? "" : "implements " + String.join(", ", this.getInterfaceNames()),
                String.join(";\n", this.getBody().stream().map(TypeDefMember::toString).collect(Collectors.toList())));
    }

}
