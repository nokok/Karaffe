/* Generated By:JJTree: Do not edit this line. ASTBoolOrExpr.java Version 6.0 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package net.nokok.karaffe.parser;

public class ASTBoolOrExpr extends SimpleNode {

    public ASTBoolOrExpr(int id) {
        super(id);
    }

    public ASTBoolOrExpr(Parser p, int id) {
        super(p, id);
    }

    /** Accept the visitor. * */
    public Object jjtAccept(ParserVisitor visitor, Object data) throws net.nokok.karaffe.parser.excptn.ParserException {

        return visitor.visit(this, data);
    }
}
/* JavaCC - OriginalChecksum=ebaba6788b200bdc8e75cf05051eb986 (do not edit this line) */
