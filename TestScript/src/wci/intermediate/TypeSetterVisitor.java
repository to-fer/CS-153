package wci.intermediate;

import wci.frontend.*;
import wci.intermediate.*;
import wci.intermediate.symtabimpl.*;

public class TypeSetterVisitor extends TSParserVisitorAdapter
{
	   private void setType(SimpleNode node)
	    { /*
	        int count = node.jjtGetNumChildren();
	        TypeSpec type = Predefined.integerType;
	        
	        for (int i = 0; (i < count) && (type == Predefined.integerType); ++i) {
	            SimpleNode child = (SimpleNode) node.jjtGetChild(i);
	            TypeSpec childType = child.getTypeSpec();
	            
	            if (childType == Predefined.realType) {
	                type = Predefined.realType;
	            }
	        }
	        
	        node.setTypeSpec(type);
	        */
	    }
  
		public Object visit(SimpleNode node, Object data) {
	        return node.childrenAccept(this, data);
		}

		public Object visit(ASTprogram node, Object data) {
	        return node.childrenAccept(this, data);

		}

		public Object visit(ASTCompound_stmt node, Object data) {
	        return node.childrenAccept(this, data);

		}

		public Object visit(ASTAssignment node, Object data) {
	        return node.childrenAccept(this, data);

		}

		public Object visit(ASTadd node, Object data) {
	        return node.childrenAccept(this, data);

		}

		public Object visit(ASTsubtract node, Object data) {
	        return node.childrenAccept(this, data);

		}

		public Object visit(ASTmultiply node, Object data) {
	        return node.childrenAccept(this, data);

		}

		public Object visit(ASTdivide node, Object data) {
	        return node.childrenAccept(this, data);

		}

		public Object visit(ASTidentifier node, Object data) {
	        return node.childrenAccept(this, data);

		}

		public Object visit(ASTnumber node, Object data) {
	        return node.childrenAccept(this, data);

		}

		public Object visit(ASTstring node, Object data) {
	        return node.childrenAccept(this, data);
		}
}
