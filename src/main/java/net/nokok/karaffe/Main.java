package net.nokok.karaffe;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import net.nokok.karaffe.parser.ASTCompileUnit;
import net.nokok.karaffe.parser.ParseException;
import net.nokok.karaffe.parser.Parser;
import net.nokok.karaffe.parser.asm.nodes.collectors.ClassCollector;
import net.nokok.karaffe.parser.asm.nodes.collectors.ImportCollector;
import net.nokok.karaffe.parser.asm.typechecker.ClassResolver;
import net.nokok.karaffe.parser.util.OutputFormatter;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.ClassNode;

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
                Map<String, Class<?>> imports = new ImportCollector(compileUnit).getImports();
                ClassResolver resolver = new ClassResolver(imports);
                ClassCollector classCollector = new ClassCollector(resolver);
                List<ClassNode> classes = classCollector.getClassNodes();
                classes.forEach(clazz -> {
                    ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_MAXS);
                    clazz.accept(classWriter);
                    classWriter.toByteArray();
                });
            } catch (ParseException ex) {
                new OutputFormatter(arg).print(ex);
            } catch (IOException ex) {
                new OutputFormatter(arg).print(ex);
            }
        }
    }
}
