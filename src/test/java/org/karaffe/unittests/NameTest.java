package org.karaffe.unittests;

import org.karaffe.compiler.util.ClassNameValidator;
import org.karaffe.compiler.util.NameValidationResult;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.karaffe.compiler.util.NameValidationResult.*;

class NameTest {

    @ParameterizedTest(name = "classNameValidationTest {index} => name={0}")
    @MethodSource("classNameValidationDataSource")
    void classNameValidationTest(String name, NameValidationResult expectedResult) {
        ClassNameValidator validator = new ClassNameValidator();
        assertEquals(expectedResult, validator.validate(name));
    }

    static Stream<Arguments> classNameValidationDataSource() {
        return Stream.of(
                Arguments.of("I", OK),
                Arguments.of("Main", OK),
                Arguments.of("i", WARN_CAMEL_CASE),
                Arguments.of(null, ERR_NULL),
                Arguments.of("", ERR_EMPTY_NAME),
                Arguments.of("👮", ERR_INVALID_JAVA_IDENTIFIER), // Unicode emoji
                Arguments.of("+", ERR_INVALID_JAVA_IDENTIFIER),
                Arguments.of("_", ERR_LAMBDA_KEYWORD)
        );
    }
}
