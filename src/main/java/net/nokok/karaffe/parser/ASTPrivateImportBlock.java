/* Generated By:JJTree: Do not edit this line. ASTPrivateImportBlock.java Version 6.0 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=true,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package net.nokok.karaffe.parser;

public
class ASTPrivateImportBlock extends SimpleNode {
  public ASTPrivateImportBlock(int id) {
    super(id);
  }

  public ASTPrivateImportBlock(Parser p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public void jjtAccept(ParserVisitor visitor, Object data) throws net.nokok.karaffe.parser.excptn.ParserException {

    visitor.visit(this, data);
  }
}
/* JavaCC - OriginalChecksum=5b4810b4ae64715340ff8e156cfb5769 (do not edit this line) */
