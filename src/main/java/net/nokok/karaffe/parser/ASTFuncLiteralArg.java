/* Generated By:JJTree: Do not edit this line. ASTFuncLiteralArg.java Version 6.0 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=true,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package net.nokok.karaffe.parser;

public class ASTFuncLiteralArg extends SimpleNode {

    public ASTFuncLiteralArg(int id) {
        super(id);
    }

    public ASTFuncLiteralArg(Parser p, int id) {
        super(p, id);
    }

    /** Accept the visitor. * */
    public Object jjtAccept(ParserVisitor visitor, Object data) throws net.nokok.karaffe.parser.excptn.ParserException {

        return visitor.visit(this, data);
    }
}
/* JavaCC - OriginalChecksum=ddfaa61771c864e13ebf34443318e8de (do not edit this line) */
