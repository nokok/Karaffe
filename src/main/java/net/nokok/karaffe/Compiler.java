/**
 *
 * Karaffe Programming Language
 *   __ _____   ___  ___   ____________
 *   / //_/ _ | / _ \/ _ | / __/ __/ __/
 *  / , \/ __ |/ , _/ __ |/ _// _// _/
 * /_/|_/_/ |_/_/|_/_/ |_/_/ /_/ /___/
 *
 */
package net.nokok.karaffe;

import java.io.FileNotFoundException;
import java.io.FileReader;
import net.nokok.karaffe.parser.ASTCompileUnit;
import net.nokok.karaffe.parser.KaraffeParser;
import net.nokok.karaffe.parser.ParseException;
import net.nokok.karaffe.parser.excptn.KaraffeParserException;
import net.nokok.karaffe.parser.util.TypePool;
import net.nokok.karaffe.parser.visitor.PrintAST;
import net.nokok.karaffe.parser.visitor.TypeChecker;

public class Compiler {

    public static void main(String... args) {
        MessageReader message = new MessageReader();
        for (String arg : args) {
            try {
                KaraffeParser parser = new KaraffeParser(new FileReader(arg));
                ASTCompileUnit compileUnit = parser.CompileUnit();
                TypePool pool = (TypePool) compileUnit.jjtAccept(new TypeChecker(), arg);
                compileUnit.jjtAccept(new PrintAST(), arg);
                System.out.println("");
            } catch (FileNotFoundException ex) {
                System.out.println(message.compilerErrorFileNotFoundException() + ":" + arg);
            } catch (ParseException ex) {
                System.out.println(message.compilerErrorSyntaxError());
            } catch (KaraffeParserException ex) {
                System.out.println(message.compilerErrorSyntaxError());
            }
        }
    }

}
