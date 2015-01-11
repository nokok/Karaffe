/**
 *
 * Karaffe Programming Language
 * __ _____ ___ ___ ____________
 *   / //_/ _ | / _ \/ _ | / __/ __/ __/
 *  / , \/ __ |/ , _/ __ |/ _// _// _/
 * /_/|_/_/ |_/_/|_/_/ |_/_/ /_/ /___/
 *
 */
package net.nokok.karaffe.parser.syntax;

import net.nokok.karaffe.parser.ASTAssign;
import net.nokok.karaffe.parser.ASTAssignment;
import net.nokok.karaffe.parser.ASTCompileUnit;
import net.nokok.karaffe.parser.ASTIntLiteral;
import net.nokok.karaffe.parser.ASTLeftHandSide;
import static net.nokok.karaffe.parser.syntax.KaraffeParserSyntaxTest.testCode;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class AssignmentExpressionSyntaxTest {

    @Test
    public void testExprNameAssignment() {
        ASTCompileUnit compileUnit = testCode("hoge = 1");
        assertThat(compileUnit.jjtGetNumChildren(), is(1));

        ASTAssignment assignment = (ASTAssignment) compileUnit.jjtGetChild(0);
        assertThat(assignment.jjtGetNumChildren(), is(3));

        ASTLeftHandSide leftHandSide = (ASTLeftHandSide) assignment.jjtGetChild(0);
        assertThat(leftHandSide.jjtGetNumChildren(), is(1));

        ASTAssign assign = (ASTAssign) assignment.jjtGetChild(1);
        assertThat(assign.jjtGetNumChildren(), is(0));

        ASTIntLiteral intLiteral = (ASTIntLiteral) assignment.jjtGetChild(2);
        assertThat(intLiteral.jjtGetNumChildren(), is(0));
    }

    @Test
    public void testInstanceMemberAssignment() {
        ASTCompileUnit compileUnit = testCode("obj.field = 9");
        assertThat(compileUnit.jjtGetNumChildren(), is(1));

        ASTAssignment assignment = (ASTAssignment) compileUnit.jjtGetChild(0);

        ASTLeftHandSide leftHandSide = (ASTLeftHandSide) assignment.jjtGetChild(0);

    }

    @Test
    public void testMethodValueAssignment() {
        testCode("hoge = hoge()\n");
    }

    @Test
    public void testElementAccessAssignment() {
        testCode("elementData = c.toArray()\n");
    }

    @Test
    public void testElementAccessAssignment1() {
        testCode("elementData = c toArray()\n");
    }

    @Test
    public void testSystemOutPrintln() {
        testCode("System out println()");
    }
}
