package karaffe;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import karaffe.compiler.Context;
import karaffe.compiler.Lexer;
import karaffe.compiler.Main;
import karaffe.compiler.Parser;
import karaffe.compiler.TypeCheckException;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;

public class ParserTest {

    @Before
    public void setUp() {
        Context.INSTANCE.clear();
    }

    @Test
    public void testAllFiles() throws Exception {
        File resources = new File("src/test/karaffe/resources/");
        String[] list = resources.list((dir, name) -> name.startsWith("test"));
        for ( String f : list ) {
            System.out.println(f);
            String file = new File(resources, f).getAbsolutePath();
            try {
                Main.main(new String[]{ file, "--debug" });
            } catch (Exception e) {
                e.printStackTrace();
                fail(f);
            } finally {
                Context.INSTANCE.clear();
            }
        }
    }

    @Test
    public void testAllFailFiles() throws Exception {
        File resources = new File("src/test/karaffe/resources/fail");
        String[] list = resources.list((dir, name) -> name.startsWith("fail"));
        for ( String f : list ) {
            System.out.println(f);
            try {
                Parser parser = new Parser(new Lexer(new FileReader(new File(resources, f))));
                parser.program();
                fail(f);
            } catch (Exception e) {
                /* OK */
            } finally {
                Context.INSTANCE.clear();
            }
        }
    }

    @Test
    public void testTypeCheck() throws Exception {
        File resources = new File("src/test/karaffe/resources/type");
        String[] list = resources.list((dir, name) -> name.startsWith("test"));
        for ( String f : list ) {
            System.out.println(f);
            String file = new File(resources, f).getAbsolutePath();
            try {
                Main.main(new String[]{ file, "--debug" });
            } catch (IOException e) {
                fail(f);
            } finally {
                Context.INSTANCE.clear();
            }
        }
    }

    @Test
    public void testTypeCheckFail() throws Exception {
        File resources = new File("src/test/karaffe/resources/type");
        String[] list = resources.list((dir, name) -> name.startsWith("fail"));
        for ( String f : list ) {
            System.out.println(f);
            String file = new File(resources, f).getAbsolutePath();
            try {
                Main.main(new String[]{ file, "--debug" });
                fail(f);
            } catch (TypeCheckException e) {
                /*OK*/
            } finally {
                Context.INSTANCE.clear();
            }
        }
    }

}
