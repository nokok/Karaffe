/**
 *
 * Karaffe Programming Language
 *   __ _____   ___  ___   ____________
 *   / //_/ _ | / _ \/ _ | / __/ __/ __/
 *  / , \/ __ |/ , _/ __ |/ _// _// _/
 * /_/|_/_/ |_/_/|_/_/ |_/_/ /_/ /___/
 *
 */
package net.nokok.karaffe.parser.syntax;

import net.nokok.karaffe.parser.KaraffeParser;
import net.nokok.karaffe.parser.ParseException;
import static org.junit.Assert.fail;
import org.junit.Test;

public class KaraffeParserSyntaxTest {

    @Test
    public void variableDeclarationExplicitType() {
        testCode("a:Int=2");
        testCode("b:Int=2\n");
        testCode("c : Int = 2");
        testCode("d : Int = 2\n");
        testCode("e : Int");
        testCode("f : Int\n");
        testCode("g = 2 //hogehoge\n");
        testCode("h = 2 /* */");
    }

    @Test
    public void variableDeclaration() {
        testCode("a = 2\n");
    }

    @Test
    public void empty() {
        testCode("");
    }

    @Test
    public void newLine() {
        testCode("\n");
    }

    @Test
    public void comment() {
        testCode("//hogehoge\n");
        testCode("/**/");
        testCode("/* */");
        testCode("/*  /* */  */");
    }

    private void testCode(String code) {
        try {
            new KaraffeParser(code).CompileUnit();
        } catch (ParseException ex) {
            ex.printStackTrace();
            fail();
        }
    }
}
