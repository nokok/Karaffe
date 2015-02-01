/**
 * Karaffe Programming Language
 */
package net.nokok.karaffe.parser.asm.nodes;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import net.nokok.karaffe.parser.ASTCompileUnit;
import net.nokok.karaffe.parser.ASTTypeName;
import net.nokok.karaffe.parser.Node;
import net.nokok.karaffe.parser.ParserDefaultVisitor;
import net.nokok.karaffe.parser.ParserVisitor;
import net.nokok.karaffe.parser.excptn.ParserException;
import net.nokok.karaffe.parser.util.AmbiguousName;

public class ImportCollector {

    private final Map<String, Class<?>> imports = new HashMap<>();

    private final ParserVisitor simpleImportVisitor = new ParserDefaultVisitor() {

        @Override
        public Object visit(ASTTypeName node, Object data) throws ParserException {
            AmbiguousName name = new AmbiguousName(node);

            try {
                //ambiguous name java.lang.String
                //          String          Class<java.lang.String>
                imports.put(name.getLast(), Class.forName(name.getPath()));
            } catch (ClassNotFoundException ex) {
                //TODO:
            }
            return null;
        }

    };

//    private final ParserVisitor aliasImportVisitor = new ParserDefaultVisitor() {
//
//    };
    private final Node node;

    public ImportCollector(ASTCompileUnit compileUnit) {
        this.node = compileUnit;
        try {
            node.jjtAccept(simpleImportVisitor, null);
//            node.jjtAccept(aliasImportVisitor, null);
        } catch (ParserException ex) {
            throw new RuntimeException(ex);
        }
    }

    public Map<String, Class<?>> getImports() {
        return Collections.unmodifiableMap(imports);
    }
}
