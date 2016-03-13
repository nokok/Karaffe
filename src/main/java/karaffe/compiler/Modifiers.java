package karaffe.compiler;

import org.objectweb.asm.Opcodes;

class Modifiers {

    public static final Modifier PUBLIC = new Modifier(Opcodes.ACC_PUBLIC);
    public static final Modifier PRIVATE = new Modifier(Opcodes.ACC_PRIVATE);
    public static final Modifier PROTECTED = new Modifier(Opcodes.ACC_PROTECTED);
    public static final Modifier ABSTRACT = new Modifier(Opcodes.ACC_ABSTRACT);
    public static final Modifier FINAL = new Modifier(Opcodes.ACC_FINAL);
    public static final Modifier STATIC = new Modifier(Opcodes.ACC_STATIC);
    public static final Modifier CONST = new Modifier(Modifier.SPECIAL_CONST, Opcodes.ACC_PUBLIC + Opcodes.ACC_FINAL + Opcodes.ACC_STATIC);

}
