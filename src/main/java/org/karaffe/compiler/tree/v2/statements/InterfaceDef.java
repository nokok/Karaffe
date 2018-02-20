package org.karaffe.compiler.tree.v2.statements;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.karaffe.compiler.pos.Position;
import org.karaffe.compiler.tree.v2.api.AbstractTree;
import org.karaffe.compiler.tree.v2.api.Statement;
import org.karaffe.compiler.tree.v2.api.StatementType;
import org.karaffe.compiler.tree.v2.api.TypeDefStatement;
import org.karaffe.compiler.tree.v2.names.SimpleName;

public class InterfaceDef extends AbstractTree implements TypeDefStatement {

    private final SimpleName interfaceName;
    private final List<Statement> interfaceBody;

    public InterfaceDef(SimpleName interfaceName, List<Statement> interfaceBody) {
        this.interfaceName = Objects.requireNonNull(interfaceName);
        this.interfaceBody = new ArrayList<>(Objects.requireNonNull(interfaceBody));
    }

    public InterfaceDef(Position position, SimpleName interfaceName, List<Statement> interfaceBody) {
        super(position);
        this.interfaceName = Objects.requireNonNull(interfaceName);
        this.interfaceBody = new ArrayList<>(Objects.requireNonNull(interfaceBody));
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
    public SimpleName getName() {
        return this.interfaceName;
    }

    @Override
    public SimpleName getSuperClass() {
        return new SimpleName("Object");
    }

    @Override
    public List<? extends SimpleName> getInterfaces() {
        // TODO: スーパ�?�インターフェースが実�?されたらここも返すように実�?する
        return new ArrayList<>(0);
    }

    @Override
    public List<? extends Statement> getBody() {
        return new ArrayList<>(this.interfaceBody);
    }

}
