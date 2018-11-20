package org.karaffe.unittests

import net.nokok.azm.Opcodes
import org.karaffe.compiler.BytecodeSelectorForNumber
import spock.lang.Specification
import spock.lang.Unroll

class ByteCodeSelectorSpec extends Specification {

    @Unroll
    def "iconst #value"() {
        expect:
        BytecodeSelectorForNumber.fromInt(value).opcode == opcodes

        where:
        value || opcodes
        -1    || Opcodes.ICONST_M1
        0     || Opcodes.ICONST_0
        1     || Opcodes.ICONST_1
        2     || Opcodes.ICONST_2
        3     || Opcodes.ICONST_3
        4     || Opcodes.ICONST_4
        5     || Opcodes.ICONST_5
    }

    @Unroll
    def "byte #value"() {
        expect:
        BytecodeSelectorForNumber.fromInt(value).opcode == opcodes

        where:
        value || opcodes
        6     || Opcodes.BIPUSH
        -2    || Opcodes.BIPUSH
        -127  || Opcodes.BIPUSH
        127   || Opcodes.BIPUSH
    }

    @Unroll
    def "short #value"() {
        expect:
        BytecodeSelectorForNumber.fromInt(value).opcode == opcodes

        where:
        value  || opcodes
        128    || Opcodes.SIPUSH
        129    || Opcodes.SIPUSH
        32767  || Opcodes.SIPUSH
        -32768 || Opcodes.SIPUSH
    }

    @Unroll
    def "int #value"() {
        expect:
        BytecodeSelectorForNumber.fromInt(value).opcode == opcodes

        where:
        value             || opcodes
        -32769            || Opcodes.LDC
        32768             || Opcodes.LDC
    }

}
