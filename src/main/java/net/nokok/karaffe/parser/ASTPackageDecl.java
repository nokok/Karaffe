/* Generated By:JJTree: Do not edit this line. ASTPackageDecl.java Version 6.0 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=true,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package net.nokok.karaffe.parser;

public
class ASTPackageDecl extends SimpleNode {
  public ASTPackageDecl(int id) {
    super(id);
  }

  public ASTPackageDecl(Parser p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public void jjtAccept(ParserVisitor visitor, Object data) throws net.nokok.karaffe.parser.excptn.ParserException {

    visitor.visit(this, data);
  }
}
/* JavaCC - OriginalChecksum=dcc48ee9a7e72ecc95af839f0fafc409 (do not edit this line) */
