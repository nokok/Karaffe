/* Generated By:JJTree: Do not edit this line. ASTSwitchExpr.java Version 6.0 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=true,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package net.nokok.karaffe.parser;

public
class ASTSwitchExpr extends SimpleNode {
  public ASTSwitchExpr(int id) {
    super(id);
  }

  public ASTSwitchExpr(Parser p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(ParserVisitor visitor, Object data) throws net.nokok.karaffe.parser.excptn.ParserException {

    return
    visitor.visit(this, data);
  }
}
/* JavaCC - OriginalChecksum=1b308ad687ad2a4095cd400d9ceb23fe (do not edit this line) */
