/**
 * Karaffe Programming Language
 */
package net.nokok.karaffe.parser.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.nokok.karaffe.parser.asm.ClassAlias;
import net.nokok.karaffe.parser.asm.ClassAndMethodName;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Type;

public class CurrentState {

    private String moduleName = "__dp__";
    private final List<Modifier> modifiers = new ArrayList<>();
    private List<ClassAlias> availableClasses = new ArrayList<>();
    private CompilerState compilerState = CompilerState.TOPLEVEL;
    public static final Map<String, ClassAndMethodName> functionAliases;
    public static final Map<String, Type> classLiteral;

    private ClassWriter classWriter;
    private MethodVisitor methodVisitor;

    static {
        Map<String, ClassAndMethodName> aliasTmp = new HashMap<>(1);
        aliasTmp.put("println", new ClassAndMethodName("java/lang/System", "println", "(Ljava/lang/Object;)V"));
        functionAliases = Collections.unmodifiableMap(aliasTmp);
        Map<String, Type> classLiteralTmp = new HashMap<>(5);
        classLiteralTmp.put("Int", Type.INT_TYPE);
        classLiteralTmp.put("Double", Type.DOUBLE_TYPE);
        classLiteralTmp.put("()", Type.VOID_TYPE);
        classLiteralTmp.put("Void", Type.VOID_TYPE);
        classLiteralTmp.put("Bool", Type.BOOLEAN_TYPE);
        classLiteral = Collections.unmodifiableMap(classLiteralTmp);
    }

    public CurrentState() {
        availableClasses.add(new ClassAlias("Int", "java/lang/Integer"));
        availableClasses.add(new ClassAlias("Double", "java/lang/Double"));
        availableClasses.add(new ClassAlias("String", "java/lang/String"));
        availableClasses.add(new ClassAlias("Bool", "java/lang/Boolean"));
        availableClasses.add(new ClassAlias("Num", "java/lang/Number"));
    }

    public void addModifier(Modifier modifier) {
        if (modifiers.contains(modifier)) {
            //重複している
        }
        modifiers.add(modifier);
    }

    public String getModule() {
        return moduleName;
    }

    public ClassWriter getClassWriter() {
        return classWriter;
    }

    public MethodVisitor getMethodVisitor() {
        return methodVisitor;
    }

    public void setCompileState(CompilerState state) {
        switch (state) {
            case MODIFIER:
                modifiers.clear();
                break;
            case TOPLEVEL:
                break;
            default:
        }
        this.compilerState = state;
    }

    public boolean is(CompilerState state) {
        return compilerState.equals(state);
    }

    public enum CompilerState {

        TOPLEVEL,
        MODIFIER,
    }

    public enum Modifier {

        PUBLIC,
        ABSTRACT,
        OVERRIDE,
        PRIVATE,
        SEALED,
        LAZY,
        VAR
    }
}
