package org.karaffe.compiler.transformer.namer;

import org.karaffe.compiler.ast.api.Statement;
import org.karaffe.compiler.ast.expressions.Block;
import org.karaffe.compiler.ast.expressions.ExpressionName;
import org.karaffe.compiler.ast.names.SimpleName;
import org.karaffe.compiler.ast.statements.LetLocalDef;
import org.karaffe.compiler.base.generator.ConsecutiveNumberGenerator;
import org.karaffe.compiler.transformer.AbstractTransformer;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class AlphaEquivalenceTransformer extends AbstractTransformer {

    public AlphaEquivalenceTransformer() {
        super("alpha-equivalence");
    }

    private final ConsecutiveNumberGenerator nameGen = new ConsecutiveNumberGenerator("s_");
    private final Set<SimpleName> names = new HashSet<>();
    private final Map<SimpleName, SimpleName> nameMapping = new HashMap<>();

    @Override
    public SimpleName transformBody(SimpleName simpleName) {
        if (this.nameMapping.containsKey(simpleName)) {
            return new SimpleName(this.nameMapping.get(simpleName));
        }
        return new SimpleName(simpleName);
    }

    @Override
    public ExpressionName transformBody(ExpressionName expressionName) {
        if (this.nameMapping.containsKey(expressionName)) {
            return new ExpressionName(this.nameMapping.get(expressionName));
        }
        return new ExpressionName(expressionName);
    }

    @Override
    public Block transformBody(Block oldBlock) {
        Block block = new Block();
        for (Statement stmt : oldBlock.getBody()) {
            block.add(transform(stmt));
        }
        return block;
    }

    @Override
    public LetLocalDef transformBody(LetLocalDef oldLocalLetDef) {
        if (this.names.add(oldLocalLetDef.getName())) {
            return new LetLocalDef(oldLocalLetDef.getPosition(), oldLocalLetDef.getName(), oldLocalLetDef.getTypeName().map(this::transform).orElse(null), oldLocalLetDef.getInitializer().map(this::transform).orElse(null));
        }
        SimpleName newName = new SimpleName(this.nameGen.generate());
        this.nameMapping.put(oldLocalLetDef.getName(), newName);
        this.names.add(newName);
        return new LetLocalDef(oldLocalLetDef.getPosition(), newName, oldLocalLetDef.getTypeName().map(this::transform).orElse(null), oldLocalLetDef.getInitializer().map(this::transform).orElse(null));
    }

}
