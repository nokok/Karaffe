package org.karaffe.unittests;

import org.karaffe.compiler.util.args.ArgsParser;
import org.karaffe.compiler.util.args.Options; // Assuming this is the type of 'opt'
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ArgTest {

    static Stream<Arguments> testParseSource() {
        return Stream.of(
            Arguments.of((Object) new String[]{}, "Options{}"),
            Arguments.of((Object) new String[]{"--help"}, "Options{--help}"),
            Arguments.of((Object) new String[]{"-h"}, "Options{--help}"),
            Arguments.of((Object) new String[]{"--version"}, "Options{--version}"),
            Arguments.of((Object) new String[]{"-g"}, "Options{-g}"),
            Arguments.of((Object) new String[]{"-v"}, "Options{-v}"),
            Arguments.of((Object) new String[]{"src/test/resources/Main.krf"}, "Options{src/test/resources/Main.krf}"),
            Arguments.of((Object) new String[]{"-version"}, "InvalidOptions{-version}"),
            Arguments.of((Object) new String[]{"-help"}, "InvalidOptions{-help}"),
            Arguments.of((Object) new String[]{"Main.krf"}, "FileNotFound{Main.krf}")
        );
    }

    @ParameterizedTest
    @MethodSource("testParseSource")
    void testParse(String[] input, String expectedOutput) {
        // setup:
        ArgsParser parser = new ArgsParser();
        Options opt = parser.parse(input); // Ensure Options is the correct type

        // expect:
        assertEquals(expectedOutput, opt.toString());
    }

    @Test
    void isEmpty() {
        // setup:
        ArgsParser parser = new ArgsParser();
        Options opt = parser.parse(new String[]{}); // Ensure Options is the correct type

        // expect:
        assertTrue(opt.isEmpty());
    }
}
