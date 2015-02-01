/**
 * Karaffe Programming Language
 */
package net.nokok.karaffe.parser.asm.nodes;

import java.util.Map;
import static net.nokok.karaffe.parser.syntax.KaraffeParserSyntaxTest.testCode;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class ImportCollectorTest {

    @Test
    public void testImports() {
        testCode("import java.lang.String java.lang.Integer").dump("");
        ImportCollector collector = new ImportCollector(testCode("import java.lang.String java.lang.Integer"));
        Map<String, Class<?>> map = collector.getImports();
        assertThat(map.get("String").getName(), is(String.class.getName()));
        assertThat(map.get("Integer").getName(), is(Integer.class.getName()));
    }
}
