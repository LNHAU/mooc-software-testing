package tudelft.sum;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TwoNumbersSumTest {
    @ParameterizedTest(name="{0} : ({1}, {2}) = {3}")
    @CsvSource(delimiter = ';', value = {"NullParameters; null; null; []", "NullParameter; null; [6, 4, 5]; [6, 4, 5]",
                "EmptyParameters; []; []; []", "EmptyParameter; [1, 2, 3]; []; [1, 2, 3]",
                "OneDigitParameters; [5]; [5]; [1, 0]", "DifferentSizeParameters; [4, 9, 6]; [4]; [5, 0, 0]",
                "DifferentSizeParameters; [9, 3]; [8, 3, 0, 1]; [8, 3, 9, 4]"})
    public void testAlgorithm(String partition, String firstStr, String secondStr, String expectedResultStr) {
        TwoNumbersSum tns = new TwoNumbersSum();
        ArrayList<Integer> first, second;
        if ("null".equals(firstStr)) first = null;
        else {
            first = new ArrayList<Integer>();
            if (firstStr.length() > 2) {
                List<String> firstInts = Arrays.asList(Arrays.stream(firstStr.substring(1, firstStr.length() - 1)
                        .split(","))
                        .map(String::trim) // supprime l'/les espace(s) qui précède(nt) et/ou suive(nt) le délimitateur ','
                        .toArray(String[]::new));
                for (int i = 0; i < firstInts.size(); i++) {
                    first.add(Integer.parseInt(firstInts.get(i)));
                }
            }
        }
        if ("null".equals(secondStr)) second = null;
        else {
            second = new ArrayList<Integer>();
            if (secondStr.length() > 2) {
                List<String> secondInts = Arrays.asList(Arrays.stream(secondStr.substring(1, secondStr.length() - 1)
                        .split(","))
                        .map(String::trim) // supprime l'/les espace(s) qui précède(nt) et/ou suive(nt) le délimitateur ','
                        .toArray(String[]::new));
                for (int i = 0; i < secondInts.size(); i++) {
                    second.add(Integer.parseInt(secondInts.get(i)));
                }
            }
        }
        ArrayList<Integer> result = tns.addTwoNumbers(first, second);
        Assertions.assertEquals(expectedResultStr, result.toString());



    }

}
