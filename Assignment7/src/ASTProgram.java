/* Generated By:JJTree: Do not edit this line. ASTProgram.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
public
class ASTProgram extends SimpleNode {
  public ASTProgram(int id) {
    super(id);
  }

  public ASTProgram(Logo p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(LogoVisitor visitor, Object data) {
    return visitor.visit(this, data);
  }
}
/* JavaCC - OriginalChecksum=a50e645a92af74a4ba703a3731bb7985 (do not edit this line) */