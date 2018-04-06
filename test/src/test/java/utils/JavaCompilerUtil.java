package utils;

import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.SimpleJavaFileObject;
import javax.tools.ToolProvider;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.assertTrue;

public class JavaCompilerUtil {
    public static byte[] compile(String sourceName, String source) {
        FileObject fileObject = new FileObject(sourceName, source);
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        DiagnosticCollector<JavaFileObject> collector = new DiagnosticCollector<>();
        JavaCompiler.CompilationTask task = compiler.getTask(
                null,
                null,
                collector,
                Arrays.asList("-d", "out"),
                null,
                Collections.singleton(fileObject));

        assertTrue(task.call());
        try {
            return Files.readAllBytes(Paths.get("out", sourceName + ".class"));
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    static class FileObject extends SimpleJavaFileObject {
        private final String source;

        protected FileObject(String name, String source) {
            super(URI.create("string:///" + name.replaceAll("\\.", "/") + Kind.SOURCE.extension), Kind.SOURCE);
            this.source = source;
        }

        @Override
        public CharSequence getCharContent(boolean ignoreEncodingErrors) throws IOException {
            return this.source;
        }
    }
}
