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

        public Object visit(ASTadd node, Object data) {
            SimpleNode addend0Node = (SimpleNode) node.jjtGetChild(0);
            SimpleNode addend1Node = (SimpleNode) node.jjtGetChild(1);

            TypeSpec type = node.getTypeSpec();

            String typePrefix = TypeCode.typeSpecToTypeCode(type).toLowerCase();

            addend0Node.jjtAccept(this, data);
            addend1Node.jjtAccept(this, data);

            CodeGenerator.objectFile.println("      " + typePrefix + "add");
            CodeGenerator.objectFile.flush();

            return data;
        }

        public Object visit(ASTsubtract node, Object data) {
            SimpleNode addend0Node = (SimpleNode) node.jjtGetChild(0);
            SimpleNode addend1Node = (SimpleNode) node.jjtGetChild(1);

            TypeSpec type = node.getTypeSpec();
            String typePrefix = TypeCode.typeSpecToTypeCode(type).toLowerCase();

            addend0Node.jjtAccept(this, data);
            addend1Node.jjtAccept(this, data);

            CodeGenerator.objectFile.println("      " + typePrefix + "sub");
            CodeGenerator.objectFile.flush();

            return data;
        }

        public Object visit(ASTdivide node, Object data) {
            SimpleNode addend0Node = (SimpleNode) node.jjtGetChild(0);
            SimpleNode addend1Node = (SimpleNode) node.jjtGetChild(1);

            TypeSpec type = node.getTypeSpec();
            String typePrefix = TypeCode.typeSpecToTypeCode(type).toLowerCase();

            addend0Node.jjtAccept(this, data);
            addend1Node.jjtAccept(this, data);

            CodeGenerator.objectFile.println("      " + typePrefix + "div");
            CodeGenerator.objectFile.flush();

            return data;
        }

        public Object visit(ASTmultiply node, Object data) {
            SimpleNode addend0Node = (SimpleNode) node.jjtGetChild(0);
            SimpleNode addend1Node = (SimpleNode) node.jjtGetChild(1);

            TypeSpec type = node.getTypeSpec();
            String typePrefix = TypeCode.typeSpecToTypeCode(type).toLowerCase();

            addend0Node.jjtAccept(this, data);
            addend1Node.jjtAccept(this, data);

            CodeGenerator.objectFile.println("      " + typePrefix + "mul");
            CodeGenerator.objectFile.flush();

            return data;
        }

//        public Object visit(ASTprintln node, Object data) {
//            CodeGenerator.objectFile.println("      getstatic java/lang/System/out Ljava/io/PrintStream;");
//
//            SimpleNode nodeToPrint = (SimpleNode) node.jjtGetChild(0);
//            nodeToPrint.jjtAccept(this, data);
//
//            CodeGenerator.objectFile.println("      invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V");
//
//            return data;
//        }
        
        public Object visit(ASTprintln node, Object data) {
//          CodeGenerator.objectFile.println("      getstatic java/lang/System/out Ljava/io/PrintStream;");

          SimpleNode nodeToPrint = (SimpleNode) node.jjtGetChild(0);
//         System.out.println (nodeToPrint.toString());
          String typePrefix = TypeCode.typeSpecToTypeCode(nodeToPrint.getTypeSpec());
          SimpleNode addend0Node = (SimpleNode) node.jjtGetChild(0);
          SymTabEntry id = (SymTabEntry) addend0Node.getAttribute(ID);
//          System.out.println( "name " + id.getName());
//          System.out.println( id.getAttribute());
          if(typePrefix.equals("F")) {
        	  
//          	generate_float_print_code(nodeToPrint, data);
        	  	//generate code for printing an identifier to a float
          		if(nodeToPrint.toString().equals("identifier")) {
          			generate_float_print_code(id.getName(), 0f, data);
          		}
          		//generate code for printing number literal
          		else if(nodeToPrint.toString().equals("number")) {
//          			System.out.println("NUMBER!!!!!!!!!!!!");
//          			System.out.println("value "+nodeToPrint.getAttribute(VALUE).toString());
          			float val = Float.parseFloat( nodeToPrint.getAttribute(VALUE).toString() );
          			generate_float_print_code(null, val, data);
          		}
          }
          
//          nodeToPrint.jjtAccept(this, data);

//          CodeGenerator.objectFile.println("      invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V");

          return data;
      }
        
        public Object generate_float_print_code(String id, float value, Object data ) {
        	if(id != null) {
        	      //getstatic    java/lang/System/out Ljava/io/PrintStream;
        	      //getstatic     TypeScriptProgram/y F
        	      //invokestatic  java/lang/String.valueOf(F)Ljava/lang/String;
        	      //invokevirtual java/io/PrintStream.println(Ljava/lang/String;)V
        		CodeGenerator.objectFile.println("       getstatic    java/lang/System/out Ljava/io/PrintStream;");
        		CodeGenerator.objectFile.println("       getstatic     TypeScriptProgram/"+id+" F");
        		CodeGenerator.objectFile.println( "      invokestatic  java/lang/String.valueOf(F)Ljava/lang/String;");
        		CodeGenerator.objectFile.println("       invokevirtual java/io/PrintStream.println(Ljava/lang/String;)V");
        	}
        	else {
        		CodeGenerator.objectFile.println("       getstatic    java/lang/System/out Ljava/io/PrintStream;");
        		CodeGenerator.objectFile.println("       ldc "+value);
        		CodeGenerator.objectFile.println( "      invokestatic  java/lang/String.valueOf(F)Ljava/lang/String;");
        		CodeGenerator.objectFile.println("       invokevirtual java/io/PrintStream.println(Ljava/lang/String;)V");
        	}
        	return data;
        }
}