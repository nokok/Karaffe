package karaffe.compiler;

import java.io.File;
import java.io.FileReader;
import java.util.List;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;

public class ASTTest {

    public Program getFileProgram(String fileName) {
        try {
            File resource = new File("src/test/karaffe/resources/" + fileName);
            Parser parser = new Parser(new Lexer(new FileReader(resource)));
            return parser.program();
        } catch (Exception e) {
            e.printStackTrace();
            fail();
            return null;
        }
    }

    @Before
    public void setUp() {
        Context.INSTANCE.clear();
    }

    @Test
    public void test1() {
        Program program = getFileProgram("test1.krf");
        PackageDef packageDef = program.getPackageDef();
        assertThat(packageDef.getPath(), is("Root"));
        assertThat(program.getClassDefList().size(), is(0));
        assertThat(program.getImportDefList().size(), is(0));
    }

    @Test
    public void test2() {
        Program program = getFileProgram("test2.krf");
        PackageDef packageDef = program.getPackageDef();
        assertThat(packageDef.getPath(), is("Root"));
        assertThat(packageDef.toPath("."), is("hoge"));
        assertThat(program.getClassDefList().size(), is(0));
        assertThat(program.getImportDefList().size(), is(0));
    }

    @Test
    public void test3() {
        Program program = getFileProgram("test3.krf");
        PackageDef packageDef = program.getPackageDef();
        assertThat(packageDef.getPath(), is("Root"));
        assertThat(packageDef.toPath("."), is("hoge.fuga"));
        assertThat(packageDef.toPath("/"), is("hoge/fuga"));
        assertThat(program.getClassDefList().size(), is(0));
        assertThat(program.getImportDefList().size(), is(0));
    }

    @Test
    public void test4() {
        Program program = getFileProgram("test4.krf");
        PackageDef packageDef = program.getPackageDef();
        assertThat(packageDef.getPath(), is("Root"));
        assertThat(packageDef.toPath("."), is("hoge.fuga"));
        List<ImportDef> importDefList = program.getImportDefList();
        assertThat(importDefList.size(), is(1));
        assertThat(program.getClassDefList().size(), is(0));
        ImportDef importDef = importDefList.get(0);
        assertThat(importDef.getPath(), is("."));
        assertThat(importDef.simpleIdent(), is("hoge"));
    }

    @Test
    public void test5() {
        Program program = getFileProgram("test5.krf");
        PackageDef packageDef = program.getPackageDef();
        assertThat(packageDef.getPath(), is("Root"));
        assertThat(packageDef.toPath("."), is("hoge.fuga"));
        List<ImportDef> importDefList = program.getImportDefList();
        assertThat(importDefList.size(), is(1));
        assertThat(program.getClassDefList().size(), is(0));
        ImportDef importDef = importDefList.get(0);
        assertThat(importDef.getPath(), is("."));
        assertThat(importDef.simpleIdent(), is("piyo"));
    }
}
