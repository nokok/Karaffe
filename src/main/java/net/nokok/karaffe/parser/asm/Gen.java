/**
 * Karaffe Programming Language
 */
package net.nokok.karaffe.parser.asm;

import java.util.List;
import java.util.Map;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.ClassNode;

import net.nokok.karaffe.parser.ASTCompileUnit;
import net.nokok.karaffe.parser.asm.nodes.ClassCollector;
import net.nokok.karaffe.parser.asm.nodes.ImportCollector;
import net.nokok.karaffe.parser.asm.typechecker.ClassResolver;

public class Gen {

    public Gen(ASTCompileUnit node) {
        Map<String, Class<?>> imports = new ImportCollector(node).getImports();
        ClassResolver resolver = new ClassResolver(imports);
        ClassCollector classCollector = new ClassCollector(resolver);
        List<ClassNode> classes = classCollector.getClassNodes();
        classes.forEach(clazz -> {
            ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_MAXS);
            clazz.accept(classWriter);
            classWriter.toByteArray();
        });
    }
}
