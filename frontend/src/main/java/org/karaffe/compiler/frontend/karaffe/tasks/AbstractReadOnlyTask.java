package org.karaffe.compiler.frontend.karaffe.tasks;

public abstract class AbstractReadOnlyTask extends AbstractTask {

    @Override
    public boolean changed() {
        return false;
    }
}
