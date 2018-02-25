package org.karaffe.compiler.tree.transform;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.karaffe.compiler.pos.Position;
import org.karaffe.compiler.tree.transform.api.BaseDefaultTransformer;
import org.karaffe.compiler.tree.v2.names.SimpleName;
import org.karaffe.compiler.tree.v2.statements.LetLocalDef;
import org.karaffe.compiler.types.v2.TypeConstraints;

public class TypeInferer implements BaseDefaultTransformer {

    public static final String name = "typer";
    private final Map<SimpleName, TypeConstraints> nameTypeMap = new HashMap<>();

    @Override
    public String getTransformerName() {
        return TypeInferer.name;
    }

    @Override
    public LetLocalDef transform(LetLocalDef oldLocalLetDef) {
        BaseDefaultTransformer.super.onLetLocalDefBefore(oldLocalLetDef);
        Position newPosition = oldLocalLetDef.getPosition();
        SimpleName letDefName = oldLocalLetDef.getName();
        Optional<SimpleName> typeName = oldLocalLetDef.getTypeName();
        if (oldLocalLetDef.hasTypeName()) {
            TypeConstraints.of(oldLocalLetDef.getTypeName().get());
        } else {
            TypeConstraints.infer(oldLocalLetDef.getInitializer().get());
        }
        LetLocalDef letLocalDef = new LetLocalDef(
                oldLocalLetDef.getPosition(),
                transform(oldLocalLetDef.getName()),
                oldLocalLetDef.getTypeName().map(this::transform).orElse(null),
                oldLocalLetDef.getInitializer().map(this::transform).orElse(null));
        BaseDefaultTransformer.super.onLetLocalDefAfter(letLocalDef);
        return letLocalDef;
    }
}
