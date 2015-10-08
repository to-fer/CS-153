package wci.frontend.pascal.parsers;

import java.util.EnumSet;
import java.util.ArrayList;

import wci.frontend.*;
import wci.frontend.pascal.*;
import wci.intermediate.*;
import wci.intermediate.symtabimpl.*;

import static wci.frontend.pascal.PascalTokenType.*;
import static wci.frontend.pascal.PascalErrorCode.*;
import static wci.intermediate.symtabimpl.SymTabKeyImpl.*;
import static wci.intermediate.typeimpl.TypeFormImpl.ARRAY;
import static wci.intermediate.typeimpl.TypeFormImpl.SUBRANGE;
import static wci.intermediate.typeimpl.TypeFormImpl.ENUMERATION;
import static wci.intermediate.typeimpl.TypeFormImpl.SET;

import static wci.intermediate.typeimpl.TypeKeyImpl.*;

/**
 * Created by harsh on 10/7/15.
 */
public class SetTypeParser extends TypeSpecificationParser{

    /**
     * Constructor.
     * @param parent the parent parser.
     */
    protected SetTypeParser(PascalParserTD parent)
    {
        super(parent);
    }

    /**
     * Parse a Pascal array type specification.
     * @param token the current token.
     * @return the array type specification.
     * @throws Exception if an error occurred.
     */
    public TypeSpec parse(Token token)
            throws Exception
    {
        TypeSpec setType = TypeFactory.createType(SET);
        token = nextToken();  // consume ARRAY
        if(token.getType() != OF) {
            errorHandler.flag(token, MISSING_OF, this);
        }
        else {
            token = nextToken();
        }

        TypeSpec elementType = parseElementType(token, setType);
        return setType;
    }


    /**
     * Parse the element type specification.
     * @param token the current token.
     * @return the element type specification.
     * @throws Exception if an error occurred.
     */
    private TypeSpec parseElementType(Token token)
            throws Exception
    {
        TypeSpecificationParser typeSpecificationParser =
                new TypeSpecificationParser(this);
        return typeSpecificationParser.parse(token);
    }
}
