/* Generated By:JJTree: Do not edit this line. ASTExplicitCtorInvocation.java Version 6.0 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=true,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package net.nokok.karaffe.parser;

public
class ASTExplicitCtorInvocation extends SimpleNode {
  public ASTExplicitCtorInvocation(int id) {
    super(id);
  }

  public ASTExplicitCtorInvocation(Parser p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(ParserVisitor visitor, Object data) throws net.nokok.karaffe.parser.excptn.ParserException {

    return
    visitor.visit(this, data);
  }
}
/* JavaCC - OriginalChecksum=b389d1a782fc3838baf4de66902ab3fb (do not edit this line) */
