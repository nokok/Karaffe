package net.nokok.karaffe;

import java.io.FileNotFoundException;
import net.nokok.karaffe.parser.ASTCompileUnit;
import net.nokok.karaffe.parser.KaraffeParser;
import net.nokok.karaffe.parser.KaraffeParserDefaultVisitor;
import net.nokok.karaffe.parser.ParseException;
import net.nokok.karaffe.parser.excptn.KaraffeParserException;
import net.nokok.karaffe.parser.visitor.PrintAST;

public class Main {

    /**
     * krfcコマンドのエントリポイントです。
     *
     * @param args
     */
    public static void main(String... args) throws FileNotFoundException, ParseException, KaraffeParserException {
        String source = "type Hoge\n"
                + "x:Int=2\n"
                + "func = [x] => {1}\n"
                + "hoge = \"hoge\"\n"
                + "hoge = -\"hogehoge\"\n"
                + "hoge = 1 - 2 * 3 + ( 2 + 3 )\n";
        System.out.println(source);
        KaraffeParser parser = new KaraffeParser(source);
        ASTCompileUnit compileUnit = parser.CompileUnit();
        Object accepted = compileUnit.jjtAccept(new KaraffeParserDefaultVisitor(), "1");
        compileUnit.jjtAccept(new PrintAST(), null);
        System.out.println("");
    }
}
