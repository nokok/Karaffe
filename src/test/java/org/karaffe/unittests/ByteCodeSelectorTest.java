package org.karaffe.unittests;

import org.karaffe.compiler.backend.jvm.BytecodeSelectorForNumber;
import org.objectweb.asm.Opcodes;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ByteCodeSelectorTest {

    @ParameterizedTest(name = "iconst {0}")
    @CsvSource({
        "-1,     " + Opcodes.ICONST_M1,
        "0,      " + Opcodes.ICONST_0,
        "1,      " + Opcodes.ICONST_1,
        "2,      " + Opcodes.ICONST_2,
        "3,      " + Opcodes.ICONST_3,
        "4,      " + Opcodes.ICONST_4,
        "5,      " + Opcodes.ICONST_5,
        "6,      " + Opcodes.BIPUSH,
        "-2,     " + Opcodes.BIPUSH,
        "-127,   " + Opcodes.BIPUSH,
        "127,    " + Opcodes.BIPUSH,
        "128,    " + Opcodes.SIPUSH,
        "129,    " + Opcodes.SIPUSH,
        "32767,  " + Opcodes.SIPUSH,
        "-32768, " + Opcodes.SIPUSH,
        "-32769, " + Opcodes.LDC,
        "32768,  " + Opcodes.LDC
    })
    void testIconst(int value, int expectedOpcode) {
        assertEquals(expectedOpcode, BytecodeSelectorForNumber.fromInt(value).getOpcode());
    }
}
