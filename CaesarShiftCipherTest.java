package tudelft.caesarshift;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class CaesarShiftCipherTest {

    @Test
    public void NullString(){
        CaesarShiftCipher csc = new CaesarShiftCipher();
        String result = csc.CaesarShiftCipher(null, 99);
        Assertions.assertEquals(null, result);
    }

    @Test
    public void EmptyString(){
        CaesarShiftCipher csc = new CaesarShiftCipher();
        String result = csc.CaesarShiftCipher("", -5);
        Assertions.assertEquals("", result);
    }

    @Test
    public void OneSpaceString() {
        CaesarShiftCipher csc = new CaesarShiftCipher();
        String result = csc.CaesarShiftCipher(" ", 6);
        Assertions.assertEquals(" ", result);
    }

    @Test
    public void ManySpacesString() {
        CaesarShiftCipher csc = new CaesarShiftCipher();
        String result = csc.CaesarShiftCipher("    ", -3);
        Assertions.assertEquals("    ", result);
    }

    @ParameterizedTest(name = "{0} : (\"{1}\", {2}) = \"{3}\"")
    @CsvSource({"0Shift, abc, 0, abc", "UpperCharString, H, 8, invalid", "UpperLettersString, FD S, -2, invalid",
                "LowerCharString, d, -1, c", "LowerCharString, z, 1, a", "LowerCharString, a, 1, b",
                "LowerCharString, z, -28, x", "LowerCharString, a, 35, j", "LowerString, klh, -26, klh",
                "LowerString, hfelhoezi, -54, fdcjfmcxg", "LowerString, djhdcyithcdyh, 26, djhdcyithcdyh"})
    public void TestAlgorithm(String partition, String message, int shift, String expectedResult) {
        CaesarShiftCipher csc = new CaesarShiftCipher();
        String result = csc.CaesarShiftCipher(message, shift);
        Assertions.assertEquals(expectedResult, result);
    }

}
