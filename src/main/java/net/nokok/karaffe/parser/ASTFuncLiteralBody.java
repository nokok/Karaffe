/* Generated By:JJTree: Do not edit this line. ASTFuncLiteralBody.java Version 6.0 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=true,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package net.nokok.karaffe.parser;

public
class ASTFuncLiteralBody extends SimpleNode {
  public ASTFuncLiteralBody(int id) {
    super(id);
  }

  public ASTFuncLiteralBody(Parser p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public void jjtAccept(ParserVisitor visitor, Object data) throws net.nokok.karaffe.parser.excptn.ParserException {

    visitor.visit(this, data);
  }
}
/* JavaCC - OriginalChecksum=14548c19f0d10b1434115960aab629ac (do not edit this line) */
