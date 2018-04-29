package org.karaffe.compiler.frontend.karaffe.ast.api;

import org.karaffe.compiler.frontend.karaffe.ast.names.SimpleName;
import org.karaffe.compiler.frontend.karaffe.ast.names.TypeName;

import java.util.List;

public interface TypeDefStatement extends Statement {

    SimpleName getName();

    void setName(SimpleName className);

    TypeName getSuperClassName();

    void setSuperClassName(TypeName superClassName);

    List<? extends TypeName> getInterfaceNames();

    void addInterface(TypeName impl);

    void replaceInterfaces(List<TypeName> interfaces);

    List<? extends Statement> getBody();

    void replaceBody(List<Statement> members);
}
