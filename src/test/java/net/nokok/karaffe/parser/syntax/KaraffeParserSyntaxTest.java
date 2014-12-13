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

    //Other test
    @Test

    public void testEmptyFile() {
        testCode("");
    }

    @Test
    public void testNewLineToken() {
        testCode("\n");
    }

    @Test
    public void testComment() {
        testCode("//hogehoge\n");
        testCode("/**/");
        testCode("/* */");
        testCode("/*  /* */  */");
    }

    public static void testCode(String code) {
        try {
            new KaraffeParser(code).CompileUnit();
        } catch (ParseException ex) {
            fail();
        }
    }
}
