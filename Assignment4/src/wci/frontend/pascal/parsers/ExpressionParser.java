package wci.frontend.pascal.parsers;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;

import wci.frontend.*;
import wci.frontend.pascal.*;
import wci.intermediate.symtabimpl.*;
import wci.intermediate.*;
import wci.intermediate.icodeimpl.*;
import wci.intermediate.typeimpl.*;

import static wci.frontend.pascal.PascalTokenType.*;
import static wci.frontend.pascal.PascalErrorCode.*;
import static wci.intermediate.icodeimpl.ICodeNodeTypeImpl.SET;
import static wci.intermediate.icodeimpl.ICodeNodeTypeImpl.VARIABLE;
import static wci.intermediate.symtabimpl.SymTabKeyImpl.*;
import static wci.intermediate.symtabimpl.DefinitionImpl.*;
import static wci.intermediate.icodeimpl.ICodeNodeTypeImpl.*;
import static wci.intermediate.icodeimpl.ICodeKeyImpl.*;

/**
 * <h1>ExpressionParser</h1>
 *
 * <p>Parse a Pascal expression.</p>
 *
 * <p>Copyright (c) 2009 by Ronald Mak</p>
 * <p>For instructional purposes only.  No warranties.</p>
 */
public class ExpressionParser extends StatementParser
{
    /**
     * Constructor.
     * @param parent the parent parser.
     */
    public ExpressionParser(PascalParserTD parent)
    {
        super(parent);
    }

    private static final int MIN_SET_ELEMENT_VALUE = 0;
    private static final int MAX_SET_ELEMENT_VALUE = 50;

    private static final EnumSet<ICodeNodeTypeImpl> NODES_THAT_CAN_BE_INTEGER =
            EnumSet.of(
                    INTEGER_CONSTANT, VARIABLE, ADD,
                    SUBTRACT, MULTIPLY, INTEGER_DIVIDE, ICodeNodeTypeImpl.MOD);

    private static final EnumSet<ICodeNodeTypeImpl> NODES_THAT_CAN_BE_SET =
            EnumSet.of(
                    SET, VARIABLE, ADD, SUBTRACT,
                    MULTIPLY, LE, GE, SET_IN, EQ
            );

    // Synchronization set for starting an expression.
    static final EnumSet<PascalTokenType> EXPR_START_SET =
        EnumSet.of(PLUS, MINUS, IDENTIFIER, INTEGER, REAL, STRING,
                   PascalTokenType.NOT, LEFT_PAREN);

    private static final EnumSet<PascalTokenType> SET_OPS =
            EnumSet.of(PLUS, MINUS, LESS_EQUALS, GREATER_EQUALS, STAR, EQUALS, IN, NOT_EQUALS);

    private boolean isOperatingOnSetWithBadOperator(ICodeNode node, TokenType type) {
        return node != null && node.getType() == SET && !SET_OPS.contains(type);
    }

    /**
     * Parse an expression.
     * @param token the initial token.
     * @return the root node of the generated parse tree.
     * @throws Exception if an error occurred.
     */
    public ICodeNode parse(Token token)
        throws Exception
    {
        return parseExpression(token);
    }

    // Set of relational operators.
    private static final EnumSet<PascalTokenType> REL_OPS =
        EnumSet.of(EQUALS, NOT_EQUALS, LESS_THAN, LESS_EQUALS,
                   GREATER_THAN, GREATER_EQUALS, IN);

    // Map relational operator tokens to node types.
    private static final HashMap<PascalTokenType, ICodeNodeType>
        REL_OPS_MAP = new HashMap<PascalTokenType, ICodeNodeType>();
    static {
        REL_OPS_MAP.put(EQUALS, EQ);
        REL_OPS_MAP.put(NOT_EQUALS, NE);
        REL_OPS_MAP.put(LESS_THAN, LT);
        REL_OPS_MAP.put(LESS_EQUALS, LE);
        REL_OPS_MAP.put(GREATER_THAN, GT);
        REL_OPS_MAP.put(GREATER_EQUALS, GE);
        REL_OPS_MAP.put(IN, SET_IN);
    };

