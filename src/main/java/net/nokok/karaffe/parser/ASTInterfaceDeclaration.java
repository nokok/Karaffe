/* Generated By:JJTree: Do not edit this line. ASTInterfaceDeclaration.java Version 6.0 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package net.nokok.karaffe.parser;

public
class ASTInterfaceDeclaration extends SimpleNode {
  public ASTInterfaceDeclaration(int id) {
    super(id);
  }

  public ASTInterfaceDeclaration(KaraffeParser p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(KaraffeParserVisitor visitor, Object data) throws net.nokok.karaffe.parser.excptn.KaraffeParserException {

    return
    visitor.visit(this, data);
  }
}
/* JavaCC - OriginalChecksum=f3e6956a57a4c41942b709d0e3478916 (do not edit this line) */
