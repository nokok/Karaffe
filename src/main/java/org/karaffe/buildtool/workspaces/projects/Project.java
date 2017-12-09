package org.karaffe.buildtool.workspaces.projects;

import java.nio.file.Path;

public interface Project {
    public ProjectType getType();

    public String getName();

    public Path rootDir();

    public Path sourceDir();

    public Path outputDir();

}