    /**
     * Parse an expression.
     * @param token the initial token.
     * @return the root node of the generated parse tree.
     * @throws Exception if an error occurred.
     */
    private ICodeNode parseExpression(Token token)
        throws Exception
    {
        // Parse a simple expression and make the root of its tree
        // the root node.
        ICodeNode rootNode = parseSimpleExpression(token);
        TypeSpec resultType = rootNode != null ? rootNode.getTypeSpec()
                                               : Predefined.undefinedType;

        Token previousToken = token;
        token = currentToken();
        TokenType tokenType = token.getType();

        if (tokenType == DOT_DOT) {
            ICodeNode rangeStart = rootNode;
            rootNode = ICodeFactory.createICodeNode(RANGE);

            token = nextToken();
            ICodeNode rangeEnd = parseSimpleExpression(token);

            rootNode.addChild(rangeStart);
            rootNode.addChild(rangeEnd);
        }
        else if (REL_OPS.contains(tokenType)) {

            if (isOperatingOnSetWithBadOperator(rootNode, tokenType)) {
                errorHandler.flag(previousToken, PascalErrorCode.INVALID_TYPE, this);
            }
            else if (tokenType == IN) {
                if (rootNode != null && !NODES_THAT_CAN_BE_INTEGER.contains(rootNode.getType())) {
                    errorHandler.flag(previousToken, PascalErrorCode.IN_LEFT_OPERAND_MUST_BE_INTEGER, this);
                }
            }

            // Create a new operator node and adopt the current tree
            // as its first child.
            ICodeNodeType nodeType = REL_OPS_MAP.get(tokenType);
            ICodeNode opNode = ICodeFactory.createICodeNode(nodeType);
            opNode.addChild(rootNode);

            token = nextToken();  // consume the operator

            // Parse the second simple expression.  The operator node adopts
            // the simple expression's tree as its second child.
            ICodeNode simExprNode = parseSimpleExpression(token);
            opNode.addChild(simExprNode);
            if (isOperatingOnSetWithBadOperator(rootNode, tokenType)) {
                errorHandler.flag(token, PascalErrorCode.INVALID_TYPE, this);
            }
            else if (tokenType == IN) {
                if (simExprNode != null && !NODES_THAT_CAN_BE_SET.contains(simExprNode.getType())) {
                    errorHandler.flag(token, PascalErrorCode.IN_RIGHT_OPERAND_MUST_BE_SET, this);
                }
            }

            // The operator node becomes the new root node.
            rootNode = opNode;

            // Type check: The operands must be comparison compatible.
            TypeSpec simExprType = simExprNode != null
                                       ? simExprNode.getTypeSpec()
                                       : Predefined.undefinedType;
            if (TypeChecker.areComparisonCompatible(resultType, simExprType)) {
                resultType = Predefined.booleanType;
            }
            else {
                errorHandler.flag(token, INCOMPATIBLE_TYPES, this);
                resultType = Predefined.undefinedType;
            }
        }

        if (rootNode != null) {
            rootNode.setTypeSpec(resultType);
        }

        return rootNode;
    }

    // Set of additive operators.
    private static final EnumSet<PascalTokenType> ADD_OPS =
        EnumSet.of(PLUS, MINUS, PascalTokenType.OR);

    // Map additive operator tokens to node types.
    private static final HashMap<PascalTokenType, ICodeNodeTypeImpl>
        ADD_OPS_OPS_MAP = new HashMap<PascalTokenType, ICodeNodeTypeImpl>();
    static {
        ADD_OPS_OPS_MAP.put(PLUS, ADD);
        ADD_OPS_OPS_MAP.put(MINUS, SUBTRACT);
        ADD_OPS_OPS_MAP.put(PascalTokenType.OR, ICodeNodeTypeImpl.OR);
    };

