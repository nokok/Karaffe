package org.karaffe.compiler.frontend.karaffe.ast.api;

import org.karaffe.compiler.frontend.karaffe.ast.names.SimpleName;
import org.karaffe.compiler.frontend.karaffe.ast.names.TypeName;

import java.util.List;

@Deprecated
public interface TypeDefStatement extends Statement {

    SimpleName getName();

    TypeName getSuperClassName();

    List<? extends TypeName> getInterfaceNames();

    List<? extends TypeDefMember> getBody();
}
