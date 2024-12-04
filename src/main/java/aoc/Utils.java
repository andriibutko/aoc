package aoc;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;


public class Utils {
    
    public static List<String> splitLines(String input) {
        return Arrays.asList(input.split(System.lineSeparator()));
    }

    public static Stream<String> streamLines(String input) {
        return Stream.of(input.split(System.lineSeparator()));
    }

}
