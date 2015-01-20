/**
 * Karaffe Programming Language
 */
package net.nokok.karaffe.parser.util;

import java.util.ArrayList;
import java.util.List;
import net.nokok.karaffe.parser.ASTIdentifier;
import net.nokok.karaffe.parser.Node;
import net.nokok.karaffe.parser.ParserDefaultVisitor;
import net.nokok.karaffe.parser.excptn.ParserException;

public class AmbiguousNameUtil {

    private final Node node;
    private String pathCache;

    private final ParserDefaultVisitor visitor = new ParserDefaultVisitor() {

        @Override
        public Object visit(ASTIdentifier node, Object data) throws ParserException {
            ids.add(node.jjtGetValue().toString());
            return null;
        }
    };

    private final List<String> ids = new ArrayList<>();

    public AmbiguousNameUtil(Node node) {
        this.node = node;
    }

    public String getPath() throws ParserException {
        node.jjtAccept(visitor, this);
        final StringBuilder sb = new StringBuilder();
        sb.append(ids.get(0));
        ids.stream().skip(1).forEach(id -> {
            sb.append("/").append(id);
        });
        pathCache = sb.toString();
        return pathCache;
    }

}
