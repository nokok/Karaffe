package org.karaffe.compiler.frontend.karaffe.transformer;

import org.karaffe.compiler.base.pos.Position;
import org.karaffe.compiler.frontend.karaffe.ast.PackageDef;
import org.karaffe.compiler.frontend.karaffe.ast.api.TypeDefStatement;
import org.karaffe.compiler.frontend.karaffe.ast.names.PackageName;

import java.util.ArrayList;
import java.util.List;

public class CleanupTransformer extends AbstractTransformer {

    public CleanupTransformer() {
        super("clean-up");
    }

    @Override
    public PackageDef transformBody(PackageDef oldPackageDef) {
        Position pos = oldPackageDef.getPosition();
        PackageName packageName = oldPackageDef.getPackageName();
        List<? extends TypeDefStatement> typeDef = oldPackageDef.getTypeDefStatements();
        return new PackageDef(pos, packageName, new ArrayList<>(), typeDef);
    }
}
