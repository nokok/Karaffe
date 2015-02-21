/**
 * Karaffe Programming Language
 */
package net.nokok.karaffe.parser.asm.nodes;

import net.nokok.karaffe.parser.asm.nodes.collectors.ImportCollector;
import static net.nokok.karaffe.parser.syntax.KaraffeParserSyntaxTest.testCode;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Map;

import org.junit.Test;

public class ImportCollectorTest {

    @Test
    public void testImports() {
        ImportCollector collector = new ImportCollector(testCode(
                "import java.lang.String java.lang.Integer\n"
                + "\n"
                + "type Hoge{\n"
                + "def a java.lang.Integer = 0\n"
                + "def a Integer = 0\n"
                + "}"
                ));
        Map<String, Class<?>> map = collector.getImports();
        assertThat(map.size(), is(2));
        assertThat(map.get("String").getName(), is(String.class.getName()));
        assertThat(map.get("Integer").getName(), is(Integer.class.getName()));
    }
}
