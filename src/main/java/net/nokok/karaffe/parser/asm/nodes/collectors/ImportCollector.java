/**
 * Karaffe Programming Language
 */
package net.nokok.karaffe.parser.asm.nodes.collectors;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.nokok.karaffe.parser.ASTCompileUnit;
import net.nokok.karaffe.parser.ASTSimpleImport;
import net.nokok.karaffe.parser.ASTTypeName;
import net.nokok.karaffe.parser.ParserDefaultVisitor;
import net.nokok.karaffe.parser.ParserVisitor;
import net.nokok.karaffe.parser.asm.nodes.NodeUtil;
import net.nokok.karaffe.parser.excptn.ParserException;
import net.nokok.karaffe.parser.util.AmbiguousName;

public class ImportCollector {

    private final Map<String, Class<?>> imports = new HashMap<>();

    private final ParserVisitor simpleImportVisitor = new ParserDefaultVisitor() {

        @Override
        public void visit(ASTSimpleImport node, Object data) throws ParserException {
            List<ASTTypeName> typeNames = new NodeUtil(node).collectNodes(ASTTypeName.class);
            for (ASTTypeName typeName : typeNames) {
                AmbiguousName name = new AmbiguousName(typeName);

                try {
                    //ambiguous name java.lang.String
                    //          String          Class<java.lang.String>
                    imports.put(name.getLast(), Class.forName(name.getPath()));
                } catch (ClassNotFoundException ex) {
                    //TODO:
                }
            }
        }

    };

//    private final ParserVisitor aliasImportVisitor = new ParserDefaultVisitor() {
//
//    };
    public ImportCollector(ASTCompileUnit compileUnit) {
        try {
            compileUnit.jjtAccept(simpleImportVisitor, null);
//            node.jjtAccept(aliasImportVisitor, null);
        } catch (ParserException ex) {
            throw new RuntimeException(ex);
        }
    }

    public Map<String, Class<?>> getImports() {
        return Collections.unmodifiableMap(imports);
    }
}
