package wci.backend.compiler;

import wci.frontend.*;
import wci.intermediate.*;
import wci.intermediate.symtabimpl.Predefined;

import static wci.intermediate.icodeimpl.ICodeKeyImpl.*;

public class CodeGeneratorVisitor
        extends TSParserVisitorAdapter
        implements PclParserTreeConstants {

        /*
         Note that once we implement functions, this will have to be changed because we currently implement identifiers
         only at the global level. There are implemented as fields. It's not feasible to implement function local
         variables as fields.

         We may even need to stop implementing global variables as fields and do it some other way so we can
         retrieve local variables in functions and global variables in a uniform way.
          */
        public Object visit(ASTidentifier node, Object data) {
                SymTabEntry id = (SymTabEntry) node.getAttribute(ID);
                String identifier = id.getName();
                TypeSpec type = id.getTypeSpec();
                String typeCode = TypeCode.typeSpecToTypeCode(type);

                CodeGenerator.objectFile.println("      getstatic " + CodeGenerator.PROGRAM_HEADER_CLASS_NAME +
                                                 "/" + identifier + " " + typeCode);
                return data;
        }

        public Object visit(ASTAssignment node, Object data) {
                SimpleNode variableNode   = (SimpleNode) node.jjtGetChild(0);
                SimpleNode expressionNode = (SimpleNode) node.jjtGetChild(1);

                expressionNode.jjtAccept(this, data);

                SymTabEntry id = (SymTabEntry) variableNode.getAttribute(ID);
                String fieldName = id.getName();
                TypeSpec type = id.getTypeSpec();
                String typeCode = TypeCode.typeSpecToTypeCode(type);

                // Emit the appropriate store instruction.
                CodeGenerator.objectFile.println("      putstatic " + CodeGenerator.PROGRAM_HEADER_CLASS_NAME +
                        "/" + fieldName + " " + typeCode);
                CodeGenerator.objectFile.flush();

                return data;
        }

        public Object visit(ASTnumber node, Object data) {
            double value = (Double) node.getAttribute(VALUE);
            CodeGenerator.objectFile.println("      ldc " + value);
            CodeGenerator.objectFile.flush();

            return data;
        }

        public Object visit(ASTstring node, Object data) {
            String value = (String) node.getAttribute(VALUE);
            CodeGenerator.objectFile.println("      ldc " + value);
            CodeGenerator.objectFile.flush();

            return data;
        }

    // TODO Implement code generation for each of the types of AST nodes via visit methods here.
}