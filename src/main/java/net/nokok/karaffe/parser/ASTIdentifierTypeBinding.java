/* Generated By:JJTree: Do not edit this line. ASTIdentifierTypeBinding.java Version 6.0 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=true,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package net.nokok.karaffe.parser;

public class ASTIdentifierTypeBinding extends SimpleNode {

    public ASTIdentifierTypeBinding(int id) {
        super(id);
    }

    public ASTIdentifierTypeBinding(Parser p, int id) {
        super(p, id);
    }

    /** Accept the visitor. * */
    public Object jjtAccept(ParserVisitor visitor, Object data) throws net.nokok.karaffe.parser.excptn.ParserException {

        return visitor.visit(this, data);
    }
}
/* JavaCC - OriginalChecksum=75e11edbd3332941b77dc68507b3cf8b (do not edit this line) */
