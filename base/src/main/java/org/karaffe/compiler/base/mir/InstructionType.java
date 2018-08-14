package org.karaffe.compiler.base.mir;

public enum InstructionType {
    BEGINBLOCK,
    BEGINCLASS,
    BEGINCONSTRUCTOR,
    BEGINMETHOD,
    BINDING,
    CAST,
    CONSTINT,
    CONSTSTRING,
    ENDBLOCK,
    ENDCLASS,
    ENDCONSTRUCTOR,
    ENDMETHOD,
    IFJUMPFALSE,
    IFJUMPTRUE,
    IMPORT,
    INVOKE,
    JUMP,
    JUMPTARGET,
    LOAD,
    RETURN,
    STORE,
    INVOKEMETHOD,
    VALDEF
}
