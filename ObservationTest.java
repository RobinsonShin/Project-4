import org.junit.Assert;
import org.junit.Test;

/**
 * Tests the ObservationTest class
 * 
 * @author Robinson Shin
 * @version 2018-09-26
 */
public class ObservationTest
{
    /**
     * Tests the constructor for the Observation
     */
    @Test
    public void ObservationConstructorTest()
    {
        Observation test = new Observation(3, "test");
        Assert.assertEquals("test", test.getStid());
        Assert.assertEquals(3, test.getValue(), 0.01);
    }

    /**
     * Tests the isValidTest method
     */
    @Test
    public void isValidTest()
    {
        Observation test = new Observation(-999, "test");
        Observation test2 = new Observation(3, "test");
        Assert.assertEquals(true, test2.isValid());
        Assert.assertEquals(false, test.isValid());
    }

    /**
     * Tests the toString for the Observation Class
     */
    @Test
    public void toStringTest()
    {
        Observation test = new Observation(3, "test");
        String expected = "stid: test, Value: 3.00";
        String actual = test.toSting();
        Assert.assertEquals(expected, actual);
    }
}
