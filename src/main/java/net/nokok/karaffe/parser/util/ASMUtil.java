/**
 *
 * Karaffe Programming Language
 *   __ _____   ___  ___   ____________
 *   / //_/ _ | / _ \/ _ | / __/ __/ __/
 *  / , \/ __ |/ , _/ __ |/ _// _// _/
 * /_/|_/_/ |_/_/|_/_/ |_/_/ /_/ /___/
 *
 */
package net.nokok.karaffe.parser.util;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;

public class ASMUtil {

    public static final String OBJECT_CLASS = "java/lang/Object";
    public static final String DEFAULT_CONSTRUCTOR_NAME = "<init>";
    public static final String RETURN_VOID = "()V";

    public static ClassWriter createPublicClass(String className, String baseName) {
        ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_MAXS);
        classWriter.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC + Opcodes.ACC_SUPER, className, null, baseName, null);
        return classWriter;
    }
}
