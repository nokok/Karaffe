package unittests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.karaffe.compiler.context.TypeInferenceContext;

public class TypeInferenceContextTest {
    @Test
    public void testDefaultImportPackages() {
        TypeInferenceContext context = TypeInferenceContext.create();
        assertEquals("java.lang.Integer", context.findFQCN("Integer").get());
        assertEquals("java.time.LocalDateTime", context.findFQCN("LocalDateTime").get());
        assertEquals("java.time.LocalDate", context.findFQCN("LocalDate").get());
    }

    @Test
    public void testDefaultImportClasses() {
        TypeInferenceContext context = TypeInferenceContext.create();
        assertEquals("java.math.BigInteger", context.findFQCN("BigInteger").get());
        assertEquals("java.math.BigDecimal", context.findFQCN("BigDecimal").get());
    }
}
