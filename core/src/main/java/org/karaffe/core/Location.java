package org.karaffe.core;

import java.nio.file.Path;

public class Location {
    private final Path path;
    private final Pos position;

    public Location(Path path, Pos position) {
        this.path = path;
        this.position = position;
    }

    @Override
    public String toString() {
        return this.path.toAbsolutePath() + ":" + this.position;
    }
}
