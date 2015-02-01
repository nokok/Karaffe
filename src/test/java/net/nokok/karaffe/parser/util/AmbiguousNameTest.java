/**
 * Karaffe Programming Language
 */
package net.nokok.karaffe.parser.util;

import net.nokok.karaffe.parser.ASTCompileUnit;
import net.nokok.karaffe.parser.ASTInterfaceType;
import net.nokok.karaffe.parser.asm.nodes.NodeUtil;
import static net.nokok.karaffe.parser.syntax.KaraffeParserSyntaxTest.testCode;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class AmbiguousNameTest implements java.io.Serializable {

    @Test
    public void testGetPath1() {
        ASTCompileUnit compileUnit = testCode("type D <- Interface1 {}");
        ASTInterfaceType interfaceType = new NodeUtil(compileUnit).forceGetFindFirstNode(ASTInterfaceType.class);
        AmbiguousName ambiguousName = new AmbiguousName(interfaceType);
        assertThat(ambiguousName.getPath(), is("Interface1"));
    }

    @Test
    public void testGetPath2() {
        ASTCompileUnit compileUnit = testCode("type D <- java.io.Serializable {}");
        ASTInterfaceType interfaceType = new NodeUtil(compileUnit).forceGetFindFirstNode(ASTInterfaceType.class);
        AmbiguousName ambiguousName = new AmbiguousName(interfaceType);
        assertThat(ambiguousName.getPath(), is("java.io.Serializable"));
    }

}
