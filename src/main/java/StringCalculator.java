import java.util.Arrays;
import java.util.stream.IntStream;

public class StringCalculator {


  public int add(String input) {
    if (input.isEmpty()) {
      return 0;
    }
    IntStream numbers = Arrays.stream(input.split("[,\\n]"))
        .mapToInt(Integer::parseInt);
    return numbers.sum();
  }
}
