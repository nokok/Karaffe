/**
 * Karaffe Programming Language
 */
package net.nokok.karaffe.parser.asm.visitorutils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import net.nokok.karaffe.parser.ASTInterfaceType;
import net.nokok.karaffe.parser.ASTInterfaces;
import net.nokok.karaffe.parser.ParserDefaultVisitor;
import net.nokok.karaffe.parser.ParserVisitor;
import net.nokok.karaffe.parser.excptn.ParserException;
import net.nokok.karaffe.parser.util.AmbiguousNameUtil;

public class InterfacesToArray {

    private final ParserVisitor visitor = new ParserDefaultVisitor() {

        @Override
        public Object visit(ASTInterfaceType node, Object data) throws ParserException {
            AmbiguousNameUtil util = new AmbiguousNameUtil(node);
            paths.add(util.getPath());
            return null;
        }

    };

    private final List<String> paths = new ArrayList<>();

    public InterfacesToArray(ASTInterfaces interfaces) {
        try {
            visitor.visit(interfaces, null);
        } catch (ParserException ex) {
            throw new IllegalArgumentException();
        }
    }

    public List<String> getInterfaces() {
        return Collections.unmodifiableList(paths);
    }
}
