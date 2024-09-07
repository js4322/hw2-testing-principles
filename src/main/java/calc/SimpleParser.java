package calc;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

public class SimpleParser implements IParser{
    private static SimpleParser instance;
    private SimpleParser(){}
    public static SimpleParser getInstance(){
        if(instance == null)
            instance = new SimpleParser();
        return instance;
    }
    public double parse(String input) {
        LexemeIterator i = new LexemeIterator(getLexemeList(input));
        if(i.get().equals(LexemeType.END_OF_EXPRESSION))
            return 0;
        return firstOrderOperation(i);
    }
    private double firstOrderOperation(LexemeIterator input) {
        double value = secondOrderOperation(input);
        while (true) {
            switch (input.get().getType()) {
                case OP_ADDITION:
                    input.next();
                    value += secondOrderOperation(input);
                    break;
                case OP_SUBTRACTION:
                    input.next();
                    value -= secondOrderOperation(input);
                    break;
                case END_OF_EXPRESSION:
                case CLOSING_BRACKET:
                    return value;
                default:
                    throw new InputMismatchException("Unexpected lexeme  at index "
                            + input.getIndex() + '\t' + input.get());
            }
        }
    }
    private double secondOrderOperation(LexemeIterator input) {
        double value = thirdOrderOperation(input);
        input.next();
        while(true) {
            switch (input.get().getType()) {
                case OP_ADDITION:
                case CLOSING_BRACKET:
                case END_OF_EXPRESSION:
                    return value;
                case OP_SUBTRACTION:
                    if(input.getNext().equals(LexemeType.OPENING_BRACKET))
                        return thirdOrderOperation(input);
                    else return value;
                case OP_MULTIPLICATION:
                    input.next();
                    value *= thirdOrderOperation(input);
                    break;
                case OP_DIVISION:
                    input.next();
                    value /= thirdOrderOperation(input);
                    break;
                default:
                    throw new InputMismatchException("Unexpected lexeme at index "
                            + input.getIndex() + '\t' + input.get());
            }
            input.next();
        }
    }
    private double thirdOrderOperation(LexemeIterator input) {
        double value = 0;
        switch (input.get().getType()){
            case NUMBER:
                return input.get().getNumericValue();
            case OPENING_BRACKET:
                input.next();
                value = firstOrderOperation(input);
                if(input.get().equals(LexemeType.CLOSING_BRACKET))
                    return value;
                else
                    throw new InputMismatchException("First order operation inside of brackets " +
                            "didn't finish on closing bracket.");
            case OP_SUBTRACTION:
                if(input.getNext().equals(LexemeType.OPENING_BRACKET)){
                    input.next();
                    input.next();
                    value = firstOrderOperation(input);
                    if(input.get().equals(LexemeType.CLOSING_BRACKET))
                        return -value;
                    else
                        throw new InputMismatchException("Firest order operation inside of brackets " +
                                "didn't finish on closing bracket.");
                }
                if(input.getNext().equals(LexemeType.NUMBER)) {
                    input.next();
                    value = thirdOrderOperation(input);
                    return -value;
                }
            default:
                throw new InputMismatchException("Unexpected lexeme at index " + input.getIndex() + '\t' + input.get());
        }

    }
    @Override
    public List<Lexeme> getLexemeList(String expression) {
        final char DECIMAL_DELIMITER = '.';//','||'.'
        int index = 0;
        List<Lexeme> lexemes = new ArrayList<>();
        while (index < expression.length()) {
            switch (expression.charAt(index)) {
                case '(':
                    lexemes.add(new Lexeme(LexemeType.OPENING_BRACKET));
                    index++;
                    continue;
                case ')':
                    lexemes.add(new Lexeme(LexemeType.CLOSING_BRACKET));
                    index++;
                    continue;
                case '*':
                    lexemes.add(new Lexeme(LexemeType.OP_MULTIPLICATION));
                    index++;
                    continue;
                case '/':
                    lexemes.add(new Lexeme(LexemeType.OP_DIVISION));
                    index++;
                    continue;
                case '+':
                    lexemes.add(new Lexeme(LexemeType.OP_ADDITION));
                    index++;
                    continue;
                case '-':
                    lexemes.add(new Lexeme(LexemeType.OP_SUBTRACTION));
                    index++;
                    continue;
                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9':
                    int initialIndex = index;
                    do {
                        index++;
                        if (index >= expression.length())
                            break;

                    } while (expression.charAt(index) >= '0' && expression.charAt(index) <= '9' ||
                            expression.charAt(index) == DECIMAL_DELIMITER);
                    lexemes.add(new Lexeme(LexemeType.NUMBER,
                            Double.parseDouble(expression.substring(initialIndex, index))));
                    continue;
                case ' ':
                    index++;
                    continue;
                default:
                    lexemes.add(new Lexeme(LexemeType.UNSUPPORTED));
                    throw new IllegalArgumentException("Symbol " + expression.charAt(index) + "\t can not be resolved.");
            }
        }
        lexemes.add(new Lexeme(LexemeType.END_OF_EXPRESSION));
        return lexemes;
    }
}
