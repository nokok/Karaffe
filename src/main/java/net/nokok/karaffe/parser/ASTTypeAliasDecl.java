/* Generated By:JJTree: Do not edit this line. ASTTypeAliasDecl.java Version 6.0 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=true,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package net.nokok.karaffe.parser;

public class ASTTypeAliasDecl extends SimpleNode {

    public ASTTypeAliasDecl(int id) {
        super(id);
    }

    public ASTTypeAliasDecl(Parser p, int id) {
        super(p, id);
    }

    /** Accept the visitor. * */
    public Object jjtAccept(ParserVisitor visitor, Object data) throws net.nokok.karaffe.parser.excptn.ParserException {

        return visitor.visit(this, data);
    }
}
/* JavaCC - OriginalChecksum=3efa2362b835d681b4ae241ee820e089 (do not edit this line) */
