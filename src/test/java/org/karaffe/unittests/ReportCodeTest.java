package org.karaffe.unittests;

import org.karaffe.compiler.util.report.ReportCode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReportCodeTest {

    @ParameterizedTest(name = "testReportCodeProperties {index} => {0}")
    @MethodSource("reportCodeDataSource")
    void testReportCodeProperties(ReportCode source, int varCount, boolean requireBody, boolean requirePosition) {
        assertEquals((varCount != 0), source.isRequireVariable());
        assertEquals(varCount, source.getVarCount());
        assertEquals(requireBody, source.isRequireBody());
        assertEquals(requirePosition, source.isRequirePosition());
    }

    static Stream<Arguments> reportCodeDataSource() {
        return Stream.of(
                Arguments.of(ReportCode.ERR_FRONTEND_SYNTAX, 0, false, true),
                Arguments.of(ReportCode.INFO_COMPILER_INTERNAL_VERSION, 1, false, false)
        );
    }
}
