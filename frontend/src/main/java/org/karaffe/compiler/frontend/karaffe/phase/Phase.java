package org.karaffe.compiler.frontend.karaffe.phase;

import org.karaffe.compiler.base.CompilerContext;
import org.karaffe.compiler.frontend.karaffe.transformer.util.Result;

public interface Phase {

    String phaseName();

    String phaseDescription();

    Result run(CompilerContext context);

    /**
     * CompilerContextに対して変更を加えた場合はtrueを返します。
     *
     * @return
     */
    boolean changed();

}
