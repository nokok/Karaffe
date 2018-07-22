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
    INVOKESPECIAL,
    JUMP,
    JUMPTARGET,
    LOAD,
    RETURN,
    STORE,
    VALDEF
}
