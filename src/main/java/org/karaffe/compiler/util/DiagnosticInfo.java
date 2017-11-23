package org.karaffe.compiler.util;

public enum DiagnosticInfo {

    INSTANCE,;

    public String javaVersion() {
        return System.getenv("java.version");
    }

    public String vendor() {
        return System.getenv("java.vendor");
    }

    public String vmSpecVersion() {
        return System.getenv("java.vm.specification.version");
    }

    public String osName() {
        return System.getenv("os.name");
    }

    public String osArch() {
        return System.getenv("os.arch");
    }

    public String osVersion() {
        return System.getenv("os.version");
    }

    public String fileEncoding() {
        return System.getenv("file.encoding");
    }
}
