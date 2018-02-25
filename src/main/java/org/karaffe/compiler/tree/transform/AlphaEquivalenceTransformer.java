package org.karaffe.compiler.tree.transform;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.karaffe.compiler.tree.transform.api.BaseDefaultTransformer;
import org.karaffe.compiler.tree.v2.api.Expression;
import org.karaffe.compiler.tree.v2.expressions.ExpressionName;
import org.karaffe.compiler.tree.v2.names.SimpleName;
import org.karaffe.compiler.tree.v2.statements.LetLocalDef;
import org.karaffe.compiler.util.NameGen;

public class AlphaEquivalenceTransformer implements BaseDefaultTransformer {

    public static final String name = "alpha-equivalence";
    private final NameGen nameGen = new NameGen("s_");
    private final Set<SimpleName> names = new HashSet<>();
    private final Map<SimpleName, SimpleName> nameMapping = new HashMap<>();

    @Override
    public String getTransformerName() {
        return AlphaEquivalenceTransformer.name;
    }

    @Override
    public SimpleName transform(SimpleName oldName) {
        if (this.nameMapping.containsKey(oldName)) {
            return new SimpleName(this.nameMapping.get(oldName));
        }
        return new SimpleName(oldName);
    }

    @Override
    public Expression transform(ExpressionName expressionName) {
        if (this.nameMapping.containsKey(expressionName)) {
            return new ExpressionName(this.nameMapping.get(expressionName));
        }
        return new ExpressionName(expressionName);
    }

    @Override
    public LetLocalDef transform(LetLocalDef oldLocalLetDef) {
        LetLocalDef def = BaseDefaultTransformer.super.transform(oldLocalLetDef);
        if (this.names.add(def.getName())) {
            return def;
        }
        SimpleName newName = this.nameGen.genSimpleName();
        this.nameMapping.put(oldLocalLetDef.getName(), newName);
        this.names.add(newName);
        return new LetLocalDef(oldLocalLetDef.getPosition(), newName, oldLocalLetDef.getTypeName().orElse(null), oldLocalLetDef.getInitializer().orElse(null));
    }

}
