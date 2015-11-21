package Test;

public class TestTreeVisitorAdaptor implements TestTreeVisitor{
	public Object visit(SimpleNode node, Object data) 
	{
		System.out.println("Visiting SimpleNode");
		return node.childrenAccept(this, data);
	}

	public Object visit(ASTExpression node, Object data) 
	{
		System.out.println("Visiting ASTExpression node");
		return node.childrenAccept(this, data);
	}

	public Object visit(ASTOperator node, Object data) 
	{
		System.out.println("Visiting ASTOperator node");
		return node.childrenAccept(this, data);
	}

	public Object visit(ASTOperand node, Object data) 
	{
		System.out.println("Visiting ASTOperand node");
		return node.childrenAccept(this, data);
	}
}
