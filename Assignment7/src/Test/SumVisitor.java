package Test;

public class SumVisitor extends TestTreeVisitorAdaptor 
{
	public int sum;

	public Object visit(ASTOperand operand, Object data) 
	{
		Integer value = (Integer) operand.jjtGetValue();
		
		System.out.println("SumVisitor visiting ASTOperand node: " +
	                       "adding " + value.intValue() + " to " + sum);
		
		sum += value;
		return super.visit(operand, data);
	}
}