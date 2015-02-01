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

public class AmbiguousName {

    private String pathCache;

    private final ParserDefaultVisitor visitor = new ParserDefaultVisitor() {

        @Override
        public Object visit(ASTIdentifier node, Object data) throws ParserException {
            ids.add(node.jjtGetValue().toString());
            return null;
        }
    };

    private final List<String> ids = new ArrayList<>();

    public AmbiguousName(Node node) {
        try {
            node.jjtAccept(visitor, this);
        } catch (ParserException ex) {
            throw new RuntimeException(ex);
        }
    }

    public String getPath() {
        if (pathCache != null) {
            return pathCache;
        }
        final StringBuilder sb = new StringBuilder();
        sb.append(ids.get(0));
        ids.stream().skip(1).forEach(id -> {
            sb.append(".").append(id);
        });
        pathCache = sb.toString();
        return pathCache;
    }

    public String getLast() {
        return ids.get(ids.size() - 1);
    }

}
