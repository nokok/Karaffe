package org.karaffe.compiler.base.mir;

public enum InstructionType {
    STORE,
    CAST,
    BINDING,
    LOAD,
    INVOKE,
    VALDEF,
    CONST,
    IFJUMPFALSE,
    JUMP,
    RETURN,
    JUMPTARGET,
    BEGINCLASS,
    BEGINMETHOD,
    BEGINBLOCK,
    ENDCLASS,
    ENDMETHOD,
    ENDBLOCK,
    BEGINCONSTRUCTOR,
    ENDCONSTRUCTOR,
    CONSTINT,
    CONSTSTRING,
    INVOKESPECIAL,
    NAMERULE,
    IFJUMPTRUE
}
