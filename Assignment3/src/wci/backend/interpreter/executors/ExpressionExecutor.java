package wci.backend.interpreter.executors;

import java.util.*;

import wci.intermediate.*;
import wci.intermediate.icodeimpl.*;
import wci.backend.interpreter.*;
import wci.util.*;
import static wci.intermediate.symtabimpl.SymTabKeyImpl.*;
import static wci.intermediate.icodeimpl.ICodeNodeTypeImpl.*;
import static wci.intermediate.icodeimpl.ICodeKeyImpl.*;
import static wci.backend.interpreter.RuntimeErrorCode.*;

/**
 * <h1>ExpressionExecutor</h1>
 *
 * <p>Execute an expression.</p>
 *
 * <p>Copyright (c) 2009 by Ronald Mak</p>
 * <p>For instructional purposes only.  No warranties.</p>
 */
public class ExpressionExecutor extends StatementExecutor
{
    private static final int MAXIMUM_SET_VALUE = 50;

    /**
     * Constructor.
     * @param parent the parent executor.
     */
    public ExpressionExecutor(Executor parent)
    {
        super(parent);
    }

    /**
     * Execute an expression.
     * @param node the root intermediate code node of the compound statement.
     * @return the computed value of the expression.
     */
    public Object execute(ICodeNode node)
    {
        ICodeNodeTypeImpl nodeType = (ICodeNodeTypeImpl) node.getType();

        switch (nodeType) {

            case VARIABLE: {

                // Get the variable's symbol table entry and return its value.
                SymTabEntry entry = (SymTabEntry) node.getAttribute(ID);
                return entry.getAttribute(DATA_VALUE);
            }

            case INTEGER_CONSTANT: {

                // Return the integer value.
                return (Integer) node.getAttribute(VALUE);
            }

            case REAL_CONSTANT: {

                // Return the float value.
                return (Float) node.getAttribute(VALUE);
            }

            case STRING_CONSTANT: {

                // Return the string value.
                return (String) node.getAttribute(VALUE);
            }

            case NEGATE: {

                // Get the NEGATE node's expression node child.
                ArrayList<ICodeNode> children = node.getChildren();
                ICodeNode expressionNode = children.get(0);

                // Execute the expression and return the negative of its value.
                Object value = execute(expressionNode);
                if (value instanceof Integer) {
                    return -((Integer) value);
                }
                else {
                    return -((Float) value);
                }
            }

            case NOT: {

                // Get the NOT node's expression node child.
                ArrayList<ICodeNode> children = node.getChildren();
                ICodeNode expressionNode = children.get(0);

                // Execute the expression and return the "not" of its value.
                boolean value = (Boolean) execute(expressionNode);
                return !value;
            }

            
            case SET: {
            	/*
                List<ICodeNode> children = node.getChildren();
                Set<Integer> setContents = new HashSet<>();
                for (ICodeNode n : children) {
                    Object value = execute(n);
                    if (value instanceof HashSet) {
                        Set<Integer> rangeSet = (Set<Integer>) value;
                        setContents.addAll(rangeSet);
                    }
                    else {
                        setContents.add((Integer)value);
                    }
                }

                for (Integer i : setContents) {
                    if (i > 50) {
                        errorHandler.flag(node, INVALID_SET_VALUE, this);
                        return 0;
                    }
                }
				*/
                ICodeNode result = ICodeFactory.createICodeNode(SET);
                HashSet<Integer> uniques = new HashSet<Integer>();
                for(ICodeNode curr: node.getChildren()) {
                    Object add = execute(curr);
                    if(add instanceof Integer) {
                        uniques.add((int) add);
                    }
                    else if(add instanceof ICodeNode) {
                        ICodeNode set = (ICodeNode) add;
                        for(ICodeNode member: set.getChildren()) {
                            uniques.add((int) member.getAttribute(VALUE));
                        }
                    }
                }
                for(int i: uniques){
                    if (i > MAXIMUM_SET_VALUE) {
                        errorHandler.flag(node, INVALID_SET_VALUE, this);
                        System.exit(-1);
                    }
                    else {
                        ICodeNode member = ICodeFactory.createICodeNode(INTEGER_CONSTANT);
                        member.setAttribute(VALUE, i);
                        result.addChild(member);
                    }
                }
                return result;
            }
            
            
            /*
             * This the were we handle the .. that is parsed into a tree
             * by adding the set nodes into the range parent's children 
             * and return a value representing the set and transform it 
             * to a SET node that will contain all the children 
             */
            case RANGE: {
    	       	List<ICodeNode> children = node.getChildren();
            	int start = (int) execute(children.get(0));
            	int end = (int ) execute(children.get(1));
            	ICodeNode set = ICodeFactory.createICodeNode(ICodeNodeTypeImpl.SET);
            	for(Integer t = start; t <= end; t++) {
                	ICodeNode setMember = ICodeFactory.createICodeNode(ICodeNodeTypeImpl.INTEGER_CONSTANT);
                	setMember.setAttribute(VALUE, t);
                	set.addChild(setMember);
            	}
            	return set;
            }

            // Must be a binary operator.
            default: return executeBinaryOperator(node, nodeType);
        }
    }

