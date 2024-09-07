package calc;

import java.util.List;

public class Lexeme {
    private LexemeType type;
    private double numericValue;

    public Lexeme(LexemeType t) {
        if (t.equals(LexemeType.NUMBER))
            throw new IllegalArgumentException("for initialising number choose an other constructor," +
                    " with number value. Argument: " + t);
        this.type = t;
    }

    public Lexeme(LexemeType t, double value) {
        if (!t.equals(LexemeType.NUMBER))
            throw new IllegalArgumentException("only number should have numeric value. Arguments: " + t + '\t' + value);
        this.type = t;
        this.numericValue = value;
    }

    public double getNumericValue() {
        if (!(this.type == LexemeType.NUMBER))
            throw new UnsupportedOperationException("numerical value is valid only for numbers. This lexeme type is " +
                    this.type);
        return numericValue;
    }

    public LexemeType getType() {
        return type;
    }

    public boolean equals(LexemeType t) {
        if (this.type.equals(t))
            return true;
        return false;
    }

    @Override
    public String toString () {
        String result = type.toString();
        if (type.equals(LexemeType.NUMBER))
            result = result + ' ' + numericValue + ' ';
        return result;
    }
}

class LexemeIterator {
    private List<Lexeme> lexemes;
    private int index;
    public LexemeIterator(List<Lexeme> list) {
        this.lexemes = list;
        this.index = 0;
    }
    public Lexeme getNext() {return lexemes.get(index + 1);}
    public void next() { index ++;}
    public Lexeme get() {return lexemes.get(index);}
    public int getIndex() {return index;}
}



