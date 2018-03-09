package org.karaffe.compiler.tree.v2.api;

import java.util.List;

import org.karaffe.compiler.tree.v2.names.SimpleName;
import org.karaffe.compiler.tree.v2.names.TypeName;

public interface TypeDefStatement extends Statement {
    public boolean isInterfaceDecl();

    public boolean isClassDecl();

    public SimpleName getName();

    public TypeName getSuperClassName();

    public List<? extends TypeName> getInterfaceNames();

    public List<? extends TypeDefMember> getBody();
}
