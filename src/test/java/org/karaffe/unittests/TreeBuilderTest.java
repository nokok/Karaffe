package org.karaffe.unittests;

import org.karaffe.compiler.frontend.karaffe.util.TypeTreeConverter;
import org.karaffe.compiler.tree.Tree;
import org.karaffe.compiler.tree.formatter.SimpleTreeFormatter;
import org.karaffe.compiler.tree.formatter.InternalStateFormatter;
import org.karaffe.compiler.util.CompilerContext;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TreeBuilderTest {

    @Test
    void testObjectClass() {
        TypeTreeConverter builder = new TypeTreeConverter();
        Tree tree = builder.convert(Object.class); // Use Object.class in Java
        InternalStateFormatter formatter = new SimpleTreeFormatter();
        CompilerContext context = CompilerContext.createInitialContext(); // Use factory method
        context.setUntypedTree(tree);
        String format = formatter.format(context);

        String expectedString = "PackageName java.lang\n" +
                                "  ClassName Object";
        assertEquals(expectedString, format);
    }
}
