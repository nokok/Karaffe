/* Generated By:JJTree: Do not edit this line. ASTInterfaceModifiers.java Version 6.0 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=true,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package net.nokok.karaffe.parser;

public
class ASTInterfaceModifiers extends SimpleNode {
  public ASTInterfaceModifiers(int id) {
    super(id);
  }

  public ASTInterfaceModifiers(Parser p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(ParserVisitor visitor, Object data) throws net.nokok.karaffe.parser.excptn.ParserException {

    return
    visitor.visit(this, data);
  }
}
/* JavaCC - OriginalChecksum=9db70ed8a462841f64c5d227f3c19b8b (do not edit this line) */
