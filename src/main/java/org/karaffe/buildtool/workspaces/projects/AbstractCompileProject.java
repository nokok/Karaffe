package org.karaffe.buildtool.workspaces.projects;

public abstract class AbstractCompileProject implements Project {

    private final String name;

    public AbstractCompileProject(final String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
