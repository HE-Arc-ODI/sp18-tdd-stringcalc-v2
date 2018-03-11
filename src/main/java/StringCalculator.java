import java.util.Arrays;
import java.util.stream.IntStream;

public class StringCalculator {


  public static int add(String input) {
    String delimiter = "[,\n]";
    if (input.isEmpty()) {
      return 0;
    }
    if (input.indexOf("//") == 0) {
      int endDelimiterSequence = input.indexOf("\n");
      delimiter = input.substring(2, endDelimiterSequence);
      input = input.substring(endDelimiterSequence + 1);
    }
    IntStream numbers = Arrays.stream(input.split(delimiter))
        .mapToInt(Integer::parseInt)
        .filter(value -> value <= 1000);
    return numbers.sum();
  }
}
