package org.karaffe.unittests;

// import org.karaffe.compiler.backend.jvm.BytecodeSelectorForNumber;
// import org.objectweb.asm.Opcodes;
// import org.junit.jupiter.params.ParameterizedTest;
// import org.junit.jupiter.params.provider.Arguments;
// import org.junit.jupiter.params.provider.MethodSource;
//
// import java.util.stream.Stream;
//
// import static org.junit.jupiter.api.Assertions.assertEquals;

class ByteCodeSelectorTest {

    // @ParameterizedTest(name = "iconst {index} => value={0}")
    // @MethodSource("iconstTestData")
    // void testIconstSelection(int value, int expectedOpcode) {
    //     assertEquals(expectedOpcode, BytecodeSelectorForNumber.fromInt(value).getOpcode());
    // }

    // static Stream<Arguments> iconstTestData() {
    //     return Stream.of(
    //             Arguments.of(-1, Opcodes.ICONST_M1),
    //             Arguments.of(0, Opcodes.ICONST_0),
    //             Arguments.of(1, Opcodes.ICONST_1),
    //             Arguments.of(2, Opcodes.ICONST_2),
    //             Arguments.of(3, Opcodes.ICONST_3),
    //             Arguments.of(4, Opcodes.ICONST_4),
    //             Arguments.of(5, Opcodes.ICONST_5),
    //             Arguments.of(6, Opcodes.BIPUSH),
    //             Arguments.of(-2, Opcodes.BIPUSH),
    //             Arguments.of(-127, Opcodes.BIPUSH),
    //             Arguments.of(127, Opcodes.BIPUSH),
    //             Arguments.of(128, Opcodes.SIPUSH),
    //             Arguments.of(129, Opcodes.SIPUSH),
    //             Arguments.of(32767, Opcodes.SIPUSH),
    //             Arguments.of(-32768, Opcodes.SIPUSH),
    //             Arguments.of(-32769, Opcodes.LDC),
    //             Arguments.of(32768, Opcodes.LDC)
    //     );
    // }
}
