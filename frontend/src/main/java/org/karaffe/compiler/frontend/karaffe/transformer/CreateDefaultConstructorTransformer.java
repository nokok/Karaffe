package org.karaffe.compiler.frontend.karaffe.transformer;

import org.karaffe.compiler.ast.api.Statement;
import org.karaffe.compiler.ast.names.SimpleName;
import org.karaffe.compiler.ast.names.TypeName;
import org.karaffe.compiler.ast.statements.ClassDef;
import org.karaffe.compiler.ast.statements.MethodDef;
import org.karaffe.compiler.frontend.karaffe.transformer.AbstractTransformer;

import java.util.ArrayList;
import java.util.List;

public class CreateDefaultConstructorTransformer extends AbstractTransformer {
    public CreateDefaultConstructorTransformer() {
        super("create-ctor");
    }

    @Override
    public ClassDef transformBody(ClassDef oldClassDef) {
        boolean noConstructor = oldClassDef.getBody().isEmpty();

        if (noConstructor) {
            List<Statement> body = new ArrayList<>();
            MethodDef constructor = new MethodDef(TypeName.voidType(), new SimpleName("<init>"), body);
            oldClassDef.addMember(constructor);
        }
        return oldClassDef;
    }
}
