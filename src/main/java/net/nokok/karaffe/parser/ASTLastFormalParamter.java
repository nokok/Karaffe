/* Generated By:JJTree: Do not edit this line. ASTLastFormalParamter.java Version 6.0 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=true,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package net.nokok.karaffe.parser;

public
class ASTLastFormalParamter extends SimpleNode {
  public ASTLastFormalParamter(int id) {
    super(id);
  }

  public ASTLastFormalParamter(Parser p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public void jjtAccept(ParserVisitor visitor, Object data) throws net.nokok.karaffe.parser.excptn.ParserException {

    visitor.visit(this, data);
  }
}
/* JavaCC - OriginalChecksum=28d8e597eb8bc4f3f9a2136cf308d3f1 (do not edit this line) */
