/*
 * Copyright (c) 2018. Cours Outils de développement intégré, HE-Arc Neuchâtel.
 */

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * TDD Kata String calculator, computes sum of a string of numbers.
 * REF: http://osherove.com/tdd-kata-1/
 */
public class StringCalculator {

  private static final String MULTIPLE_DELIMITERS_REGEX = "\\[(.*?)\\]";
  private static final String STANDARD_DELIMITERS = "[,\n]";
  private static final int CEILING = 1000;

  /**
   * Computes the sum of a string containing numbers separated by arbitrary delimiters. The delimiters are declared on the first line of the string. See kata ref link for more info.
   *
   * @param input numbers to be summed separated by delimiters
   * @return sum of the numbers
   */
  public static int add(String input) {
    if (input.isEmpty()) {
      return 0;
    }
    return computeSum(input);
  }

  private static int computeSum(String input) {
    int[] numbers = extractNumbers(input);
    return Arrays.stream(numbers).filter(value -> value <= CEILING).sum();
  }

  private static int[] extractNumbers(String input) {
    String delimitersRegex = extractDelimiters(input);
    String operands = extractOperands(input);
    int[] numbers = Arrays.stream(operands.split(delimitersRegex))
        .mapToInt(Integer::parseInt).toArray();
    return checkNegativeNumbers(numbers);
  }

  private static int[] checkNegativeNumbers(int[] numbers) {
    int[] negativeNumbers = Arrays.stream(numbers)
        .filter(value -> value < 0).toArray();
    if (negativeNumbers.length > 0) {
      throw new IllegalArgumentException(
          "negatives not allowed: " + Arrays.toString(negativeNumbers));
    }
    return numbers;
  }

  private static String extractOperands(String input) {
    String delimiterStart = "//";
    int endDelimiterDeclaration = 0;
    if (input.startsWith(delimiterStart)) {
      endDelimiterDeclaration = findEndOfDelimiters(input);
    }
    return input.substring(endDelimiterDeclaration);
  }

  private static int findEndOfDelimiters(String input) {
    int endDelimiterDeclaration = 0;
    String simpleDelimiterEnd = "\n";
    String multipleDelimiterEnd = "]\n";
    if (input.contains(multipleDelimiterEnd)) {
      endDelimiterDeclaration = input.indexOf(multipleDelimiterEnd) + multipleDelimiterEnd.length();
    } else if (input.contains(simpleDelimiterEnd)) {
      endDelimiterDeclaration = input.indexOf(simpleDelimiterEnd) + simpleDelimiterEnd.length();
    }
    return endDelimiterDeclaration;
  }

  private static String extractDelimiters(String input) {
    String delimiter = STANDARD_DELIMITERS;
    if (input.startsWith("//[")) {
      delimiter = multipleDelimiterSplitter(input);
    } else if (input.startsWith("//")) {
      delimiter = simpleDelimiterSplitter(input);
    }
    return delimiter;
  }

  private static String simpleDelimiterSplitter(String input) {
    String delimiter;
    delimiter = input.substring(2, 3);
    return delimiter;
  }

  private static String multipleDelimiterSplitter(String input) {
    String delimiter;
    int endDelimiterDeclaration;
    endDelimiterDeclaration = findEndOfDelimiters(input);
    delimiter = input.substring(2, endDelimiterDeclaration);
    if (delimiter.startsWith("[")) {
      delimiter = buildMultipleDelimiterRegex(delimiter);
    }
    return delimiter;
  }

  private static String buildMultipleDelimiterRegex(String delimiter) {
    Pattern multipleDelimitersPtn = Pattern.compile(MULTIPLE_DELIMITERS_REGEX);
    Matcher multipleDelimitersMatcher = multipleDelimitersPtn.matcher(delimiter);
    StringBuilder delimitersRegex = new StringBuilder();
    while (multipleDelimitersMatcher.find()) {
      if (delimitersRegex.length() > 0) {
        delimitersRegex.append("|");
      }
      delimitersRegex.append("(");
      delimitersRegex.append(escapeSpecialRegexChars(multipleDelimitersMatcher.group(1)));
      delimitersRegex.append(")");
    }
    delimiter = delimitersRegex.toString();
    return delimiter;
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
