/* Generated By:JJTree: Do not edit this line. ASTHexadecimalFPLiteral.java Version 6.0 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=true,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package net.nokok.karaffe.parser;

public
class ASTHexadecimalFPLiteral extends SimpleNode {
  public ASTHexadecimalFPLiteral(int id) {
    super(id);
  }

  public ASTHexadecimalFPLiteral(Parser p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(ParserVisitor visitor, Object data) throws net.nokok.karaffe.parser.excptn.ParserException {

    return
    visitor.visit(this, data);
  }
}
/* JavaCC - OriginalChecksum=9afb0d95ee32fdc5696dea17c24f10f8 (do not edit this line) */