    /**
     * Parse a simple expression.
     * @param token the initial token.
     * @return the root node of the generated parse tree.
     * @throws Exception if an error occurred.
     */
    private ICodeNode parseSimpleExpression(Token token)
        throws Exception
    {
        Token signToken = null;
        TokenType signType = null;  // type of leading sign (if any)

        // Look for a leading + or - sign.
        TokenType tokenType = token.getType();
        if ((tokenType == PLUS) || (tokenType == MINUS)) {
            signType = tokenType;
            signToken = token;
            token = nextToken();  // consume the + or -
        }

        // Parse a term and make the root of its tree the root node.
        ICodeNode rootNode = parseTerm(token);
        TypeSpec resultType = rootNode != null ? rootNode.getTypeSpec()
                                               : Predefined.undefinedType;

        if (ADD_OPS.contains(currentToken().getType()) &&
                isOperatingOnSetWithBadOperator(rootNode, currentToken().getType())) {
            errorHandler.flag(token, PascalErrorCode.INVALID_TYPE, this);
        }

        // Type check: Leading sign.
        if ((signType != null) && (!TypeChecker.isIntegerOrReal(resultType))) {
            errorHandler.flag(signToken, INCOMPATIBLE_TYPES, this);
        }

        // Was there a leading - sign?
        if (signType == MINUS) {

            // Create a NEGATE node and adopt the current tree
            // as its child. The NEGATE node becomes the new root node.
            ICodeNode negateNode = ICodeFactory.createICodeNode(NEGATE);
            negateNode.addChild(rootNode);
            negateNode.setTypeSpec(rootNode.getTypeSpec());
            rootNode = negateNode;
        }

        token = currentToken();
        tokenType = token.getType();

        // Loop over additive operators.
        while (ADD_OPS.contains(tokenType)) {
            TokenType operator = tokenType;

            // Create a new operator node and adopt the current tree
            // as its first child.
            ICodeNodeType nodeType = ADD_OPS_OPS_MAP.get(operator);
            ICodeNode opNode = ICodeFactory.createICodeNode(nodeType);
            opNode.addChild(rootNode);

            token = nextToken();  // consume the operator

            // Parse another term.  The operator node adopts
            // the term's tree as its second child.
            ICodeNode termNode = parseTerm(token);
            if (isOperatingOnSetWithBadOperator(termNode, tokenType)) {
                errorHandler.flag(token, PascalErrorCode.INVALID_TYPE, this);
            }
            opNode.addChild(termNode);
            TypeSpec termType = termNode != null ? termNode.getTypeSpec()
                                                 : Predefined.undefinedType;

            // The operator node becomes the new root node.
            rootNode = opNode;

            // Determine the result type.
            switch ((PascalTokenType) operator) {

                case PLUS:
                case MINUS: {
                    // Both operands integer ==> integer result.
                    if (TypeChecker.areBothInteger(resultType, termType)) {
                        resultType = Predefined.integerType;
                    }

                    // Both real operands or one real and one integer operand
                    // ==> real result.
                    else if (TypeChecker.isAtLeastOneReal(resultType,
                                                          termType)) {
                        resultType = Predefined.realType;
                    }

                    else {
                        errorHandler.flag(token, INCOMPATIBLE_TYPES, this);
                    }

                    break;
                }

                case OR: {
                    // Both operands boolean ==> boolean result.
                    if (TypeChecker.areBothBoolean(resultType, termType)) {
                        resultType = Predefined.booleanType;
                    }
                    else {
                        errorHandler.flag(token, INCOMPATIBLE_TYPES, this);
                    }

                    break;
                }
            }

            rootNode.setTypeSpec(resultType);

            token = currentToken();
            tokenType = token.getType();
        }

        return rootNode;
    }

    // Set of multiplicative operators.
    private static final EnumSet<PascalTokenType> MULT_OPS =
        EnumSet.of(STAR, SLASH, DIV, PascalTokenType.MOD, PascalTokenType.AND);

    // Map multiplicative operator tokens to node types.
    private static final HashMap<PascalTokenType, ICodeNodeType>
        MULT_OPS_OPS_MAP = new HashMap<PascalTokenType, ICodeNodeType>();
    static {
        MULT_OPS_OPS_MAP.put(STAR, MULTIPLY);
        MULT_OPS_OPS_MAP.put(SLASH, FLOAT_DIVIDE);
        MULT_OPS_OPS_MAP.put(DIV, INTEGER_DIVIDE);
        MULT_OPS_OPS_MAP.put(PascalTokenType.MOD, ICodeNodeTypeImpl.MOD);
        MULT_OPS_OPS_MAP.put(PascalTokenType.AND, ICodeNodeTypeImpl.AND);
    };

