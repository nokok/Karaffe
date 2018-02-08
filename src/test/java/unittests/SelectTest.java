package unittests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.karaffe.compiler.context.TypeContext;
import org.karaffe.compiler.tree.Select;
import org.karaffe.compiler.tree.SelectCategory;

public class SelectTest {

    @Test
    public void testUnknown() {
        assertEquals(new Select("java").getCategory(TypeContext.create()), SelectCategory.UNKNOWN);
    }

    @Test
    public void testPackageRef() {
        assertEquals(new Select("java", "lang").getCategory(TypeContext.create()), SelectCategory.PACKAGEREF);
    }

    @Test
    public void testClassRef() {
        assertEquals(new Select("java", "lang", "Object").getCategory(TypeContext.create()), SelectCategory.CLASSREF);
    }

    @Test
    public void testMethodRef() {
        assertEquals(new Select("java", "lang", "Object", "toString").getCategory(TypeContext.create()), SelectCategory.METHODREF);
    }
}
