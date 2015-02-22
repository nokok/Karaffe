/**
 * Karaffe Programming Language
 */
package net.nokok.karaffe.parser.util;

import net.nokok.karaffe.parser.ASTClassType;
import net.nokok.karaffe.parser.ASTCompileUnit;
import net.nokok.karaffe.parser.asm.nodes.NodeUtil;
import static net.nokok.karaffe.parser.syntax.KaraffeParserSyntaxTest.testCode;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class AmbiguousNameTest implements java.io.Serializable {

    @Test
    public void testGetPath1() {
        ASTCompileUnit compileUnit = testCode("type D < Interface1 {}");
        ASTClassType interfaceType = new NodeUtil(compileUnit).forceGetFindFirstNode(ASTClassType.class);
        AmbiguousName ambiguousName = new AmbiguousName(interfaceType);
        assertThat(ambiguousName.getPath(), is("Interface1"));
    }

    @Test
    public void testGetPath2() {
        ASTCompileUnit compileUnit = testCode("type D < java.io.Serializable {}");
        ASTClassType interfaceType = new NodeUtil(compileUnit).forceGetFindFirstNode(ASTClassType.class);
        AmbiguousName ambiguousName = new AmbiguousName(interfaceType);
        assertThat(ambiguousName.getPath(), is("java.io.Serializable"));
    }

    @Test
    public void testGetLast() {
        ASTCompileUnit compileUnit = testCode("import java.lang.String");
        AmbiguousName name = new AmbiguousName(compileUnit);
        assertThat(name.getLast(), is("String"));
    }

    @Test
    public void testGetLast1() {
        ASTCompileUnit compileUnit = testCode("import Hoge");
        AmbiguousName name = new AmbiguousName(compileUnit);
        assertThat(name.getLast(), is("Hoge"));
    }

    @Test(expected = IllegalStateException.class)
    public void testEmpty() {
        ASTCompileUnit compileUnit = testCode("");
        AmbiguousName name = new AmbiguousName(compileUnit);
    }

}
