import org.junit.Assert;
import org.junit.Test;

/**
 * Tests the MapData class
 * 
 * @author Robinson Shin
 * @version 2018-10-28
 */
public class MapDataTest
{

    /**
     * Tests the createFileName method
     */
    @Test
    public void createFileNameTest()
    {
        MapData test = new MapData(2018, 9, 18, 6, 0, "data");
        String testString = test.createFileName(2018, 9, 18, 6, 0, "data");

        Assert.assertEquals("data/201809180600.mdf", testString);
    }

    /**
     * Tests the parseFileTest method
     */
    @Test
    public void parseFileTest()
    {
        try
        {
            MapData test = new MapData(2018, 8, 30, 17, 45, "data");
            test.parseFile();
            String SRAD = "SRAD";
            String TA9M = "TA9M";
            String TAIR = "TAIR";

            Assert.assertEquals(828.1, test.getStatistics(StatsType.AVERAGE, SRAD).getValue(), 0.1);
            Assert.assertEquals(163.0, test.getStatistics(StatsType.MINIMUM, SRAD).getValue(), 0.1);
            Assert.assertEquals(968.0, test.getStatistics(StatsType.MAXIMUM, SRAD).getValue(), 0.1);
            Assert.assertEquals(97720.0, test.getStatistics(StatsType.TOTAL, SRAD).getValue(), 0.1);
            Assert.assertEquals(32.4, test.getStatistics(StatsType.AVERAGE, TAIR).getValue(), 0.1);
            Assert.assertEquals(20.8, test.getStatistics(StatsType.MINIMUM, TAIR).getValue(), 0.1);
            Assert.assertEquals(36.5, test.getStatistics(StatsType.MAXIMUM, TAIR).getValue(), 0.1);
            Assert.assertEquals(31.6, test.getStatistics(StatsType.AVERAGE, TA9M).getValue(), 0.1);
            Assert.assertEquals(20.7, test.getStatistics(StatsType.MINIMUM, TA9M).getValue(), 0.1);
            Assert.assertEquals(34.9, test.getStatistics(StatsType.MAXIMUM, TA9M).getValue(), 0.1);

            MapData testError = new MapData(2018, 9, 18, 7, 00, "data");
            testError.parseFile();

            Assert.assertEquals(0, testError.getStatistics(StatsType.AVERAGE, SRAD).getValue(), 0.1);
            Assert.assertEquals(-999.0, testError.getStatistics(StatsType.MINIMUM, SRAD).getValue(), 0.1);
            Assert.assertEquals(-999.0, testError.getStatistics(StatsType.MAXIMUM, SRAD).getValue(), 0.1);
            Assert.assertEquals(0, testError.getStatistics(StatsType.TOTAL, SRAD).getValue(), 0.1);
            Assert.assertEquals(0, testError.getStatistics(StatsType.AVERAGE, TAIR).getValue(), 0.1);
            Assert.assertEquals(-999.0, testError.getStatistics(StatsType.MINIMUM, TAIR).getValue(), 0.1);
            Assert.assertEquals(-999.0, testError.getStatistics(StatsType.MAXIMUM, TAIR).getValue(), 0.1);
            Assert.assertEquals(0, testError.getStatistics(StatsType.AVERAGE, TA9M).getValue(), 0.1);
            Assert.assertEquals(-999.0, testError.getStatistics(StatsType.MINIMUM, TA9M).getValue(), 0.1);
            Assert.assertEquals(-999.0, testError.getStatistics(StatsType.MAXIMUM, TA9M).getValue(), 0.1);
        }

        catch (Exception e)
        {
            Assert.fail("Legal expression threw an Exception: " + e.getMessage());
        }
    }

    /**
     * Tests the toString for the MapData Class
     */
    @Test
    public void toStringTest()
    {
        try
        {
            MapData test = new MapData(2018, 8, 30, 17, 45, "data");
            test.parseFile();

            String expected = "=========================================================\n"
                    + "=== 2018-08-30 17:45 ===\n" + "=========================================================\n"
                    + "Maximum Air Temperature[1.5m] = 36.5 C at HOOK\n"
                    + "Minimum Air Temperature[1.5m] = 20.8 C at MIAM\n"
                    + "Average Air Temperature[1.5m] = 32.4 C at Mesonet\n"
                    + "=========================================================\n"
                    + "=========================================================\n"
                    + "Maximum Air Temperature[9.0m] = 34.9 C at HOOK\n"
                    + "Minimum Air Temperature[9.0m] = 20.7 C at MIAM\n"
                    + "Average Air Temperature[9.0m] = 31.6 C at Mesonet\n"
                    + "=========================================================\n"
                    + "=========================================================\n"
                    + "Maximum Solar Radiation[1.5m] = 968.0 W/m^2 at SLAP\n"
                    + "Minimum Solar Radiation[1.5m] = 163.0 W/m^2 at MIAM\n"
                    + "Average Solar Radiation[1.5m] = 828.1 W/m^2 at Mesonet\n"
                    + "=========================================================\n";
            String actual = test.toString();

            test.parseFile();

            Assert.assertEquals(expected, actual);
        }

        catch (Exception e)
        {
            Assert.fail("Legal expression threw an Exception: " + e.getMessage());
        }
    }
}
