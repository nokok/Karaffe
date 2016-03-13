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

public class Main {

    public static void main(String... args) throws Exception {
        if ( args == null || args.length < 1 ) {
            System.out.println("Usage: krfc FileName.krf [options...]");
            return;
        }
        File file = new File(args[0]);

        Context.INSTANCE.add(file);

        Parser parser = new Parser(new Lexer(new FileReader(file)));
        Program program = parser.program();

        boolean isDebugMode = Arrays.asList(args).contains("--debug");
        if ( isDebugMode ) {
            System.out.println("\n\n");
            System.out.println("DEBUG");
            System.out.println(program);
        }

        List<ClassNode> classNodes = program.toClassNodes();
        classNodes.stream()
            .forEach(c -> {
                ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
                c.accept(classWriter);
                byte[] bytecode = classWriter.toByteArray();
                try {
                    Files.write(new File(c.name + ".class").toPath(), bytecode);
                } catch (IOException e) {
                    throw new UncheckedIOException(e);
                }
            });
        if ( isDebugMode ) {
            System.out.println(Context.INSTANCE.getPathList());
        }
    }
}
