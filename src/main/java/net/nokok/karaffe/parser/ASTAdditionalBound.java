/* Generated By:JJTree: Do not edit this line. ASTAdditionalBound.java Version 6.0 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=true,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package net.nokok.karaffe.parser;

public
class ASTAdditionalBound extends SimpleNode {
  public ASTAdditionalBound(int id) {
    super(id);
  }

  public ASTAdditionalBound(Parser p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public void jjtAccept(ParserVisitor visitor, Object data) throws net.nokok.karaffe.parser.excptn.ParserException {

    visitor.visit(this, data);
  }
}
/* JavaCC - OriginalChecksum=b746ea58565331b22ee9e4c50ae0e7f4 (do not edit this line) */
