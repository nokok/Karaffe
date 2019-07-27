package org.karaffe.unittests

import org.karaffe.compiler.backend.jvm.BytecodeSelectorForNumber
import org.objectweb.asm.Opcodes
import spock.lang.Specification
import spock.lang.Unroll

class ByteCodeSelectorSpec extends Specification {

  @Unroll
  def "iconst #value"() {
    expect:
    BytecodeSelectorForNumber.fromInt(value).opcode == opcodes

    where:
    value  || opcodes
    -1     || Opcodes.ICONST_M1
    0      || Opcodes.ICONST_0
    1      || Opcodes.ICONST_1
    2      || Opcodes.ICONST_2
    3      || Opcodes.ICONST_3
    4      || Opcodes.ICONST_4
    5      || Opcodes.ICONST_5
    6      || Opcodes.BIPUSH
    -2     || Opcodes.BIPUSH
    -127   || Opcodes.BIPUSH
    127    || Opcodes.BIPUSH
    128    || Opcodes.SIPUSH
    129    || Opcodes.SIPUSH
    32767  || Opcodes.SIPUSH
    -32768 || Opcodes.SIPUSH
    -32769 || Opcodes.LDC
    32768  || Opcodes.LDC
  }

}
