/* Generated By:JJTree: Do not edit this line. ASTAlgebraicDataTypeDecl.java Version 6.0 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package net.nokok.karaffe.parser;

public class ASTAlgebraicDataTypeDecl extends SimpleNode {

    public ASTAlgebraicDataTypeDecl(int id) {
        super(id);
    }

    public ASTAlgebraicDataTypeDecl(Parser p, int id) {
        super(p, id);
    }

    /** Accept the visitor. * */
    public Object jjtAccept(ParserVisitor visitor, Object data) throws net.nokok.karaffe.parser.excptn.ParserException {

        return visitor.visit(this, data);
    }
}
/* JavaCC - OriginalChecksum=7721a7df59f09c5204ff40e3618c7369 (do not edit this line) */
