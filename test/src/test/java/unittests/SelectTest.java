package unittests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Ignore;
import org.junit.Test;
import org.karaffe.compiler.context.TypeContext;
import org.karaffe.compiler.tree.Select;
import org.karaffe.compiler.tree.SelectCategory;

@Ignore
public class SelectTest {

    @Test
    public void testUnknown() {
        assertEquals(SelectCategory.UNKNOWN, new Select("java").getCategory(TypeContext.create()));
    }

    @Test
    public void testPackageRef() {
        assertEquals(SelectCategory.PACKAGEREF, new Select("java", "lang").getCategory(TypeContext.create()));
    }

    @Test
    public void testClassRef() {
        assertEquals(SelectCategory.CLASSREF, new Select("java", "lang", "Object").getCategory(TypeContext.create()));
    }

    @Test
    public void testMethodRef() {
        assertEquals(SelectCategory.METHODREF, new Select("java", "lang", "Object", "toString").getCategory(TypeContext.create()));
    }

    @Test
    public void testConstructorRef() {
        assertEquals(SelectCategory.CONSTRUCTORREF, new Select("java", "lang", "Object", "<init>").getCategory(TypeContext.create()));
    }

    @Test
    public void testStaticFieldRef() {
        assertEquals(SelectCategory.STATICFIELDREF, new Select("java", "lang", "System", "out").getCategory(TypeContext.create()));
    }

    @Test
    public void testClassRefWithContext() {
        // with default import
        assertEquals(SelectCategory.CLASSREF, new Select("Int").getCategory(TypeContext.create()));
    }

    @Test
    public void testConstructorRefWithContext() {
        // with default import
        assertEquals(SelectCategory.CONSTRUCTORREF, new Select("Int", "<init>").getCategory(TypeContext.create()));
    }

    @Test
    public void testIsSimpleName() {
        assertTrue(new Select("name").isSimpleName());
        assertFalse(new Select("name1", "name2").isSimpleName());
    }
}
