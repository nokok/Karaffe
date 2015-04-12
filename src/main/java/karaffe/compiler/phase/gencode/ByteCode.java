/**
 * Karaffe Programming Language
 */
package karaffe.compiler.phase.gencode;

public class ByteCode {

    private final byte[] bytecode;
    private final String fileName;

    public ByteCode(byte[] bytecode, String fileName) {
        this.bytecode = bytecode;
        this.fileName = fileName;
    }

    public String fileName() {
        return fileName;
    }

    public byte[] get() {
        return bytecode;
    }
}
