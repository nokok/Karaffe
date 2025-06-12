package org.karaffe.unittests;

import org.karaffe.compiler.util.args.Flag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.MissingResourceException; // Though not strictly needed for assertDoesNotThrow for any exception
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class FlagTest {

    @ParameterizedTest(name = "testResource {index} => flag={0}")
    @MethodSource("flagProvider")
    void testResourceDescriptionExists(Flag flag) {
        assertDoesNotThrow(flag::getDescription);
    }

    static Stream<Flag> flagProvider() {
        return Arrays.stream(Flag.values());
    }
}
