package org.karaffe.buildtool.workspaces.projects;

import java.nio.file.Path;

public class KaraffeProject extends AbstractCompileProject {
    public KaraffeProject(String name) {
        super(name);
    }

    @Override
    public ProjectType getType() {
        return ProjectType.KARAFFE;
    }

    @Override
    public Path rootDir() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Path sourceDir() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Path outputDir() {
        // TODO Auto-generated method stub
        return null;
    }
}
