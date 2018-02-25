package org.karaffe.compiler.tree.transform;

import org.karaffe.compiler.tree.transform.api.BaseDefaultTransformer;
import org.karaffe.compiler.util.NameGen;

public class Scoper implements BaseDefaultTransformer {
    public static final String name = "scoper";
    private final NameGen nameGen = new NameGen("s_");

    @Override
    public String getTransformerName() {
        return Scoper.name;
    }

}
