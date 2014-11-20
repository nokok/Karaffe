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
        String source = "type Hoge\nx:Int=2\nfunc = [x] => {1}\n";
        System.out.println(source);
        KaraffeParser parser = new KaraffeParser(source);
        ASTCompileUnit compileUnit = parser.CompileUnit();
        Object accepted = compileUnit.jjtAccept(new KaraffeParserDefaultVisitor(), "1");
        compileUnit.jjtAccept(new PrintAST(), null);
        System.out.println("");
    }
}
