package org.karaffe.core;

import java.nio.file.Path;

public class Location {
    private final Path path;
    private final Position position;

    public Location(Path path, Position position) {
        this.path = path;
        this.position = position;
    }

    @Override
    public String toString() {
        return this.path.toAbsolutePath() + ":" + this.position;
    }
}
