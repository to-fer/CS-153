package wci.intermediate;

import wci.frontend.ASTAssignment;
import wci.frontend.ASTCompound_stmt;
import wci.frontend.ASTStatement;
import wci.frontend.ASTadd;
import wci.frontend.ASTsubtract;
import wci.frontend.ASTmultiply;
import wci.frontend.ASTnumber;
import wci.frontend.ASTprogram;
import wci.frontend.ASTstring;
import wci.frontend.ASTdivide;
import wci.frontend.ASTerror;
import wci.frontend.ASTidentifier;
import wci.frontend.PclParserVisitor;
import wci.frontend.SimpleNode;
import wci.intermediate.symtabimpl.Predefined;

public class TSParserVisitorAdapter implements PclParserVisitor
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
	public Object visit(ASTCompound_stmt node, Object data) {
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
		return null;
	}



}