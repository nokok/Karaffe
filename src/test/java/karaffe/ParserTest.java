package karaffe;

import java.io.File;
import java.io.FileReader;
import karaffe.compiler.Context;
import karaffe.compiler.Lexer;
import karaffe.compiler.Parser;
import static org.junit.Assert.fail;
import org.junit.Test;

public class ParserTest {

    @Test
    public void testAllFiles() throws Exception {
        File resources = new File("src/test/karaffe/resources/");
        String[] list = resources.list((dir, name) -> name.startsWith("test"));
        for ( String f : list ) {
            try {
                Parser parser = new Parser(new Lexer(new FileReader(new File(resources, f))));
                parser.parse();
            } catch (Exception e) {
                e.printStackTrace();
                fail();
            }
            Context.INSTANCE.clear();
        }
    }
}
