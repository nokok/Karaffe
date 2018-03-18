package org.karaffe.compiler.ast.api;

import java.util.List;

import org.karaffe.compiler.ast.names.SimpleName;
import org.karaffe.compiler.ast.names.TypeName;

public interface TypeDefStatement extends Statement {
    public boolean isInterfaceDecl();

    public boolean isClassDecl();

    public SimpleName getName();

    public TypeName getSuperClassName();

    public List<? extends TypeName> getInterfaceNames();

    public List<? extends TypeDefMember> getBody();
}
