/* Generated By:JJTree: Do not edit this line. ASTTypeParameter.java Version 6.0 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=true,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package net.nokok.karaffe.parser;

public
class ASTTypeParameter extends SimpleNode {
  public ASTTypeParameter(int id) {
    super(id);
  }

  public ASTTypeParameter(Parser p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public void jjtAccept(ParserVisitor visitor, Object data) throws net.nokok.karaffe.parser.excptn.ParserException {

    visitor.visit(this, data);
  }
}
/* JavaCC - OriginalChecksum=49f2f617b392fddd57b93ddc465f6227 (do not edit this line) */
