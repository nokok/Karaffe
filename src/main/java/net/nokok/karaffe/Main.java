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

public class Main {

    /**
     * krfcコマンドのエントリポイントです。
     *
     * @param args
     */
    public static void main(String... args) {
        for ( String arg : args ) {
            try {
                File file = new File(arg);
                KaraffeParser parser = new KaraffeParser(new FileReader(file));
                ASTCompileUnit compileUnit = parser.CompileUnit();
                compileUnit.dump("");
                compileUnit.jjtAccept(new BytecodeGenerator(arg), null);
            } catch (ParseException ex) {
                new OutputFormatter(arg).print(ex);
            } catch (IOException ex) {
                new OutputFormatter(arg).print(ex);
            } catch (KaraffeParserException ex) {
                new OutputFormatter(arg).print(ex);
            }
        }
    }
}
