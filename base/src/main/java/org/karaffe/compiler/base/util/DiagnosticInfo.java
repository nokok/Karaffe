package org.karaffe.compiler.base.util;

public enum DiagnosticInfo {

    INSTANCE,;

    public String fileEncoding() {
        return System.getProperty("file.encoding");
    }

    public String javaVersion() {
        return System.getProperty("java.version");
    }

    public String osArch() {
        return System.getProperty("os.arch");
    }

    public String osName() {
        return System.getProperty("os.name");
    }

    public String osVersion() {
        return System.getProperty("os.version");
    }

    public String javaHome() {
        return System.getenv("JAVA_HOME");
    }

    public String karaffeHome() {
        return System.getenv("KARAFFE_HOME");
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("KARAFFE_HOME : ").append(this.karaffeHome()).append(System.lineSeparator());
        builder.append("JAVA_HOME    : ").append(this.javaHome()).append(System.lineSeparator());
        builder.append("Java Version : ").append(this.javaVersion()).append(System.lineSeparator());
        builder.append("Vendor       : ").append(this.vendor()).append(System.lineSeparator());
        builder.append("VMSpec       : ").append(this.vmSpecVersion()).append(System.lineSeparator());
        builder.append("OSName       : ").append(this.osName()).append(System.lineSeparator());
        builder.append("OSArch       : ").append(this.osArch()).append(System.lineSeparator());
        builder.append("OSVersion    : ").append(this.osVersion()).append(System.lineSeparator());
        builder.append("Encoding     : ").append(this.fileEncoding()).append(System.lineSeparator());
        return builder.toString();
    }

    public String vendor() {
        return System.getProperty("java.vendor");
    }

    public String vmSpecVersion() {
        return System.getProperty("java.vm.specification.version");
    }
}
