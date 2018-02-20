package org.karaffe.compiler.tree.v2.api;

import java.util.List;

import org.karaffe.compiler.tree.v2.names.SimpleName;

public interface TypeDefStatement extends Statement {
    public boolean isInterfaceDecl();

    public boolean isClassDecl();

    public SimpleName getName();

    public SimpleName getSuperClass();

    public List<? extends SimpleName> getInterfaces();

    public List<? extends Statement> getBody();
}
