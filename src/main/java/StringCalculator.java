import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

public class StringCalculator {

  private static final String ANYLENGTH_DELIMITERS_REGEX = "\\[(.*?)\\]";

  public static int add(String input) {
    String delimiter = "[,\n]";
    if (input.isEmpty()) {
      return 0;
    }
    if (input.indexOf("//") == 0) {
      int endDelimiterSequence = input.indexOf("\n");
      delimiter = input.substring(2, endDelimiterSequence);
      if (delimiter.indexOf("[") == 0) {
        Pattern anyLengthDelimitersPtn = Pattern.compile(ANYLENGTH_DELIMITERS_REGEX);
        Matcher anyLengthDelimitersMatcher = anyLengthDelimitersPtn.matcher(delimiter);
        if (anyLengthDelimitersMatcher.find()) {
          delimiter = anyLengthDelimitersMatcher.group(1);
          delimiter = escapeSpecialRegexChars(delimiter);
        }
      }
      input = input.substring(endDelimiterSequence + 1);
    }
    IntStream numbers = Arrays.stream(input.split(delimiter))
        .mapToInt(Integer::parseInt)
        .filter(value -> value <= 1000);
    return numbers.sum();
  }

  /**
   * Escapes all special regex characters
   * REF: https://stackoverflow.com/a/25853507/2195344
   *
   * @param str input
   * @return string with special regex characters escaped
   */
  private static String escapeSpecialRegexChars(String str) {
    Pattern SPECIAL_REGEX_CHARS = Pattern.compile("[{}()\\[\\].+*?^$\\\\|]");
    return SPECIAL_REGEX_CHARS.matcher(str).replaceAll("\\\\$0");
  }
}
