/* Generated By:JJTree: Do not edit this line. ASTStringLiteral.java Version 6.0 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=true,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package net.nokok.karaffe.parser;

public
class ASTStringLiteral extends SimpleNode {
  public ASTStringLiteral(int id) {
    super(id);
  }

  public ASTStringLiteral(Parser p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(ParserVisitor visitor, Object data) throws net.nokok.karaffe.parser.excptn.ParserException {

    return
    visitor.visit(this, data);
  }
}
/* JavaCC - OriginalChecksum=619b9e22bf791df26e6483fac2de3518 (do not edit this line) */
