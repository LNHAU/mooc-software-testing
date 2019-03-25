package tudelft.ghappy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class GHappyTest {

    @ParameterizedTest(name = "{0} : ({1}) = {2}")
    @CsvSource({"NullString, null, true", "EmptyString, \"\", true",
                "OneCharString, a, true", "OneCharString, g, false",
                "TwoCharsString, bw, true", "TwoCharsString, gj, false", "TwoCharsString, zg, false", "TwoCharsString, gg, true",
                "ThreeCharsString, jto, true", "ThreeCharsString, gsg, false", "ThreeCharsString, ggf, true",
                "ThreeCharsString, hgg, true", "ThreeCharsString, ggg, true",
                "LongEvenStringWithoutg, edfihebcibcjkGkjcn, true", "LongOddStringWithoutg, bdciubGudbncienci, true",
                "LongEvenStringWithOneg, edfihebcibcgjkbkjcnb, false", "LongOddStringWithOneg, bdciubiudbncignci, false",
                "LongEvenStringWithTwog, edfigebcibcgjkbkjcnb, false", "LongOddStringWithTwog, bdciubigdbncignci, false",
                "LongEvenStringWithALotOfg, ggfigebcibcgjkbkjcnb, false", "LongOddStringWithALotOfg, bdciubigdbncigngg, false",
                "LongEvenStringWithALotOfg, ggfigggcibcggggkjcnb, true", "LongOddStringWithALotOfg, bdgggbiggbncggggg, true"})
    public void testAlgorithm(String partition, String string, boolean expectedResult){
        GHappy gh = new GHappy();
        boolean result = gh.gHappy(string);
        Assertions.assertTrue(result == expectedResult);
    }
}
