/* Generated By:JJTree: Do not edit this line. ASTExpressionName.java Version 6.0 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=true,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package net.nokok.karaffe.parser;

public class ASTExpressionName extends SimpleNode {

    public ASTExpressionName(int id) {
        super(id);
    }

    public ASTExpressionName(Parser p, int id) {
        super(p, id);
    }

    /** Accept the visitor. * */
    public Object jjtAccept(ParserVisitor visitor, Object data) throws net.nokok.karaffe.parser.excptn.ParserException {

        return visitor.visit(this, data);
    }
}
/* JavaCC - OriginalChecksum=5e6c031d3a5b3022cc30e7a34b59b765 (do not edit this line) */
