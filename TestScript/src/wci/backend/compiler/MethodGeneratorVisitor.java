package wci.backend.compiler;

import static wci.intermediate.icodeimpl.ICodeKeyImpl.ID;
import static wci.intermediate.icodeimpl.ICodeKeyImpl.IS_WHILE;
import static wci.intermediate.icodeimpl.ICodeKeyImpl.VALUE;

import wci.frontend.*;
import wci.intermediate.SymTabEntry;
import wci.intermediate.TSParserVisitorAdapter;
import wci.intermediate.TypeSpec;

public class MethodGeneratorVisitor
		extends TSParserVisitorAdapter 
			implements TypeScriptParserTreeConstants {
	

    /**
     * Assumes all function are void
     */
	public Object visit(ASTFunctionDeclaration node, Object data) {
    	SymTabEntry id = (SymTabEntry) node.getAttribute(ID);
    	String funcName = id.getName();
    	
    	CodeGenerator.objectFile.println();
    	//generate function code
    	CodeGenerator.objectFile.println(".method public static " +  funcName + "()V");
		SimpleNode functionCode = (SimpleNode) node.jjtGetChild(0);
		CodeGeneratorVisitor codeGen = new CodeGeneratorVisitor();
		functionCode.jjtAccept(codeGen, null);
		functionCode.jjtAccept(this,data);
		
		//finish off function
		CodeGenerator.objectFile.println("return");
		CodeGenerator.objectFile.println(".limit locals 32");
		CodeGenerator.objectFile.println(".limit stack 40");
		CodeGenerator.objectFile.println(".end method");
    	CodeGenerator.objectFile.println();
		return data;
	}

}

