/* Generated By:JJTree: Do not edit this line. ASTAssignmentExpression.java Version 6.0 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package net.nokok.karaffe.parser;

public
class ASTAssignmentExpression extends SimpleNode {
  public ASTAssignmentExpression(int id) {
    super(id);
  }

  public ASTAssignmentExpression(KaraffeParser p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(KaraffeParserVisitor visitor, Object data) throws net.nokok.karaffe.parser.excptn.KaraffeParserException {

    return
    visitor.visit(this, data);
  }
}
/* JavaCC - OriginalChecksum=1f1c3f79fbb0c2f659cce445cc6dcbce (do not edit this line) */