    /**
     * Parse a term.
     * @param token the initial token.
     * @return the root node of the generated parse tree.
     * @throws Exception if an error occurred.
     */
    private ICodeNode parseTerm(Token token)
        throws Exception
    {
        // Parse a factor and make its node the root node.
        ICodeNode rootNode = parseFactor(token);
        TypeSpec resultType = rootNode != null ? rootNode.getTypeSpec()
                                               : Predefined.undefinedType;

        if (MULT_OPS.contains(currentToken().getType()) &&
                isOperatingOnSetWithBadOperator(rootNode, currentToken().getType())) {
            errorHandler.flag(token, PascalErrorCode.INVALID_TYPE, this);
        }

        token = currentToken();
        TokenType tokenType = token.getType();

        // Loop over multiplicative operators.
        while (MULT_OPS.contains(tokenType)) {
            TokenType operator = tokenType;

            // Create a new operator node and adopt the current tree
            // as its first child.
            ICodeNodeType nodeType = MULT_OPS_OPS_MAP.get(operator);
            ICodeNode opNode = ICodeFactory.createICodeNode(nodeType);
            opNode.addChild(rootNode);

            token = nextToken();  // consume the operator

            // Parse another factor.  The operator node adopts
            // the term's tree as its second child.
            ICodeNode factorNode = parseFactor(token);
            if (isOperatingOnSetWithBadOperator(factorNode, tokenType)) {
                errorHandler.flag(token, PascalErrorCode.INVALID_TYPE, this);
            }
            opNode.addChild(factorNode);
            TypeSpec factorType = factorNode != null ? factorNode.getTypeSpec()
                                                     : Predefined.undefinedType;

            // The operator node becomes the new root node.
            rootNode = opNode;

            // Determine the result type.
            switch ((PascalTokenType) operator) {

                case STAR: {
                    // Both operands integer ==> integer result.
                    if (TypeChecker.areBothInteger(resultType, factorType)) {
                        resultType = Predefined.integerType;
                    }

                    // Both real operands or one real and one integer operand
                    // ==> real result.
                    else if (TypeChecker.isAtLeastOneReal(resultType,
                                                          factorType)) {
                        resultType = Predefined.realType;
                    }

                    else {
                        errorHandler.flag(token, INCOMPATIBLE_TYPES, this);
                    }

                    break;
                }

                case SLASH: {
                    // All integer and real operand combinations
                    // ==> real result.
                    if (TypeChecker.areBothInteger(resultType, factorType) ||
                        TypeChecker.isAtLeastOneReal(resultType, factorType))
                    {
                        resultType = Predefined.realType;
                    }
                    else {
                        errorHandler.flag(token, INCOMPATIBLE_TYPES, this);
                    }

                    break;
                }

                case DIV:
                case MOD: {
                    // Both operands integer ==> integer result.
                    if (TypeChecker.areBothInteger(resultType, factorType)) {
                        resultType = Predefined.integerType;
                    }
                    else {
                        errorHandler.flag(token, INCOMPATIBLE_TYPES, this);
                    }

                    break;
                }

                case AND: {
                    // Both operands boolean ==> boolean result.
                    if (TypeChecker.areBothBoolean(resultType, factorType)) {
                        resultType = Predefined.booleanType;
                    }
                    else {
                        errorHandler.flag(token, INCOMPATIBLE_TYPES, this);
                    }

                    break;
                }
            }

            rootNode.setTypeSpec(resultType);

            token = currentToken();
            tokenType = token.getType();
        }

        return rootNode;
    }

