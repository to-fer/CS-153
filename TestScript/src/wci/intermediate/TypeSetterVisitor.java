package wci.intermediate;

import wci.frontend.*;
import wci.intermediate.symtabimpl.*;

public class TypeSetterVisitor extends TSParserVisitorAdapter
{
	   private void setType(SimpleNode node)
	    { 
		   try {
	        TypeSpec type = Predefined.numberType;

	        SimpleNode child = (SimpleNode) node.jjtGetChild(0);	 
            TypeSpec childType = child.getTypeSpec();

	        if (childType == Predefined.numberType) {
                type = Predefined.numberType;
            } else if(childType == Predefined.booleanType) {
            	type = Predefined.booleanType;
            } else if(childType == Predefined.charType) {
            	type = Predefined.charType;
            } else {
            	type = Predefined.undefinedType;
            }
	        node.setTypeSpec(type);

		   } catch (Exception e) {
			   System.out.println("Node bay not have child in TypeSetterVisitor");
		        node.setTypeSpec(Predefined.undefinedType);
		   }
		   
	    }
  
		public Object visit(SimpleNode node, Object data) {
	        Object obj = super.visit(node, data);
	        setType(node);
	        return obj;		}

		public Object visit(ASTprogram node, Object data) {
	        Object obj = super.visit(node, data);
	        setType(node);
	        return obj;
		}

		public Object visit(ASTCompound_stmt node, Object data) {
	        Object obj = super.visit(node, data);
	        setType(node);
	        return obj;
		}

		public Object visit(ASTAssignment node, Object data) {
	        Object obj = super.visit(node, data);
	        setType(node);
	        return obj;
		}

		public Object visit(ASTadd node, Object data) {
	        Object obj = super.visit(node, data);
	        setType(node);
	        return obj;
		}

		public Object visit(ASTsubtract node, Object data) {
	        Object obj = super.visit(node, data);
	        setType(node);
	        return obj;
		}

		public Object visit(ASTmultiply node, Object data) {
	        Object obj = super.visit(node, data);
	        setType(node);
	        return obj;
		}

		public Object visit(ASTdivide node, Object data) {
	        Object obj = super.visit(node, data);
	        setType(node);
	        return obj;
		}

		public Object visit(ASTidentifier node, Object data) {
	        return data;

		}

		public Object visit(ASTnumber node, Object data) {
	        return data;

		}

		public Object visit(ASTstring node, Object data) {
	        return data;
		}
}
