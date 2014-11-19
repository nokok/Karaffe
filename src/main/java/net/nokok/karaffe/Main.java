package net.nokok.karaffe;

import java.io.FileNotFoundException;
import net.nokok.karaffe.parser.excptn.KaraffeParserException;
import net.nokok.karaffe.parser.ASTCompileUnit;
import net.nokok.karaffe.parser.KaraffeParser;
import net.nokok.karaffe.parser.KaraffeParserDefaultVisitor;
import net.nokok.karaffe.parser.ParseException;

public class Main {

    /**
     * krfcコマンドのエントリポイントです。
     *
     * @param args
     */
    public static void main(String... args) throws FileNotFoundException, ParseException, KaraffeParserException {
        KaraffeParser parser = new KaraffeParser("type Hoge\n");
        ASTCompileUnit compileUnit = parser.CompileUnit();
        Object accepted = compileUnit.jjtAccept(new KaraffeParserDefaultVisitor(), "1");
        System.out.println(accepted);
    }
}
