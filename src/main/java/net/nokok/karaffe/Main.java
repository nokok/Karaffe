package net.nokok.karaffe;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import net.nokok.karaffe.parser.ASTCompileUnit;
import net.nokok.karaffe.parser.ParseException;
import net.nokok.karaffe.parser.Parser;
import net.nokok.karaffe.parser.asm.BytecodeGeneratorVisitor;
import net.nokok.karaffe.parser.excptn.ParserException;
import net.nokok.karaffe.parser.util.OutputFormatter;

public class Main {

    /**
     * krfcコマンドのエントリポイントです。
     *
     * @param args
     * @throws java.io.IOException
     */
    public static void main(String... args) throws IOException {
        args = new String[]{"Int.krf"}; //Debug
        for (final String arg : args) {
            try {
                final File file = new File(arg);
                final Parser parser = new Parser(new FileReader(file));
                final ASTCompileUnit compileUnit = parser.CompileUnit();
                compileUnit.dump(""); //Debug
                final BytecodeGeneratorVisitor visitor = new BytecodeGeneratorVisitor();
                compileUnit.jjtAccept(visitor, null);
            } catch (ParseException ex) {
                new OutputFormatter(arg).print(ex);
            } catch (IOException ex) {
                new OutputFormatter(arg).print(ex);
            } catch (ParserException ex) {
                new OutputFormatter(arg).print(ex);
            }
        }
    }
}
