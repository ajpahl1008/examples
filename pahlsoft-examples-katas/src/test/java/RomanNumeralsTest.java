import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RomanNumeralsTest {
    RomanNumerals converter;
    @Before
    public void setup() {
        converter = new RomanNumerals();
    }
    @Test
    public void testOne() {
        Assert.assertEquals("I",converter.numberToNumeral(1));
    }

    @Test
    public void testThree() {
        Assert.assertEquals("III",converter.numberToNumeral(3));
    }

    @Test
    public void testNine() {
        Assert.assertEquals("IX",converter.numberToNumeral(9));
    }


}