    /**
     * Parse a factor.
     * @param token the initial token.
     * @return the root node of the generated parse tree.
     * @throws Exception if an error occurred.
     */
    private ICodeNode parseFactor(Token token)
        throws Exception
    {
        TokenType tokenType = token.getType();
        ICodeNode rootNode = null;

        switch ((PascalTokenType) tokenType) {

            case IDENTIFIER: {
                return parseIdentifier(token);
            }

            case INTEGER: {
                // Create an INTEGER_CONSTANT node as the root node.
                rootNode = ICodeFactory.createICodeNode(INTEGER_CONSTANT);
                rootNode.setAttribute(VALUE, token.getValue());

                token = nextToken();  // consume the number

                rootNode.setTypeSpec(Predefined.integerType);
                break;
            }

            case REAL: {
                // Create an REAL_CONSTANT node as the root node.
                rootNode = ICodeFactory.createICodeNode(REAL_CONSTANT);
                rootNode.setAttribute(VALUE, token.getValue());

                token = nextToken();  // consume the number

                rootNode.setTypeSpec(Predefined.realType);
                break;
            }

            case STRING: {
                String value = (String) token.getValue();

                // Create a STRING_CONSTANT node as the root node.
                rootNode = ICodeFactory.createICodeNode(STRING_CONSTANT);
                rootNode.setAttribute(VALUE, value);

                TypeSpec resultType = value.length() == 1
                                          ? Predefined.charType
                                          : TypeFactory.createStringType(value);

                token = nextToken();  // consume the string

                rootNode.setTypeSpec(resultType);
                break;
            }

            case NOT: {
                token = nextToken();  // consume the NOT

                // Create a NOT node as the root node.
                rootNode = ICodeFactory.createICodeNode(ICodeNodeTypeImpl.NOT);

                // Parse the factor.  The NOT node adopts the
                // factor node as its child.
                ICodeNode factorNode = parseFactor(token);
                rootNode.addChild(factorNode);

                // Type check: The factor must be boolean.
                TypeSpec factorType = factorNode != null
                                          ? factorNode.getTypeSpec()
                                          : Predefined.undefinedType;
                if (!TypeChecker.isBoolean(factorType)) {
                    errorHandler.flag(token, INCOMPATIBLE_TYPES, this);
                }

                rootNode.setTypeSpec(Predefined.booleanType);
                break;
            }

            case LEFT_PAREN: {
                token = nextToken();      // consume the (

                // Parse an expression and make its node the root node.
                rootNode = parseExpression(token);
                TypeSpec resultType = rootNode != null
                                          ? rootNode.getTypeSpec()
                                          : Predefined.undefinedType;

                // Look for the matching ) token.
                token = currentToken();
                if (token.getType() == RIGHT_PAREN) {
                    token = nextToken();  // consume the )
                }
                else {
                    errorHandler.flag(token, MISSING_RIGHT_PAREN, this);
                }

                rootNode.setTypeSpec(resultType);
                break;
            }

            // this is where the code goes for building the parse tree for sets
            // add a case for LEFT_BRACKET because '[' signifies that it is a set
            case LEFT_BRACKET: {
                token = nextToken(); // consume the [
                rootNode = ICodeFactory.createICodeNode(ICodeNodeTypeImpl.SET);

                if (token.getType() == RIGHT_BRACKET) {
                    nextToken(); // Consume ]
                    break; // Just exit if the set is empty.
                }

                boolean isExpectingSetElement = true;
                boolean isDoneParsingSet = false;
                HashSet<Integer> setMembers = new HashSet<>();
                while(!isDoneParsingSet) {
                    token = currentToken();

                    if (isExpectingSetElement) {
                        ICodeNode currentNode = parse(token);
                        if (currentNode != null) {
                            /*
                            The assignment requires that we do courtesy error checking if the range start and end are
                            integer constants. We might as well get rid of the range node and add its contents to
                            the set node if we find that to be the case.
                            */
                            if (currentNode.getType() == RANGE) {
                                /*
                                Make sure we're dealing with a valid range (start and end of range were both
                                tokens that were recognized correctly by the parser in the parse() method)
                                 */
                                if (currentNode.getChildren().size() == 2) {
                                    ICodeNode rangeStartNode = currentNode.getChildren().get(0);
                                    ICodeNode rangeEndNode = currentNode.getChildren().get(1);

                                    boolean startIsInt = rangeStartNode.getType() == INTEGER_CONSTANT;
                                    boolean endIsInt = rangeEndNode.getType() == INTEGER_CONSTANT;

                                    boolean areBothIntConstants = startIsInt && endIsInt;
                                    if (areBothIntConstants) {
                                        boolean hasFlaggedDuplicateError = false;
                                        int from = (Integer) rangeStartNode.getAttribute(VALUE);
                                        int to = (Integer) rangeEndNode.getAttribute(VALUE);
                                        while(from <= to) {
                                            ICodeNode child = ICodeFactory.createICodeNode(INTEGER_CONSTANT);
                                            child.setAttribute(VALUE, from);
                                        /*
                                        Ranges may produce many duplicates, spamming the error output. Let's make sure
                                        we only report a single error.
                                        */
                                            if (!hasFlaggedDuplicateError) {
                                                hasFlaggedDuplicateError =
                                                        !addSetNodeChild(rootNode, child, token, setMembers);
                                            }
                                            else {
                                            /*
                                            We deliberately DO NOT use addSetNodeChild() here because we have already checked
                                            for duplicate members up above (see the above comment).
                                            */
                                                setMembers.add(from);
                                                if (!isValidSetElementValue(from)) {
                                                    errorHandler.flag(token,
                                                            PascalErrorCode.SET_ELEMENT_OUT_OF_BOUNDS, this);
                                                }
                                                rootNode.addChild(child);
                                            }

                                            from++;
                                        }
                                    }
                                    else if (startIsInt &&
                                            !isValidSetElementValue((int)rangeStartNode.getAttribute(VALUE))) {
                                        errorHandler.flag(token, PascalErrorCode.SET_ELEMENT_OUT_OF_BOUNDS, this);
                                    }
                                    else if (endIsInt &&
                                            !isValidSetElementValue((int)rangeEndNode.getAttribute(VALUE))) {
                                        errorHandler.flag(peekPreviousToken(),
                                                PascalErrorCode.SET_ELEMENT_OUT_OF_BOUNDS, this);
                                    }

                                    isExpectingSetElement = false;
                                }
                                else if (peekPreviousToken().getType() == COMMA) {
                                    isExpectingSetElement = true;
                                }
                                else {
                                    isExpectingSetElement = false;
                                }
                            }
                            else {
                                isExpectingSetElement = false;
                            }

                            addSetNodeChild(rootNode, currentNode, token, setMembers);
                        }
                        else {
                            if (token.getType() == COMMA) {
                                // If we encounter duplicate commas, keep trying to find a set element.
                                isExpectingSetElement = true;
                            }
                            else if (token.getType() == RIGHT_BRACKET) {
                                isDoneParsingSet = true;
                            }
                        }
                    }
                    else {
                        if (token.getType() == COMMA) {
                            nextToken(); // consume the comma
                        }
                        else if (token.getType() == INTEGER) {
                            errorHandler.flag(token, PascalErrorCode.MISSING_COMMA, this);
                        }
                        else {
                            if (token.getType() != RIGHT_BRACKET) {
                                errorHandler.flag(token, PascalErrorCode.MISSING_RIGHT_BRACKET, this);
                            }
                            else {
                                nextToken(); // consume ]
                            }

                            isDoneParsingSet = true;
                        }

                        isExpectingSetElement = true;
                    }

                }
                break;
            }

            default: {
                errorHandler.flag(token, UNEXPECTED_TOKEN, this);
                nextToken();
            }
        }
        
        //Create a set typeSpec 
        TypeForm setForm = TypeFormImpl.SET;
        TypeSpec setType = TypeFactory.createType(setForm); 
        // set the attributes of the set to match the children.
        /** he current problem is that a child type could be a scalar
         * so setting the set type base on the first child assumes that 
         * the error checking for all children being that base type is also true
         */
        if(!rootNode.getChildren().isEmpty()) {
        setType.setAttribute(TypeKeyImpl.SET_ELEMENT_TYPE,
        		rootNode.getChildren().get(0).getTypeSpec().baseType());
        } else {
            setType.setAttribute(TypeKeyImpl.SET_ELEMENT_TYPE, Predefined.undefinedType);
        }
        rootNode.setTypeSpec(setType);

        if(!rootNode.getChildren().isEmpty()) {
            for (ICodeNode i : rootNode.getChildren()) {
                if(!i.getTypeSpec().baseType()
                        .equals(rootNode.getChildren().get(0).getTypeSpec().baseType()) ) {
                    // what error should I throw here
                    errorHandler.flag(token, MULTIPLE_TYPES_IN_SET, this);
                }
            }
        }
        return rootNode;
    }

