/* Generated By:JJTree: Do not edit this line. ASTLastFormalParamter.java Version 6.0 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=true,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package net.nokok.karaffe.parser;

public class ASTLastFormalParamter extends SimpleNode {

    public ASTLastFormalParamter(int id) {
        super(id);
    }

    public ASTLastFormalParamter(Parser p, int id) {
        super(p, id);
    }

    /** Accept the visitor. * */
    public Object jjtAccept(ParserVisitor visitor, Object data) throws net.nokok.karaffe.parser.excptn.ParserException {

        return visitor.visit(this, data);
    }
}
/* JavaCC - OriginalChecksum=b9a4f39639e55f35a53f6a3725fa43f4 (do not edit this line) */
