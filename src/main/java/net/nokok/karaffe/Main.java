package net.nokok.karaffe;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import net.nokok.karaffe.parser.ASTCompileUnit;
import net.nokok.karaffe.parser.KaraffeParser;
import net.nokok.karaffe.parser.ParseException;
import net.nokok.karaffe.parser.excptn.KaraffeParserException;
import net.nokok.karaffe.parser.util.OutputFormatter;
import net.nokok.karaffe.parser.visitor.BytecodeGenerator;
import net.nokok.karaffe.parser.visitor.PrintAST;

public class Main {

    /**
     * krfcコマンドのエントリポイントです。
     *
     * @param args
     */
    public static void main(String... args) throws IOException, KaraffeParserException {
        args = new String[]{"Int.krf"};
        for (String arg : args) {
            try {
                File file = new File(arg);
                KaraffeParser parser = new KaraffeParser(new FileReader(file));
                ASTCompileUnit compileUnit = parser.CompileUnit();
                compileUnit.dump("");
                compileUnit.jjtAccept(new PrintAST(), null);
                compileUnit.jjtAccept(new BytecodeGenerator(arg), null);
            } catch (ParseException ex) {
                ex.printStackTrace();
                new OutputFormatter(arg).print(ex);
            }
        }
    }
}
