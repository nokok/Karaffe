package org.karaffe.compiler.base.util;

import java.io.File;

public class Platform {

    public static boolean isWindows() {
        return File.separatorChar == '\\';
    }
}
