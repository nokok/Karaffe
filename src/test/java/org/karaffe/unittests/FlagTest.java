package org.karaffe.unittests;

import org.karaffe.compiler.util.args.Flag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

// It's possible MissingResourceException is a custom exception.
// If it's standard, this import might be java.util.MissingResourceException.
// For now, let's assume it's not needed for assertDoesNotThrow if it's a generic Exception.
// import java.util.MissingResourceException; 

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class FlagTest {

    @ParameterizedTest(name = "testResource {0}")
    @EnumSource(Flag.class)
    void testResource(Flag flag) {
        // when:
        // then:
        assertDoesNotThrow(
            () -> flag.getDescription(),
            "Getting description for " + flag.name() + " should not throw MissingResourceException"
        );
    }
}
