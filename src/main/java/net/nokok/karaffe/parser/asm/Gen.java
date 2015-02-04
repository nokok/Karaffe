/**
 * Karaffe Programming Language
 */
package net.nokok.karaffe.parser.asm;

import java.util.Map;
import net.nokok.karaffe.parser.ASTCompileUnit;
import net.nokok.karaffe.parser.asm.nodes.ImportCollector;
import net.nokok.karaffe.parser.asm.typechecker.ClassResolver;

public class Gen {

    public Gen(ASTCompileUnit node) {
        ImportCollector importCollector = new ImportCollector(node);
        Map<String, Class<?>> imports = importCollector.getImports();
        ClassResolver resolver = new ClassResolver(imports);
    }
}
