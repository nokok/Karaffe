package org.karaffe;

import java.io.File;

public class Platform {

    public static boolean isWindows() {
        return File.separatorChar == '\\';
    }
}
