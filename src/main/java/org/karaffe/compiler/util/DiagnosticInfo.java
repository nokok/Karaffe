package org.karaffe.compiler.util;

public enum DiagnosticInfo {

    INSTANCE,;

    public String javaVersion() {
        return System.getProperty("java.version");
    }

    public String vendor() {
        return System.getProperty("java.vendor");
    }

    public String vmSpecVersion() {
        return System.getProperty("java.vm.specification.version");
    }

    public String osName() {
        return System.getProperty("os.name");
    }

    public String osArch() {
        return System.getProperty("os.arch");
    }

    public String osVersion() {
        return System.getProperty("os.version");
    }

    public String fileEncoding() {
        return System.getProperty("file.encoding");
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Java Version : ").append(this.javaVersion()).append(System.lineSeparator());
        builder.append("Vendor       : ").append(this.vendor()).append(System.lineSeparator());
        builder.append("VMSpec       : ").append(this.vmSpecVersion()).append(System.lineSeparator());
        builder.append("OSName       : ").append(this.osName()).append(System.lineSeparator());
        builder.append("OSArch       : ").append(this.osArch()).append(System.lineSeparator());
        builder.append("OSVersion    : ").append(this.osVersion()).append(System.lineSeparator());
        builder.append("Encoding     : ").append(this.fileEncoding()).append(System.lineSeparator());
        return builder.toString();
    }
}