    /**
     * Parse an identifier.
     * @param token the current token.
     * @return the root node of the generated parse tree.
     * @throws Exception if an error occurred.
     */
    private ICodeNode parseIdentifier(Token token)
        throws Exception
    {
        ICodeNode rootNode = null;

        // Look up the identifier in the symbol table stack.
        String name = token.getText().toLowerCase();
        SymTabEntry id = symTabStack.lookup(name);

        // Undefined.
        if (id == null) {
            errorHandler.flag(token, IDENTIFIER_UNDEFINED, this);
            id = symTabStack.enterLocal(name);
            id.setDefinition(UNDEFINED);
            id.setTypeSpec(Predefined.undefinedType);
        }

        Definition defnCode = id.getDefinition();

        switch ((DefinitionImpl) defnCode) {

            case CONSTANT: {
                Object value = id.getAttribute(CONSTANT_VALUE);
                TypeSpec type = id.getTypeSpec();

                if (value instanceof Integer) {
                    rootNode = ICodeFactory.createICodeNode(INTEGER_CONSTANT);
                    rootNode.setAttribute(VALUE, value);
                }
                else if (value instanceof Float) {
                    rootNode = ICodeFactory.createICodeNode(REAL_CONSTANT);
                    rootNode.setAttribute(VALUE, value);
                }
                else if (value instanceof String) {
                    rootNode = ICodeFactory.createICodeNode(STRING_CONSTANT);
                    rootNode.setAttribute(VALUE, value);
                }

                id.appendLineNumber(token.getLineNumber());
                token = nextToken();  // consume the constant identifier

                if (rootNode != null) {
                    rootNode.setTypeSpec(type);
                }

                break;
            }

            case ENUMERATION_CONSTANT: {
                Object value = id.getAttribute(CONSTANT_VALUE);
                TypeSpec type = id.getTypeSpec();

                rootNode = ICodeFactory.createICodeNode(INTEGER_CONSTANT);
                rootNode.setAttribute(VALUE, value);

                id.appendLineNumber(token.getLineNumber());
                token = nextToken();  // consume the enum constant identifier

                rootNode.setTypeSpec(type);
                break;
            }

            default: {
                VariableParser variableParser = new VariableParser(this);
                rootNode = variableParser.parse(token, id);
                break;
            }
        }

        return rootNode;
    }

