package karaffe.compiler;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.ClassNode;

class Main {

    public static void main(String... args) throws Exception {
        if ( args == null || args.length < 1 ) {
            System.out.println("Usage: krfc FileName.krf [options...]");
            return;
        }
        Parser parser = new Parser(new Lexer(new FileReader(args[0])));
        Program program = parser.program();


        List<ClassNode> classNodes = program.toClassNodes();
        classNodes.stream()
            .forEach(c -> {
                ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_MAXS);
                c.accept(classWriter);
                byte[] bytecode = classWriter.toByteArray();
                try {
                    Files.write(new File(c.name + ".class").toPath(), bytecode);
                } catch (IOException e) {
                    throw new UncheckedIOException(e);
                }
            });
        if ( Arrays.asList(args).contains("--debug") ) {
            System.out.println("Generated AST");
            System.out.println(program);
            System.out.println(Context.INSTANCE.getPathList());
        }
    }
}
