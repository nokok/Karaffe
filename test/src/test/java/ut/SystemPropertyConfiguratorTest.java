package ut;

import org.junit.Test;
import org.karaffe.compiler.base.util.SystemPropertyConfigurator;

import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class SystemPropertyConfiguratorTest {
    @Test
    public void testConfigurator1() {
        SystemPropertyConfigurator configurator = new SystemPropertyConfigurator(
                new String[]{
                        "-Dfile.encoding=utf8",
                        "do",
                        "some=thing",
                        "-Dss=",
                        "-Dfoo=bar=baz"
                });
        Map<String, String> map = configurator.getConfigureMap();
        assertEquals("utf8", map.get("file.encoding"));
        assertEquals("", map.get("ss"));
        assertEquals("bar=baz", map.get("foo"));
        assertNull(map.get("do"));
        assertNull(map.get("some"));

        assertNull(System.getProperty("foo"));

        configurator.updateSystemProperty();

        assertEquals("bar=baz", System.getProperty("foo"));
    }
}
