package wci.backend.compiler;

import wci.intermediate.TypeSpec;
import wci.intermediate.symtabimpl.Predefined;

public class TypeCode {
    private static final String BOOLEAN_TYPECODE = "B";
    private static final String CHAR_TYPECODE = "C";
    private static final String DOUBLE_TYPECODE = "D";
    private static final String FLOAT_TYPECODE = "F";

    public static String typeSpecToTypeCode(TypeSpec spec) {
        if (spec == Predefined.booleanType) {
            return BOOLEAN_TYPECODE;
        }
        else if (spec == Predefined.charType) {
            return CHAR_TYPECODE;
        }
        else if (spec == Predefined.numberType) {
            return FLOAT_TYPECODE;
        }
        else {
            throw new UnsupportedOperationException("That type is not yet implemented in the code generator!");
        }
    }
}
