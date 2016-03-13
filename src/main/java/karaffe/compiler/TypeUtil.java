package karaffe.compiler;

import java.io.IOException;
import java.io.UncheckedIOException;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.tree.ClassNode;

public class TypeUtil {

    public ClassNode loadClassNode(Class<?> clazz) {
        try {
            ClassNode classNode = new ClassNode();
            ClassReader classReader = new ClassReader(clazz.getCanonicalName());
            classReader.accept(classNode, classReader.SKIP_DEBUG);
            return classNode;
        } catch (IOException ex) {
            throw new UncheckedIOException(ex);
        }
    }
}
