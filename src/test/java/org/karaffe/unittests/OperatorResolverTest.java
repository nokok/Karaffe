package org.karaffe.unittests;

import org.karaffe.compiler.util.resolver.OperatorResolver;
import org.objectweb.asm.Opcodes;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

class OperatorResolverTest {

    @ParameterizedTest(name = "validPlusOperatorSourceTypeTest {index} => source={0}")
    @MethodSource("validPlusOperatorSourceTypeProvider")
    void validPlusOperatorSourceTypeTest(Class<?> sourceClass) {
        assertDoesNotThrow(() -> new OperatorResolver(sourceClass));
    }

    static Stream<Class<?>> validPlusOperatorSourceTypeProvider() {
        return Stream.of(
                int.class,
                long.class,
                float.class,
                double.class
        );
    }

    @ParameterizedTest(name = "plusOperationTest {index} => {0} + {1} = {2}")
    @MethodSource("plusOperationDataSource")
    void plusOperationTest(Class<?> sourceClass, Class<?> paramClass, int expectedOpcode) {
        OperatorResolver resolver = new OperatorResolver(sourceClass);
        // Assuming resolver.plus(paramClass) returns an object with getOpcode() or a public opcode field.
        // This is based on similar structure in ByteCodeSelectorSpec.
        // If OpCode is the type: OpCode inst = resolver.plus(paramClass);
        // If it's an Instruction or similar: Instruction inst = resolver.plus(paramClass);
        // For now, let's assume it returns something that has an 'opcode' field or 'getOpcode()'
        // resolver.plus(paramClass) returns AbstractInsnNode, which has getOpcode()
        org.objectweb.asm.tree.AbstractInsnNode instruction = resolver.plus(paramClass);
        assertEquals(expectedOpcode, instruction.getOpcode());
    }

    static Stream<Arguments> plusOperationDataSource() {
        return Stream.of(
                Arguments.of(int.class, int.class, Opcodes.IADD),
                Arguments.of(int.class, long.class, Opcodes.LADD),
                Arguments.of(int.class, float.class, Opcodes.FADD),
                Arguments.of(int.class, double.class, Opcodes.DADD),
                Arguments.of(long.class, int.class, Opcodes.LADD),
                Arguments.of(long.class, long.class, Opcodes.LADD),
                Arguments.of(long.class, float.class, Opcodes.FADD),
                Arguments.of(long.class, double.class, Opcodes.DADD),
                Arguments.of(float.class, int.class, Opcodes.FADD),
                Arguments.of(float.class, long.class, Opcodes.FADD),
                Arguments.of(float.class, float.class, Opcodes.FADD),
                Arguments.of(float.class, double.class, Opcodes.DADD),
                Arguments.of(double.class, int.class, Opcodes.DADD),
                Arguments.of(double.class, long.class, Opcodes.DADD),
                Arguments.of(double.class, float.class, Opcodes.DADD),
                Arguments.of(double.class, double.class, Opcodes.DADD)
        );
    }
}
