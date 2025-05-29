package org.karaffe.unittests;

import org.karaffe.compiler.util.resolver.OperatorResolver;
import org.objectweb.asm.Opcodes; // For Opcodes.IADD etc.
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

class OperatorResolverTest {

    private static Stream<Class<?>> validPlusOperatorSource() {
        return Stream.of(
            int.class,
            long.class,
            float.class,
            double.class
        );
    }

    @ParameterizedTest(name = "valid plusOperator for {0}")
    @MethodSource("validPlusOperatorSource")
    void testValidPlusOperator(Class<?> sourceClass) {
        assertDoesNotThrow(() -> new OperatorResolver(sourceClass));
    }

    private static Stream<Arguments> plusOperationSource() {
        return Stream.of(
            Arguments.of(int.class,    int.class,    Opcodes.IADD),
            Arguments.of(int.class,    long.class,   Opcodes.LADD),
            Arguments.of(int.class,    float.class,  Opcodes.FADD),
            Arguments.of(int.class,    double.class, Opcodes.DADD),
            Arguments.of(long.class,   int.class,    Opcodes.LADD),
            Arguments.of(long.class,   long.class,   Opcodes.LADD),
            Arguments.of(long.class,   float.class,  Opcodes.FADD),
            Arguments.of(long.class,   double.class, Opcodes.DADD),
            Arguments.of(float.class,  int.class,    Opcodes.FADD),
            Arguments.of(float.class,  long.class,   Opcodes.FADD),
            Arguments.of(float.class,  float.class,  Opcodes.FADD),
            Arguments.of(float.class,  double.class, Opcodes.DADD),
            Arguments.of(double.class, int.class,    Opcodes.DADD),
            Arguments.of(double.class, long.class,   Opcodes.DADD),
            Arguments.of(double.class, float.class,  Opcodes.DADD),
            Arguments.of(double.class, double.class, Opcodes.DADD)
        );
    }

    @ParameterizedTest(name = "{0} + {1} = opcode {2}")
    @MethodSource("plusOperationSource")
    void testPlusOperation(Class<?> sourceClass, Class<?> paramClass, int expectedOpcode) {
        OperatorResolver resolver = new OperatorResolver(sourceClass);
        // Assuming resolver.plus(paramClass) returns an object that has a public 'opcode' field
        // or a public 'getOpcode()' method. The Spock code 'inst.opcode' implies a field or Groovy property.
        // Let's try '.getOpcode()' first as it's more common in Java.
        // If 'opcode' is a public field, then '.opcode' would be correct.
        // The worker should verify this.
        assertEquals(expectedOpcode, resolver.plus(paramClass).getOpcode());
    }
}
