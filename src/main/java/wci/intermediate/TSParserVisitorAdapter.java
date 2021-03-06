package wci.intermediate;


import wci.frontend.*;

public class TSParserVisitorAdapter implements TypeScriptParserVisitor
{

	@Override
	public Object visit(SimpleNode node, Object data) {
        return node.childrenAccept(this, data);
	}

	@Override
	public Object visit(ASTprogram node, Object data) {
        return node.childrenAccept(this, data);

	}

	@Override
	public Object visit(ASTLESS_THAN node, Object data) {
        return node.childrenAccept(this, data);
	}

	@Override
	public Object visit(ASTGREATER_THAN node, Object data) {
        return node.childrenAccept(this, data);
	}

	@Override
	public Object visit(ASTLESS_THAN_OR_EQUALS node, Object data) {
        return node.childrenAccept(this, data);
	}

	@Override
	public Object visit(ASTGREATER_THAN_OR_EQUALS node, Object data) {
        return node.childrenAccept(this, data);
	}

	@Override
	public Object visit(ASTEQUALITY node, Object data) {
        return node.childrenAccept(this, data);
	}

	@Override
	public Object visit(ASTOR node, Object data) {
        return node.childrenAccept(this, data);
	}

	@Override
	public Object visit(ASTAND node, Object data) {
        return node.childrenAccept(this, data);
	}

	@Override
	public Object visit(ASTNOT_EQUALS node, Object data) {
        return node.childrenAccept(this, data);
	}

	@Override
	public Object visit(ASTif_part node, Object data) {
        return node.childrenAccept(this, data);
	}

	@Override
	public Object visit(ASTelse_part node, Object data) {
        return node.childrenAccept(this, data);
	}

	@Override
	public Object visit(ASTif_body node, Object data) {
        return node.childrenAccept(this, data);
	}

	@Override
	public Object visit(ASTif_stmt node, Object data) {
        return node.childrenAccept(this, data);
	}

	@Override
	public Object visit(ASTprintln node, Object data) {
        return node.childrenAccept(this, data);
	}

	@Override
	public Object visit(ASTPureFunction node, Object data) {
        return node.childrenAccept(this, data);
	}

	@Override
	public Object visit(ASTFunctionDeclaration node, Object data) {
        return node.childrenAccept(this, data);
	}

	@Override
	public Object visit(ASTFunction_invocation node, Object data) {
        return node.childrenAccept(this, data);
	}

	@Override
	public Object visit(ASTCompound_stmt node, Object data) {
        return node.childrenAccept(this, data);

	}

	@Override
	public Object visit(ASTdeclaration node, Object data) {
        return node.childrenAccept(this, data);
	}


	@Override
	public Object visit(ASTAssignment node, Object data) {
        return node.childrenAccept(this, data);

	}

	@Override
	public Object visit(ASTadd node, Object data) {
        return node.childrenAccept(this, data);

	}

	@Override
	public Object visit(ASTsubtract node, Object data) {
        return node.childrenAccept(this, data);

	}

	@Override
	public Object visit(ASTmultiply node, Object data) {
        return node.childrenAccept(this, data);

	}

	@Override
	public Object visit(ASTdivide node, Object data) {
        return node.childrenAccept(this, data);

	}

	@Override
	public Object visit(ASTidentifier node, Object data) {
        return node.childrenAccept(this, data);

	}

	@Override
	public Object visit(ASTnumber node, Object data) {
        return node.childrenAccept(this, data);

	}

	@Override
	public Object visit(ASTstring node, Object data) {
        return node.childrenAccept(this, data);
	}
	@Override
	public Object visit(ASTerror node, Object data) {
		// TODO Auto-generated method stub
        return node.childrenAccept(this, data);
	}

	@Override
	public Object visit(ASTcondition node, Object data) {
		// TODO Auto-generated method stub
        return node.childrenAccept(this, data);
	}

	@Override
	public Object visit(ASTboolean_op node, Object data) {
		// TODO Auto-generated method stub
        return node.childrenAccept(this, data);
	}

	@Override
	public Object visit(ASTboolean_node node, Object data) {
		// TODO Auto-generated method stub
        return node.childrenAccept(this, data);
	}


	@Override
	public Object visit(ASTwhile_node node, Object data) {
		// TODO Auto-generated method stub
        return node.childrenAccept(this, data);
	}

	@Override
	public Object visit(ASThandleError node, Object data) {
		// TODO Auto-generated method stub
		return null;
	}

}