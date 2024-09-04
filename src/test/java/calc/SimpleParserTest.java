package calc;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.InputMismatchException;

import static org.junit.jupiter.api.Assertions.*;

class SimpleParserTest {
    SimpleParser instance;
    @BeforeEach
    void beforeEach(){
        instance = SimpleParser.getInstance();
    }
    //Test that missing arguments or operands throws java.util.InputMismatchException
    @Test
    void testThatNumberFormatExceptionIsThrown(){
        //expected NumberFormatException thrown when input
        String input = "10.211.2";
        //actual
        assertThrows(NumberFormatException.class, ()->{instance.parse(input);});
    }
    @Test
    void testThatLexemesInIncorrectOrderCauseInputMismatchException1(){
        //expected java.util.InputMismatchException thrown when input
        String input = "*";
        //actual
        assertThrows(InputMismatchException.class, ()->{instance.parse(input);});
    }
    @Test
    void testThatLexemesInIncorrectOrderCauseInputMismatchException2(){
        //expected java.util.InputMismatchException thrown when input
        String input = "1.1*-";
        //actual
        assertThrows(InputMismatchException.class, ()->{instance.parse(input);});
    }
    @Test
    void testThatLexemesInIncorrectOrderCauseInputMismatchException3(){
        //expected java.util.InputMismatchException thrown when input
        String input = "**";
        //actual
        assertThrows(InputMismatchException.class, ()->{instance.parse(input);});
    }

    @Test
    void testThatLexemesInIncorrectOrderCauseInputMismatchException4(){
        //expected java.util.InputMismatchException thrown when input
        String input = "8 15";
        //actual
        assertThrows(InputMismatchException.class, ()->{instance.parse(input);});
    }
    @Test
    void testThatLexemesInIncorrectOrderCauseInputMismatchException5(){
        //expected java.util.InputMismatchException thrown when input
        String input = "7*8-5+3-+";
        //actual
        assertThrows(InputMismatchException.class, ()->{instance.parse(input);});
    }
    @Test
    void testThatLexemesInIncorrectOrderCauseInputMismatchException6(){
        //expected java.util.InputMismatchException thrown when input
        String input = "* - +";
        //actual
        assertThrows(InputMismatchException.class, ()->{instance.parse(input);});
    }
    @Test
    void testThatLexemesInIncorrectOrderCauseInputMismatchException7(){
        //expected java.util.InputMismatchException thrown when input
        String input = "1 *";
        //actual
        assertThrows(InputMismatchException.class, ()->{instance.parse(input);});
    }
    @Test
    void testThatLexemesInIncorrectOrderCauseInputMismatchException8(){
        //expected java.util.InputMismatchException thrown when input
        String input = "1 *";
        //actual
        assertThrows(InputMismatchException.class, ()->{instance.parse(input);});
    }
    @Test
    void testThatLexemesInIncorrectOrderCauseInputMismatchException9(){
        //expected java.util.InputMismatchException thrown when input
        String input = "* 1 /";
        //actual
        assertThrows(InputMismatchException.class, ()->{instance.parse(input);});
    }

    //Test that incorrect symbols are ignored
    @Test
    void testThatIncorrectSymbolsLeadToIllegalArgumentException(){
        //given
        String input = "^()fds;oinkewrarLDGR  FNU{ODFRGXJ!@#$%^&<>?\"i,";
        //expected that exception is thrown
        //actual
        assertThrows(IllegalArgumentException.class, ()->{instance.parse(input);});
    }
    //Test that blank input returns blank output
    @Test
    void testThatBlankInputReturnsBlankOutput(){
        //given
        String input = "";
        //expected
        float expected = 0f;

        //actual
        float actual = (float)instance.parse(input);
        assertEquals(expected, actual);
    }
}