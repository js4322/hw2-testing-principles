package calc;

public enum LexemeType {
    NUMBER,
    OP_ADDITION,
    OP_SUBTRACTION,
    OP_MULTIPLICATION,
    OP_DIVISION,
    OPENING_BRACKET,
    CLOSING_BRACKET,
    END_OF_EXPRESSION,
    UNSUPPORTED;
    @Override public String toString(){
        switch(this){
            case OPENING_BRACKET:   return "(";
            case CLOSING_BRACKET:   return ")";
            case NUMBER:            return "Num";
            case OP_ADDITION:       return "+";
            case END_OF_EXPRESSION: return "EOE";
            case OP_DIVISION:       return "/";
            case UNSUPPORTED:       return "unsupported token";
            case OP_SUBTRACTION:    return "-";
            case OP_MULTIPLICATION: return "*";
            default:                throw new IllegalStateException();
        }
    }
}
