/* Generated By:JJTree: Do not edit this line. ASTSingleArrow.java Version 6.0 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package net.nokok.karaffe.parser;

public
class ASTSingleArrow extends SimpleNode {
  public ASTSingleArrow(int id) {
    super(id);
  }

  public ASTSingleArrow(KaraffeParser p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(KaraffeParserVisitor visitor, Object data) throws net.nokok.karaffe.parser.excptn.KaraffeParserException {

    return
    visitor.visit(this, data);
  }
}
/* JavaCC - OriginalChecksum=e4f1436d0cefd454e2027dab9a66d20e (do not edit this line) */
