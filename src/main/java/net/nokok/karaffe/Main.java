package net.nokok.karaffe;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Set;
import javassist.CannotCompileException;
import javassist.CtClass;
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
                Set<CtClass> classes = (Set<CtClass>) compileUnit.jjtAccept(new BytecodeGenerator(), null);
                for ( CtClass clazz : classes ) {
                    clazz.writeFile(".");
                }
            } catch (ParseException ex) {
                new OutputFormatter(arg).print(ex);
            } catch (IOException ex) {
                new OutputFormatter(arg).print(ex);
            } catch (KaraffeParserException ex) {
                new OutputFormatter(arg).print(ex);
            } catch (CannotCompileException ex) {
                ex.printStackTrace();
            }
        }
    }
}
