package org.karaffe.unittests;

import org.karaffe.compiler.util.args.ArgsParser;
import org.karaffe.compiler.util.args.Options;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ArgTest {

    @ParameterizedTest(name = "testParse {index} => input={0}")
    @MethodSource("argTestData")
    void testParse(String[] input, String expectedOutput) {
        ArgsParser parser = new ArgsParser();
        Options opt = parser.parse(input);

        assertEquals(expectedOutput, opt.toString());
    }

    static Stream<Arguments> argTestData() {
        return Stream.of(
                Arguments.of(new String[]{}, "Options{}"),
                Arguments.of(new String[]{"--help"}, "Options{--help}"),
                Arguments.of(new String[]{"-h"}, "Options{--help}"),
                Arguments.of(new String[]{"--version"}, "Options{--version}"),
                Arguments.of(new String[]{"-g"}, "Options{-g}"),
                Arguments.of(new String[]{"-v"}, "Options{-v}"),
                Arguments.of(new String[]{"src/test/resources/Main.krf"}, "Options{src/test/resources/Main.krf}"),
                Arguments.of(new String[]{"-version"}, "InvalidOptions{-version}"),
                Arguments.of(new String[]{"-help"}, "InvalidOptions{-help}"),
                Arguments.of(new String[]{"Main.krf"}, "FileNotFound{Main.krf}")
        );
    }

    @Test
    void isEmpty() {
        ArgsParser parser = new ArgsParser();
        Options opt = parser.parse(new String[]{});

        assertTrue(opt.isEmpty());
    }
}
