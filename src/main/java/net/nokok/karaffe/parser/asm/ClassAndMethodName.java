/**
 * Karaffe Programming Language
 */
package net.nokok.karaffe.parser.asm;

public class ClassAndMethodName {

    private final String className;
    private final String methodName;
    private final String descriptor;

    public ClassAndMethodName(String className, String methodName, String descriptor) {
        this.className = className;
        this.methodName = methodName;
        this.descriptor = descriptor;
    }

    public String getClassName() {
        return className;
    }

    public String getMethodName() {
        return methodName;
    }

    public String getDescriptor() {
        return descriptor;
    }

}
