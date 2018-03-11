import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class StringCalculatorTest {

  @Test
  public void testEmptyString() {
    // Arrange / Build
    String input = "";
    int expected = 0;
    // Act / Operate
    int actual = StringCalculator.add(input);
    // Assert / Check
    assertEquals(expected, actual);
  }

  @Test
  public void testAddASingleNumber() {
    // Arrange / Build
    String input = "1";
    int expected = 1;
    // Act / Operate
    int actual = StringCalculator.add(input);
    // Assert / Check
    assertEquals(expected, actual);
  }

  @Test
  public void addTwoNumbers() {
    // Arrange / Build
    String input = "1,2";
    int expected = 3;
    // Act / Operate
    int actual = StringCalculator.add(input);
    // Assert / Check
    assertEquals(expected, actual);
  }


  @Test
  public void addMultipleNumbers() {
    // Arrange / Build
    String input = "1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30";
    int expected = 465;
    // Act / Operate
    int actual = StringCalculator.add(input);
    // Assert / Check
    assertEquals(expected, actual);
  }


  @Test
  public void allowNewLinesBetweenNumbers() {
    // Arrange / Build
    String input = "1\n2,3";
    int expected = 6;
    // Act / Operate
    int actual = StringCalculator.add(input);
    // Assert / Check
    assertEquals(expected, actual);
  }

  @Test
  public void declareDelimitersAtFirstLine() {
    // Arrange / Build
    String input = "//;\n1;2;3";
    int expected = 6;
    // Act / Operate
    int actual = StringCalculator.add(input);
    // Assert / Check
    assertEquals(expected, actual);
  }

  @Test
  public void ignoreNumbersBiggerThan1000() {
    // Arrange / Build
    String input = "2,1001";
    int expected = 2;
    // Act / Operate
    int actual = StringCalculator.add(input);
    // Assert / Check
    assertEquals(expected, actual);
  }

  @Test
  public void delimitersCanBeOfAnyLength() {
    // Arrange / Build
    String input = "//[***]\n1***2***3";
    int expected = 6;
    // Act / Operate
    int actual = StringCalculator.add(input);
    // Assert / Check
    assertEquals(expected, actual);
  }

}