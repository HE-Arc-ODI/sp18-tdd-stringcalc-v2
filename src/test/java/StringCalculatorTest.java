import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class StringCalculatorTest {

  @Test
  public void testEmptyString() {
    // Arrange / Build
    StringCalculator calc = new StringCalculator();
    String input = "";
    int expected = 0;
    // Act / Operate
    int actual = calc.add(input);
    // Assert / Check
    assertEquals(expected, actual);
  }

  @Test
  public void testAddASingleNumber() {
    // Arrange / Build
    StringCalculator calc = new StringCalculator();
    String input = "1";
    int expected = 1;
    // Act / Operate
    int actual = calc.add(input);
    // Assert / Check
    assertEquals(expected, actual);
  }

  @Test
  public void addTwoNumbers() {
    // Arrange / Build
    StringCalculator calc = new StringCalculator();
    String input = "1,2";
    int expected = 3;
    // Act / Operate
    int actual = calc.add(input);
    // Assert / Check
    assertEquals(expected, actual);
  }


  @Test
  public void addMultipleNumbers() {
    // Arrange / Build
    StringCalculator calc = new StringCalculator();
    String input = "1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30";
    int expected = 465;
    // Act / Operate
    int actual = calc.add(input);
    // Assert / Check
    assertEquals(expected, actual);
  }


  @Test
  public void allowNewLinesBetweenNumbers() {
    // Arrange / Build
    StringCalculator calc = new StringCalculator();
    String input = "1\n2,3";
    int expected = 6;
    // Act / Operate
    int actual = calc.add(input);
    // Assert / Check
    assertEquals(expected, actual);
  }

  @Test
  public void declareDelimitersAtFirstLine() {
    // Arrange / Build
    StringCalculator calc = new StringCalculator();
    String input = "//;\n1;2;3";
    int expected = 6;
    // Act / Operate
    int actual = calc.add(input);
    // Assert / Check
    assertEquals(expected, actual);
  }

}