package wci.backend.compiler;

import wci.frontend.*;
import wci.intermediate.*;
import wci.intermediate.symtabimpl.Predefined;

import static wci.intermediate.icodeimpl.ICodeKeyImpl.*;

public class CodeGeneratorVisitor
        extends TSParserVisitorAdapter
        implements PclParserTreeConstants {
	
		int label_count = 0;
		String label_suffix = "Label";
		int empty_count = 0;

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
                        "/" + identifier + " " + typeCode + CodeGenerator.writeComment("identifier"));

                return data;
        }

        public Object visit(ASTAssignment node, Object data) {
        		System.out.println("STARTED ASSIGNMENT");
                SimpleNode variableNode   = (SimpleNode) node.jjtGetChild(0);
                SimpleNode expressionNode = (SimpleNode) node.jjtGetChild(1);
                System.out.println("BEFORE EXPRESSION!!");
                SymTabEntry id1 = (SymTabEntry) variableNode.getAttribute(ID);
                String fieldName1 = id1.getName();
                System.out.println("FIELD "+fieldName1);
                TypeSpec type1 = id1.getTypeSpec();
                String typeCode1 = TypeCode.typeSpecToTypeCode(type1);
                System.out.println("EXP type: "+typeCode1);
                expressionNode.jjtAccept(this, data);
                System.out.println("AFTER EXPRESSION!!");

                SymTabEntry id = (SymTabEntry) variableNode.getAttribute(ID);
                String fieldName = id.getName();
                System.out.println("FIELD "+fieldName);
                TypeSpec type = id.getTypeSpec();
                String typeCode = TypeCode.typeSpecToTypeCode(type);

                // Emit the appropriate store instruction.
                CodeGenerator.objectFile.println("      putstatic " + CodeGenerator.PROGRAM_HEADER_CLASS_NAME +
                        "/" + fieldName + " " + typeCode + CodeGenerator.writeComment("pop value: assingment_node"));
                CodeGenerator.objectFile.flush();
                System.out.println("done with assignment!!!!");
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
            System.out.println("TYPE PREFIX "+typePrefix);
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
        
        public Object visit(ASTprintln node, Object data) {
          SimpleNode nodeToPrint = (SimpleNode) node.jjtGetChild(0);
          String typePrefix = TypeCode.typeSpecToTypeCode(nodeToPrint.getTypeSpec());
          SimpleNode addend0Node = (SimpleNode) node.jjtGetChild(0);
          SymTabEntry id = (SymTabEntry) addend0Node.getAttribute(ID);

          if(typePrefix.equals("F")) {
          		if(nodeToPrint.toString().equals("identifier")) {
          			generate_float_print_code(id.getName(), 0f, data);
          		}
          		else if(nodeToPrint.toString().equals("number")) {
          			float val = Float.parseFloat( nodeToPrint.getAttribute(VALUE).toString() );
          			generate_float_print_code(null, val, data);
          		}
          }
          if(typePrefix.equals("Ljava/lang/String;")) {
        	  System.out.println("FOUND A STRING!!!!!");
        	  if(nodeToPrint.toString().equals("identifier")) {
        			System.out.println("found string var!!!!!");
        			generate_string_print_code(id.getName(), "", data);
        	  }
        	  else {
        		  System.out.println("found string literal!!!!!");
        		  String val = nodeToPrint.getAttribute(VALUE).toString();
        		  generate_string_print_code(null, val, data);
        	  }
          }
          if(typePrefix.equals("Z")) {
        	  if( id.getName().equals("true") )
        	  {
        		  System.out.println("generating true print code");
        		  generate_string_print_code(null, "\"true\"", data);
        	  }
        	  else if( id.getName().equals("false") ) generate_string_print_code(null, "\"false\"", data);
        	  else generate_bool_var_print_code(id.getName(), data);
          }
          
          return data;
      }
        public Object generate_bool_var_print_code(String id, Object data) {
        	CodeGenerator.objectFile.println("       getstatic    java/lang/System/out Ljava/io/PrintStream;");
    		CodeGenerator.objectFile.println("       getstatic     TypeScriptProgram/"+id+" Z");
    		CodeGenerator.objectFile.println( "      invokestatic  java/lang/String.valueOf(Z)Ljava/lang/String;");
    		CodeGenerator.objectFile.println("       invokevirtual java/io/PrintStream.println(Ljava/lang/String;)V");
        	return data;
        }
        
        public Object generate_string_print_code(String id, String val, Object data) {
        	if(id != null) {
      	      //getstatic    java/lang/System/out Ljava/io/PrintStream;
      	      //getstatic     TypeScriptProgram/y F
      	      //invokestatic  java/lang/String.valueOf(F)Ljava/lang/String;
      	      //invokevirtual java/io/PrintStream.println(Ljava/lang/String;)V
      		CodeGenerator.objectFile.println("       getstatic    java/lang/System/out Ljava/io/PrintStream;");
      		CodeGenerator.objectFile.println("       getstatic     TypeScriptProgram/"+id+" Ljava/lang/String;");
//      		CodeGenerator.objectFile.println( "      invokestatic  java/lang/String.valueOf(F)Ljava/lang/String;");
      		CodeGenerator.objectFile.println("       invokevirtual java/io/PrintStream.println(Ljava/lang/String;)V");
	      	}
	      	else {
	      		CodeGenerator.objectFile.println("       getstatic    java/lang/System/out Ljava/io/PrintStream;");
	      		CodeGenerator.objectFile.println("       ldc "+val);
//	      		CodeGenerator.objectFile.println( "      invokestatic  java/lang/String.valueOf(F)Ljava/lang/String;");
	      		CodeGenerator.objectFile.println("       invokevirtual java/io/PrintStream.println(Ljava/lang/String;)V");
	      	}
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
        //LT GT LE GE DOUBLE_EQ NOT_EQ OR AND
        public Object visit(ASTboolean_op node, Object data) {
        	String boolOpString = "       ";
        	String op = ( (SimpleNode) node.jjtGetChild(0)).toString();
        	System.out.println("OP "+op);
        	if(op.equals("LESS_THAN")) {
        		boolOpString = "fcmpl \n";
        		boolOpString += "ifge "+label_suffix + ++label_count;
        	}
        	else if(op.equals("GREATER_THAN")) {
        		boolOpString = "fcmpg \n";
        		boolOpString += "ifle "+label_suffix + ++label_count;
        	}
        	else if(op.equals("LESS_THAN_OR_EQUALS")) {
        		boolOpString = "fcmpl \n";
        		boolOpString += "ifgt "+label_suffix + ++label_count;
        	}
        	else if(op.equals("GREATER_THAN_OR_EQUALS")) {
        		boolOpString = "fcmpg \n";
        		boolOpString += "iflt "+label_suffix+ ++label_count;
        	}
        	else if(op.equals("EQUALITY")) {
        		boolOpString = "fcmpg \n";
        		boolOpString += "ifne "+label_suffix+ ++label_count;
        	}
        	else if(op.equals("NOT_EQUALS")) {
        		boolOpString = "fcmpg \n";
        		boolOpString += "ifeq "+label_suffix+ ++label_count;
        	}
        	CodeGenerator.objectFile.println(boolOpString);
        	return data;
        }
        
        public Object visit(ASTcondition node, Object data) {
        	SimpleNode exp1 = (SimpleNode) node.jjtGetChild(0);
        	SimpleNode op = (SimpleNode) node.jjtGetChild(1);
        	SimpleNode exp2 = (SimpleNode) node.jjtGetChild(2);
        	exp1.jjtAccept(this, data);
        	exp2.jjtAccept(this, data);
        	op.jjtAccept(this, data);
        	return data;
        }
        
        public Object visit(ASTif_body node, Object data) {
        	if(node.jjtGetNumChildren() > 0) {
        	SimpleNode body = (SimpleNode) node.jjtGetChild(0);
        	body.jjtAccept(this, data);
        	}
        	return data;
        }
        
        public Object visit(ASTCompound_stmt node, Object data) {
        	System.out.println("VISITED COMPUND STMT");
        	for(int i = 0; i < node.jjtGetNumChildren(); i++) {
        		SimpleNode curr = (SimpleNode) node.jjtGetChild(i);
        		curr.jjtAccept(this, data);
        		System.out.println(i);
        	}
        	System.out.println("EXITED COMPOUND STMT");
        	return data;
        }
        
        public Object visit(ASTelse_part node, Object data) {
        	if(node.jjtGetNumChildren() > 0) {
            	SimpleNode body = (SimpleNode) node.jjtGetChild(0);
            	body.jjtAccept(this, data);
            }
        	return data;
        }
        
        public Object visit(ASTif_part node, Object data) {
        	SimpleNode condition = (SimpleNode) node.jjtGetChild(0);
        	SimpleNode body = (SimpleNode) node.jjtGetChild(1);
        	condition.jjtAccept(this, data);
        	CodeGenerator.objectFile.println(label_suffix+label_count+":");
        	body.jjtAccept(this, data);
        	return data;
        }
        
        public Object visit(ASTif_stmt node, Object data) {
//        	SimpleNode if_part = (SimpleNode) node.jjtGetChild(0);
//        	if_part.jjtAccept(this, data);
//        	if(node.jjtGetNumChildren() > 1){
//        		SimpleNode else_part = (SimpleNode) node.jjtGetChild(1);
//        		else_part.jjtAccept(this, data);
//        	}
        	
        	SimpleNode condition = (SimpleNode) node.jjtGetChild(0).jjtGetChild(0);
        	SimpleNode branch1 = (SimpleNode) node.jjtGetChild(0).jjtGetChild(1);
        	SimpleNode branch2 = (SimpleNode) node.jjtGetChild(1);
        	condition.jjtAccept(this, data);
        	branch1.jjtAccept(this, data);
        	CodeGenerator.objectFile.println("goto "+"Empty"+ ++empty_count);
        	CodeGenerator.objectFile.println(label_suffix+label_count+":");
        	branch2.jjtAccept(this, data);
        	CodeGenerator.objectFile.println("Empty"+empty_count+":");
        	return data;
        }
        
        public Object visit(ASTboolean_node node, Object data) {
        	SymTabEntry id = (SymTabEntry) node.getAttribute(ID);
            String identifier = id.getName();
        	if(identifier.equals("true")) {
        		CodeGenerator.objectFile.println("ldc 1" + CodeGenerator.writeComment("push boolean value:boolean_node"));
        	}
        	else if(identifier.equals("false")) {
        		CodeGenerator.objectFile.println("ldc 0" + CodeGenerator.writeComment("push boolean value:boolean_node"));
        	}
        	return data;
        }
        
        public Object visit(ASTwhile_node node, Object data) {
        	System.out.println("WHILE has "+node.jjtGetNumChildren());
        	SimpleNode condition = (SimpleNode) node.jjtGetChild(0);
        	condition.jjtAccept(this, data);
        	CodeGenerator.objectFile.println("goto "+"Empty"+ ++empty_count);
        	CodeGenerator.objectFile.println(label_suffix+label_count+":");
        	SimpleNode body = (SimpleNode) node.jjtGetChild(1);
        	body.jjtAccept(this, data);
        	//CodeGenerator.objectFile.println("goto "+label_suffix+label_count);
        	//CodeGenerator.objectFile.println("Empty"+empty_count+":");
        	return data;
        }
        
}