    /**
     * Adds a child to the set node. If the value is an integer constant, the parser has the opportunity to check
     * to see if that value already exists in the set. If it does, we flag it as an error since it probably wasn't
     * intended that were duplicate constants in the set (doing so in the parser is recommended by the assignment).
     *
     * @param setNode the set node
     * @param child the child node to add, assumed to ne non-null
     * @param childToken the token from which the child node was parsed
     * @param setMembers the members of the set seen so far
     *
     * @return true if no error occurred, false otherwise.
     */
    private boolean addSetNodeChild(ICodeNode setNode,
                                    ICodeNode child,
                                    Token childToken,
                                    HashSet<Integer> setMembers) {
        boolean success = true;

        if (child.getType() == INTEGER_CONSTANT) {
            int value = (int) child.getAttribute(ICodeKeyImpl.VALUE);
            if (setMembers.contains(value)) {
                errorHandler.flag(childToken, PascalErrorCode.DUPLICATE_ELEMENT, this);
                success = false;
            }
            else {
                setMembers.add(value);
            }

            if (!isValidSetElementValue(value)) {
                errorHandler.flag(childToken, PascalErrorCode.SET_ELEMENT_OUT_OF_BOUNDS, this);
            }
        }

        setNode.addChild(child);

        return success;
    }

    private boolean isValidSetElementValue(int value) {
        return MIN_SET_ELEMENT_VALUE <= value && value <= MAX_SET_ELEMENT_VALUE;
    }
}
