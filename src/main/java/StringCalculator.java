/*
 * Copyright (c) 2018. Cours Outils de développement intégré, HE-Arc Neuchâtel.
 */

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * TDD Kata String calculator
 * REF: http://osherove.com/tdd-kata-1/
 */
public class StringCalculator {

  private static final String MULTIPLE_DELIMITERS_REGEX = "\\[(.*?)\\]";

  /**
   * Computes the sum of a string containing numbers separated by arbitrary delimiters. The delimiters are declared on the first line of the string.
   *
   * @param input numbers to be summed separated by delimiters
   * @return sum of the numbers
   */
  public static int add(String input) {
    String delimiter = "[,\n]";
    if (input.isEmpty()) {
      return 0;
    }
    if (input.indexOf("//") == 0) {
      int endDelimiterSequence = input.indexOf("\n");
      delimiter = input.substring(2, endDelimiterSequence);
      if (delimiter.indexOf("[") == 0) {
        Pattern MultipleDelimitersPtn = Pattern.compile(MULTIPLE_DELIMITERS_REGEX);
        Matcher MultipleDelimitersMatcher = MultipleDelimitersPtn.matcher(delimiter);
        StringBuilder b = new StringBuilder();
        while (MultipleDelimitersMatcher.find()) {
          if (b.length() > 0) {
            b.append("|");
          }
          b.append("(");
          b.append(escapeSpecialRegexChars(MultipleDelimitersMatcher.group(1)));
          b.append(")");
        }
        delimiter = b.toString();
      }
      input = input.substring(endDelimiterSequence + 1);
    }
    int[] numbers = Arrays.stream(input.split(delimiter))
        .mapToInt(Integer::parseInt)
        .filter(value -> value <= 1000).toArray();
    int[] negatives = Arrays.stream(numbers).filter(value -> value < 0).toArray();
    if (negatives.length > 0) {
      throw new IllegalArgumentException("negatives not allowed: " + Arrays.toString(negatives));
    }
    return Arrays.stream(numbers).sum();
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
