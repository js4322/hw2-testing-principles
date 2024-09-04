package calc;

import java.util.List;

public interface IParser {
    List<Lexeme> getLexemeList(String expression);
}
