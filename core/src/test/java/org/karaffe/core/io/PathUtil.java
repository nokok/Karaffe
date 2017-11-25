package org.karaffe.core.io;

import java.nio.file.Path;
import java.nio.file.Paths;

public final class PathUtil {
    public static Path testResourcesDir() {
        return Paths.get("src", "test", "resources");
    }
}
