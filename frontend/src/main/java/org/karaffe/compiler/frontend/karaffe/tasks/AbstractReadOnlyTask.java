package org.karaffe.compiler.frontend.karaffe.tasks;

import org.karaffe.compiler.base.task.AbstractTask;

public abstract class AbstractReadOnlyTask extends AbstractTask {

    @Override
    public boolean changed() {
        return false;
    }
}
