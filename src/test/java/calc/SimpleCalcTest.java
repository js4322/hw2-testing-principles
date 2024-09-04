package calc;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SimpleCalcTest {
    SimpleCalc instance;
    double minimalFloatDifference = 1e-44;
    @BeforeEach
    void beforeEach(){
        instance = SimpleCalc.getInstance();
    }
    //Test that multiplication and addition is commutative and associative.
    // And subtraction and division ar not commutative nor associative.
    @Test
    void TestThatMultiplicationIsCommutative() {
        //given
        String input1 = "3*2";
        String input2 = "2*3";
        //expected 3*2 == 2*3
        //actual
        assertEquals(instance.calc(input1),instance.calc(input2));
    }
    @Test
    void TestThatAdditionIsCommutative() {
        //given
        String input1 = "3+2";
        String input2 = "2+3";
        //expected 3+2 == 2+3
        //actual
        assertEquals(instance.calc(input1),instance.calc(input2));
    }
    @Test
    void TestThatDivisionIsNotCommutative() {
        //given
        String input1 = "3/2";
        String input2 = "2/3";
        //expected 3/2 != 2/3
        //actual
        assertNotEquals(instance.calc(input1),instance.calc(input2));
    }

    //Test that 2 + 3 = 5 +- minimalFloatDifference
    @Test
    void TestThatTwoPlusThreeEqualsFive() {
        //given
        String input1 = "2+3";
        //expected
        float expected = 5f;
        //actual
        float actual = instance.calc(input1);
        assertTrue(expected-actual <= minimalFloatDifference);
    }
    //Test that 3 + 2 = 2 + 3 +- minimalFloatDifference
    @Test
    void TestThatThreePlusTwoEqualsTwoPlusThree() {
        //given
        String input1 = "2+3";
        String input2 = "3+2";
        //expected 2+3 - (3+2) <= minimalFloatDifference
        //actual
        float actual = instance.calc(input1) - instance.calc(input2);
        assertTrue(actual <= minimalFloatDifference);
    }
    //Test that 2*3 = 6 +- minimalFloatDifference
    @Test
    void TestThatTwoMultiplyByThreeEqualsSix() {
        //given
        String input1 = "2*3";
        //expected
        float expected = 6f;
        //actual
        float actual = instance.calc(input1);
        assertTrue(actual - expected <= minimalFloatDifference);
    }
    //Test that 3*2 = 2*3 +- minimalFloatDifference
    @Test
    void TestThat3MultiplyBy2Equals2MultiplyBy3() {
        //given
        String input1 = "2*3";
        String input2 = "3*2";
        //expected 2*3 - (3*2) <= minimalFloatDifference
        //actual
        float actual = instance.calc(input1) - instance.calc(input2);
        assertTrue(actual <= minimalFloatDifference);
    }
    //Test that 6*3/2 = 9 +- minimalFloatDifference
    @Test
    void TestThatSixMultiplyByThreeDivideByTwoEqualsNine() {
        //given
        String input1 = "6*3/2";
        //expected
        float expected = 9f;
        //actual
        float actual = instance.calc(input1);
        assertTrue(actual - expected <= minimalFloatDifference);
    }
    ////Test that operation order works fine
    @Test
    void TestThat2plus2multiplyBy2equals6() {
        //given
        String input1 = "2+2*2";
        //expected
        float expected = 6f;
        //actual
        float actual = instance.calc(input1);
        assertTrue(actual - expected <= minimalFloatDifference);
    }
    //given expression number 1: 1*2 + 3*4 + 5*6 = 30+12+2= 44
    @Test
    void TestGivenExpression1() {
        //given
        String input1 = "1*2 + 3*4 + 5*6";
        //expected
        float expected = 44f;
        //actual
        float actual = instance.calc(input1);
        assertTrue(actual - expected <= minimalFloatDifference);
    }
    //Test that 1 + 2*3 + 4*5 + 6 = 1+6+20+6 = 33
    @Test
    void TestGivenExpression2() {
        //given
        String input1 = "1 + 2*3 + 4*5 + 6";
        //expected
        float expected = 33f;
        //actual
        float actual = instance.calc(input1);
        assertTrue(actual - expected <= minimalFloatDifference);
    }
}