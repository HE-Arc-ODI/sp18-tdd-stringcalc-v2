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


}