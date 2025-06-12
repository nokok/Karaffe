package org.karaffe.unittests;

import org.karaffe.compiler.util.report.ReportCode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.MissingResourceException; // For clarity, though not strictly needed for this assertDoesNotThrow
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class ResourceTest {

    @ParameterizedTest(name = "propertiesTest {index} => code={0}")
    @MethodSource("reportCodeProvider")
    void propertiesTest(ReportCode code) {
        assertDoesNotThrow(code::toReportHeader);
    }

    static Stream<ReportCode> reportCodeProvider() {
        return Arrays.stream(ReportCode.values());
    }
}
