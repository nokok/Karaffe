package org.karaffe.compiler.frontend.karaffe.transformer;

import org.antlr.v4.runtime.CommonTokenStream;
import org.karaffe.compiler.base.util.config.Options;
import org.karaffe.compiler.frontend.karaffe.ast.api.Tree;
import org.karaffe.compiler.frontend.karaffe.transformer.util.PhaseResult;

public class Phases implements Phase<String, Void> {

    private final Options options;

    public Phases(Options options) {
        this.options = options;
    }

    private final AbstractFileTransformer<CommonTokenStream> fileTransformer = null;
    private final AbstractTokenStreamTransformer<Tree> tokenStreamTransformer = null;
    private final AbstractASTTransformer<org.karaffe.compiler.ir.Tree> astTransformer = null;
    private final AbstractMIRTransformer<org.karaffe.compiler.ir.Tree> mirTransformer = null;

    @Override
    public String phaseName() {
        return "";
    }

    @Override
    public String phaseDescription() {
        return "";
    }

    @Override
    public PhaseResult<Void> run(String input) {
        return null;
    }

    @Override
    public boolean changed() {
        return false;
    }
}
