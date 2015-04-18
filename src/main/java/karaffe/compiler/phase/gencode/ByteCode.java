/**
 * Karaffe Programming Language
 */
package karaffe.compiler.phase.gencode;

public class ByteCode {

    private final byte[] bytecode;
    private final String fileName;
    private final String packagePrefix;

    public ByteCode(byte[] bytecode, String fileName, String packagePrefix) {
        this.bytecode = bytecode;
        this.fileName = fileName;
        this.packagePrefix = packagePrefix;
    }

    public String fileName() {
        return fileName;
    }

    public byte[] get() {
        return bytecode;
    }

    public String packagePrefix() {
        return packagePrefix;
    }
}
