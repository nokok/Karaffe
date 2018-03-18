package org.karaffe.buildtool.workspaces.projects;

import java.nio.file.Path;

public interface Project {
    public String getName();

    public ProjectType getType();

    public Path outputDir();

    public Path rootDir();

    public Path sourceDir();

}
