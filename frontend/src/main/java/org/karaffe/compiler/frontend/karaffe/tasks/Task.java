package org.karaffe.compiler.frontend.karaffe.tasks;

import org.karaffe.compiler.base.CompilerContext;
import org.karaffe.compiler.frontend.karaffe.transformer.util.Result;

public interface Task {

    String name();

    String description();

    Result run(CompilerContext context);

    boolean isRunnable(CompilerContext context);

    /**
     * CompilerContextに対して変更を加えた場合はtrueを返します。
     *
     * @return
     */
    boolean changed();

}
