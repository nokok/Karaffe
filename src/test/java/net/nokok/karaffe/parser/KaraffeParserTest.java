/**
 *
 * Karaffe Programming Language
 * __ _____ ___ ___ ____________
 *   / //_/ _ | / _ \/ _ | / __/ __/ __/
 *  / , \/ __ |/ , _/ __ |/ _// _// _/
 * /_/|_/_/ |_/_/|_/_/ |_/_/ /_/ /___/
 *
 */
package net.nokok.karaffe.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import static org.junit.Assert.fail;
import org.junit.Test;

public class KaraffeParserTest {

    @Test
    public void testEmptyFile() {
        testFile("empty.krf");
    }

    @Test
    public void testBlockComment() {
        testFile("blockcomment.krf");
    }

    @Test
    public void testSingleLineComment() {
        testFile("single_line_comment.krf");
    }

    private void testFile(String fileName) {
        try {
            KaraffeParser parser = new KaraffeParser(getFileReader(fileName));
            ASTCompileUnit CompileUnit = parser.CompileUnit();
        } catch (ParseException ex) {
            fail(ex.getMessage());
        }
    }

    private FileReader getFileReader(String fileName) {
        try {
            File file = new File("src/test/resources/" + fileName);
            return new FileReader(file);
        } catch (FileNotFoundException ex) {
            fail(ex.getMessage());
            throw new RuntimeException(ex); //failするのでここには来ない
        }
    }
}
