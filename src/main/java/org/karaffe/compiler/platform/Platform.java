package org.karaffe.compiler.platform;

public final class Platform {
    public static boolean isWindows() {
        return System.lineSeparator().equals("\r\n");
    }

    public static boolean hasModuleSystem() {
        Class<?> clazz = Class.class;
        try {
            clazz.getMethod("getModule");
            return true;
        } catch (NoSuchMethodException e) {
            return false;
        }
    }
}
