package org.karaffe.compiler.frontend.karaffe.phase;

import org.antlr.v4.runtime.CommonTokenStream;
import org.karaffe.compiler.base.util.config.Options;
import org.karaffe.compiler.frontend.karaffe.ast.api.Tree;
import org.karaffe.compiler.frontend.karaffe.phase.ast.AbstractASTPhase;
import org.karaffe.compiler.frontend.karaffe.phase.file.AbstractFilePhase;
import org.karaffe.compiler.frontend.karaffe.phase.mir.AbstractMIRPhase;
import org.karaffe.compiler.frontend.karaffe.phase.tokenstream.AbstractTokenStreamPhase;
import org.karaffe.compiler.frontend.karaffe.transformer.util.PhaseResult;

import java.io.File;

public class Phases implements Phase<File, org.karaffe.compiler.ir.Tree> {

    private final Options options;

    public Phases(Options options) {
        this.options = options;
    }

    private final Phase<Options, ?> compilerPreConditions = new CompilerPreConditionChecker();
    private final AbstractFilePhase<CommonTokenStream> fileTransformer = null;
    private final AbstractTokenStreamPhase<Tree> tokenStreamTransformer = null;
    private final AbstractASTPhase<org.karaffe.compiler.ir.Tree> astTransformer = null;
    private final AbstractMIRPhase<org.karaffe.compiler.ir.Tree> mirTransformer = null;

    @Override
    public String phaseName() {
        return "";
    }

    @Override
    public String phaseDescription() {
        return "";
    }

    @Override
    public PhaseResult<org.karaffe.compiler.ir.Tree> run(File input) {
        this.compilerPreConditions.run(this.options);

        PhaseResult<CommonTokenStream> result = this.fileTransformer.run(input);
        CommonTokenStream tokenStream = result.getOrThrow();
        PhaseResult<Tree> treeResult = tokenStreamTransformer.run(tokenStream);
        Tree tree = treeResult.getOrThrow();
        PhaseResult<org.karaffe.compiler.ir.Tree> mirResult = astTransformer.run(tree);
        org.karaffe.compiler.ir.Tree mir = mirResult.getOrThrow();
        PhaseResult<org.karaffe.compiler.ir.Tree> transformedMir = mirTransformer.run(mir);
        return PhaseResult.onSuccess(transformedMir.getOrThrow());
    }

    @Override
    public boolean changed() {
        return false;
    }

}
