/* Generated By:JJTree: Do not edit this line. ASTAssignmentOp.java Version 6.0 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package net.nokok.karaffe.parser;

public class ASTAssignmentOp extends SimpleNode {

    public ASTAssignmentOp(int id) {
        super(id);
    }

    public ASTAssignmentOp(Parser p, int id) {
        super(p, id);
    }

    /** Accept the visitor. * */
    public Object jjtAccept(ParserVisitor visitor, Object data) throws net.nokok.karaffe.parser.excptn.ParserException {

        return visitor.visit(this, data);
    }
}
/* JavaCC - OriginalChecksum=fe72a170fa4977131c3f0805a975c284 (do not edit this line) */
