package org.karaffe.compiler.frontend.karaffe.ast.statements;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.karaffe.compiler.base.pos.Position;
import org.karaffe.compiler.frontend.karaffe.ast.api.StatementType;
import org.karaffe.compiler.frontend.karaffe.ast.api.TypeDefMember;
import org.karaffe.compiler.frontend.karaffe.ast.names.SimpleName;
import org.karaffe.compiler.frontend.karaffe.ast.names.TypeName;

public class InterfaceDef extends AbstractTypeDefStatement {

    public InterfaceDef(SimpleName typeName) {
        super(typeName,
                TypeName.rootClass());
    }

    public InterfaceDef(Position position, SimpleName typeName) {
        super(position,
                typeName,
                TypeName.rootClass());
    }

    public InterfaceDef(Position position, SimpleName typeName, List<? extends TypeDefMember> body) {
        super(position,
                typeName,
                TypeName.rootClass(),
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
    public String toString() {
        return String.format("interface %s%s {\n"
                + "%s\n"
                + "}",
                this.getName(),
                this.getInterfaceNames().isEmpty() ? "" : " implements " + String.join(", ", this.getInterfaceNames()),
                String.join(";\n", this.getBody().stream().map(TypeDefMember::toString).collect(Collectors.toList())));
    }

}