    // Set of arithmetic operator node types.
    private static final EnumSet<ICodeNodeTypeImpl> ARITH_OPS =
        EnumSet.of(ADD, SUBTRACT, MULTIPLY, FLOAT_DIVIDE, INTEGER_DIVIDE, MOD, SET);
    
    private static final EnumSet<ICodeNodeTypeImpl> SET_OPS =
        EnumSet.of(SET_UNION, SET_DIFFERENCE, SET_INTERSECTION, SET_SYS_DIFFERENCE, SET_CONTAINS, SET_ISCONTAINED, SET_EQUALS, SET_IN);

    private HashSet<Integer> convertSetChildrenToHashSet(ArrayList<ICodeNode> setChildren) {
        HashSet<Integer> result = new HashSet<Integer>();
        for(ICodeNode i: setChildren) {
            result.add((int) i.getAttribute(VALUE));
        }
        return result;
    }

    private ICodeNode convertHashSetToSetNode(HashSet<Integer> set) {
        ICodeNode result = ICodeFactory.createICodeNode(SET);
        for(int i: set) {
            ICodeNode curr = ICodeFactory.createICodeNode(INTEGER_CONSTANT);
            curr.setAttribute(VALUE, i);
            result.addChild(curr);
        }
        return result;
    }

    private Object executeSetOp(ICodeNode op, Object first_operand, Object second_operand) {
        ICodeNodeType opType = op.getType();
        boolean areBothSets = first_operand instanceof ICodeNode && second_operand instanceof ICodeNode;
        if (areBothSets) {
            ArrayList<ICodeNode> set1Members = ((ICodeNode)first_operand).getChildren();
            ArrayList<ICodeNode> set2Members = ((ICodeNode)second_operand).getChildren();
            HashSet<Integer> s1 =  convertSetChildrenToHashSet(set1Members);
            HashSet<Integer> s2 = convertSetChildrenToHashSet(set2Members);
            HashSet<Integer> result = new HashSet<Integer>();
            if(opType == ADD) {

                for(Integer i: s1) {
                    result.add(i);
                }
                for(Integer i: s2) {
                    result.add(i);
                }
                return convertHashSetToSetNode(result);

            }
            else if(opType == SUBTRACT) {
                result = s1;
                for(int i: s2) {
                    if(result.contains(i)) result.remove(i);
                }
                return convertHashSetToSetNode(result);
            }
            else if(opType == MULTIPLY) {
                for(int i: s1) {
                    if(s2.contains(i)) {
                        result.add(i);
                    }
                }
                return convertHashSetToSetNode(result);
            }
            else if(opType == EQ) {
                if(s1.equals(s2)) {
                    return new Boolean(true);
                }
                return new Boolean(false);
            }
            else if(opType == LE) {
                for(int i: s1) {
                    if(!s2.contains(i)) return new Boolean(false);
                }
                return new Boolean(true);
            }
            else if(opType == GE) {
                for(int i: s2) {
                    if(!s1.contains(i)) return new Boolean(false);
                }
                return new Boolean(true);
            }
            else if(opType == NE) {
                if(!s1.equals(s2)) return new Boolean(true);
                return new Boolean(false);
            }
            else {
                errorHandler.flag(op, INVALID_OPERATION, this);
                return 0;
            }
        }
        else if (opType == SET_IN){
            // op type must be SET_IN because one operand must be a set and the other must be an int.
            HashSet<Integer> setOperand;
            int intOperand;
            if (first_operand instanceof ICodeNode) {
                setOperand = convertSetChildrenToHashSet(((ICodeNode) first_operand).getChildren());
                intOperand = (int)second_operand;
            }
            else {
                setOperand = convertSetChildrenToHashSet(((ICodeNode) second_operand).getChildren());
                intOperand = (int)first_operand;
            }

            return setOperand.contains(intOperand);
        }
        else {
            errorHandler.flag(op, INVALID_OPERATION, this);
            return 0;
        }
    }
    /**
     * Execute a binary operator.
     * @param node the root node of the expression.
     * @param nodeType the node type.
     * @return the computed value of the expression.
     */
    private Object executeBinaryOperator(ICodeNode node,
                                         ICodeNodeTypeImpl nodeType)
    {
        // Get the two operand children of the operator node.
        ArrayList<ICodeNode> children = node.getChildren();
        ICodeNode operandNode1 = children.get(0);
        ICodeNode operandNode2 = children.get(1);

        // Operands.
        Object operand1 = execute(operandNode1);
        Object operand2 = execute(operandNode2);

        boolean integerMode = (operand1 instanceof Integer) &&
                              (operand2 instanceof Integer);

        boolean setMode = (operand1 instanceof ICodeNode) || (operand2 instanceof ICodeNode);

        if(setMode)
            return executeSetOp(node, operand1, operand2);

        // ====================
        // Arithmetic operators
        // ====================

        if (ARITH_OPS.contains(nodeType)) {
            if (integerMode) {
                int value1 = (Integer) operand1;
                int value2 = (Integer) operand2;

                // Integer operations.
                switch (nodeType) {
                    case ADD:      return value1 + value2;
                    case SUBTRACT: return value1 - value2;
                    case MULTIPLY: return value1 * value2;

                    case FLOAT_DIVIDE: {

                        // Check for division by zero.
                        if (value2 != 0) {
                            return ((float) value1)/((float) value2);
                        }
                        else {
                            errorHandler.flag(node, DIVISION_BY_ZERO, this);
                            return 0;
                        }
                    }

                    case INTEGER_DIVIDE: {

                        // Check for division by zero.
                        if (value2 != 0) {
                            return value1/value2;
                        }
                        else {
                            errorHandler.flag(node, DIVISION_BY_ZERO, this);
                            return 0;
                        }
                    }

                    case MOD:  {

                        // Check for division by zero.
                        if (value2 != 0) {
                            return value1%value2;
                        }
                        else {
                            errorHandler.flag(node, DIVISION_BY_ZERO, this);
                            return 0;
                        }
                    }
                }
            }
            else {
                float value1 = operand1 instanceof Integer
                                   ? (Integer) operand1 : (Float) operand1;
                float value2 = operand2 instanceof Integer
                                   ? (Integer) operand2 : (Float) operand2;

                // Float operations.
                switch (nodeType) {
                    case ADD:      return value1 + value2;
                    case SUBTRACT: return value1 - value2;
                    case MULTIPLY: return value1 * value2;

                    case FLOAT_DIVIDE: {

                        // Check for division by zero.
                        if (value2 != 0.0f) {
                            return value1/value2;
                        }
                        else {
                            errorHandler.flag(node, DIVISION_BY_ZERO, this);
                            return 0.0f;
                        }
                    }
                }
            }
        }

        // ==========
        // AND and OR
        // ==========

        else if ((nodeType == AND) || (nodeType == OR)) {
            boolean value1 = (Boolean) operand1;
            boolean value2 = (Boolean) operand2;

            switch (nodeType) {
                case AND: return value1 && value2;
                case OR:  return value1 || value2;
            }
        }

        // ====================
        // Relational operators
        // ====================

        else if (integerMode) {
            int value1 = (Integer) operand1;
            int value2 = (Integer) operand2;

            // Integer operands.
            switch (nodeType) {
                case EQ: return value1 == value2;
                case NE: return value1 != value2;
                case LT: return value1 <  value2;
                case LE: return value1 <= value2;
                case GT: return value1 >  value2;
                case GE: return value1 >= value2;
                case RANGE: {
                    int leftSide = (Integer) operand1;
                    int rightSide = (Integer) operand2;

                    Set<Integer> rangeContents = new HashSet<>();
                    for (int i = leftSide; i <= rightSide; i++)
                        rangeContents.add(i);

                    return rangeContents;
                }
            }
        }
        else {
            float value1 = operand1 instanceof Integer
                               ? (Integer) operand1 : (Float) operand1;
            float value2 = operand2 instanceof Integer
                               ? (Integer) operand2 : (Float) operand2;

            // Float operands.
            switch (nodeType) {
                case EQ: return value1 == value2;
                case NE: return value1 != value2;
                case LT: return value1 <  value2;
                case LE: return value1 <= value2;
                case GT: return value1 >  value2;
                case GE: return value1 >= value2;
            }
        }

        return 0;  // should never get here
    }
}
