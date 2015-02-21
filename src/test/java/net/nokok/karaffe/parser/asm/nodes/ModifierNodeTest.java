/**
 * Karaffe Programming Language
 */
package net.nokok.karaffe.parser.asm.nodes;

import java.util.Optional;
import net.nokok.karaffe.parser.ASTCompileUnit;
import static net.nokok.karaffe.parser.syntax.KaraffeParserSyntaxTest.testCode;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import org.objectweb.asm.Opcodes;

public class ModifierNodeTest {

    @Test
    public void testEmptyModifier() {
        ASTCompileUnit compileUnit = testCode("type D{}");
        ModifierNode modifierNode = new ModifierNode(compileUnit);
        Optional<Integer> modifier = modifierNode.getModifier();
        assertThat(modifier.isPresent(), is(true));
        assertThat(modifier.get(), is(0));
    }

    @Test
    public void testPublicModifier() {
        ASTCompileUnit compileUnit = testCode("public type D{}");
        ModifierNode modifierNode = new ModifierNode(compileUnit);
        Optional<Integer> modifier = modifierNode.getModifier();
        assertThat(modifier.isPresent(), is(true));
        assertThat(modifier.get(), is(Opcodes.ACC_PUBLIC));
    }

    @Test
    public void testProtectedModifier() {
        ASTCompileUnit compileUnit = testCode("protected type D{}");
        ModifierNode modifierNode = new ModifierNode(compileUnit);
        Optional<Integer> modifier = modifierNode.getModifier();
        assertThat(modifier.isPresent(), is(true));
        assertThat(modifier.get(), is(Opcodes.ACC_PROTECTED));
    }

    @Test
    public void testPrivateModifier() {
        ASTCompileUnit compileUnit = testCode("private type D{}");
        ModifierNode modifierNode = new ModifierNode(compileUnit);
        Optional<Integer> modifier = modifierNode.getModifier();
        assertThat(modifier.isPresent(), is(true));
        assertThat(modifier.get(), is(Opcodes.ACC_PRIVATE));
    }

    @Test(expected = ModifierNode.DuplicateModifierException.class)
    public void testDuplicateModifier() {
        ASTCompileUnit compileUnit = testCode("public public type D{}");
        ModifierNode modifierNode = new ModifierNode(compileUnit);
        modifierNode.getModifier(); //should throw DuplicateModifierException
    }

}
