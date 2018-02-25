package org.karaffe.compiler.tree.v2.statements;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.karaffe.compiler.pos.Position;
import org.karaffe.compiler.tree.v2.api.StatementType;
import org.karaffe.compiler.tree.v2.api.TypeDefMember;
import org.karaffe.compiler.tree.v2.names.SimpleName;

public class InterfaceDef extends AbstractTypeDefStatement {

    public InterfaceDef(SimpleName typeName) {
        super(typeName,
                SimpleName.rootClass());
    }

    public InterfaceDef(Position position, SimpleName typeName) {
        super(position,
                typeName,
                SimpleName.rootClass());
    }

    public InterfaceDef(Position position, SimpleName typeName, List<? extends TypeDefMember> body) {
        super(position,
                typeName,
                SimpleName.rootClass(),
                new ArrayList<>(),
                body);
    }

    public InterfaceDef(InterfaceDef otherInterfaceDef) {
        super(otherInterfaceDef.getPosition(),
                otherInterfaceDef.getName(),
                otherInterfaceDef.getSuperClassName(),
                otherInterfaceDef.getInterfaceNames(),
                otherInterfaceDef.getBody());
    }

    @Override
    public StatementType getStatementType() {
        return StatementType.INTERFACE_DEF;
    }

    @Override
    public boolean isInterfaceDecl() {
        return true;
    }

    @Override
    public boolean isClassDecl() {
        return false;
    }

    @Override
    public String toString() {
        return String.format("interface %s%s {\n"
                + "%s\n"
                + "}",
                this.getName(),
                this.getInterfaceNames().isEmpty() ? "" : " implements " + String.join(", ", this.getInterfaceNames()),
                String.join(";\n", this.getBody().stream().map(TypeDefMember::toString).collect(Collectors.toList())));
    }

}
