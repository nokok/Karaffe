/* Generated By:JJTree: Do not edit this line. ASTEnumModifier.java Version 6.0 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=true,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package net.nokok.karaffe.parser;

public
class ASTEnumModifier extends SimpleNode {
  public ASTEnumModifier(int id) {
    super(id);
  }

  public ASTEnumModifier(Parser p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public void jjtAccept(ParserVisitor visitor, Object data) throws net.nokok.karaffe.parser.excptn.ParserException {

    visitor.visit(this, data);
  }
}
/* JavaCC - OriginalChecksum=d1e5b8a97bd0447d1793349fb374ef5f (do not edit this line) */
