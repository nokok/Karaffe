package org.karaffe.compiler.frontend.karaffe.ast.api;

import java.util.List;

import org.karaffe.compiler.frontend.karaffe.ast.names.SimpleName;
import org.karaffe.compiler.frontend.karaffe.ast.names.TypeName;

public interface TypeDefStatement extends Statement {
    public boolean isInterfaceDecl();

    public boolean isClassDecl();

    public SimpleName getName();

    public TypeName getSuperClassName();

    public List<? extends TypeName> getInterfaceNames();

    public List<? extends TypeDefMember> getBody();
}
