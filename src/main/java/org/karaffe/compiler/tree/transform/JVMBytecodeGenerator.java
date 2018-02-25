package org.karaffe.compiler.tree.transform;

import org.karaffe.compiler.tree.transform.api.BaseDefaultTransformer;

public class JVMBytecodeGenerator implements BaseDefaultTransformer {

    public static final String name = "jvm";

    @Override
    public String getTransformerName() {
        return JVMBytecodeGenerator.name;
    }
}
