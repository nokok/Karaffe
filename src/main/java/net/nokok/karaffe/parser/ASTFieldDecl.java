/* Generated By:JJTree: Do not edit this line. ASTFieldDecl.java Version 6.0 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=true,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package net.nokok.karaffe.parser;

public
class ASTFieldDecl extends SimpleNode {
  public ASTFieldDecl(int id) {
    super(id);
  }

  public ASTFieldDecl(Parser p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public void jjtAccept(ParserVisitor visitor, Object data) throws net.nokok.karaffe.parser.excptn.ParserException {

    visitor.visit(this, data);
  }
}
/* JavaCC - OriginalChecksum=23edcd80b87c324998cbe53be08ecc13 (do not edit this line) */
