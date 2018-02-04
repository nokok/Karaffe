package unittests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.karaffe.compiler.context.TypeInferenceContext;
import org.karaffe.compiler.tree.Select;

public class SelectTest {

    @Test
    public void testUnknown() {
        assertEquals(new Select("java").getCategory(TypeInferenceContext.create()), Select.Category.UNKNOWN);
    }

    @Test
    public void testPackageRef() {
        assertEquals(new Select("java", "lang").getCategory(TypeInferenceContext.create()), Select.Category.PACKAGEREF);
    }

    @Test
    public void testClassRef() {
        assertEquals(new Select("java", "lang", "Object").getCategory(TypeInferenceContext.create()), Select.Category.CLASSREF);
    }

    @Test
    public void testMethodRef() {
        assertEquals(new Select("java", "lang", "Object", "toString").getCategory(TypeInferenceContext.create()), Select.Category.CLASSREF);
    }
}
