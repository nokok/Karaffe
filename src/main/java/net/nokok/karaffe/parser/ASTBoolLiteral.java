/* Generated By:JJTree: Do not edit this line. ASTBoolLiteral.java Version 6.0 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=true,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package net.nokok.karaffe.parser;

public class ASTBoolLiteral extends SimpleNode {

    public ASTBoolLiteral(int id) {
        super(id);
    }

    public ASTBoolLiteral(Parser p, int id) {
        super(p, id);
    }

    /** Accept the visitor. * */
    public Object jjtAccept(ParserVisitor visitor, Object data) throws net.nokok.karaffe.parser.excptn.ParserException {

        return visitor.visit(this, data);
    }
}
/* JavaCC - OriginalChecksum=36da71f9ef55902cc5a1b482764b36c2 (do not edit this line) */
