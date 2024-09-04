package calc;

import java.util.List;

public class SimpleParser implements IParser{
    private static SimpleParser instance;
    private SimpleParser(){}
    public static SimpleParser getInstance(){
        if(instance == null)
            instance = new SimpleParser();
        return instance;
    }
    @Override
    public List<Lexeme> getLexemeList(String expression) {
        return null;
    }

    @Override
    public double parse(String input) {
        return 0;
    }

}
