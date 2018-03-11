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

}