import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.GregorianCalendar;

import org.junit.Assert;
import org.junit.Test;

/**
 * Tests the Statistics class
 * 
 * @author Robinson Shin
 * @version 2018-10-28
 */
public class StatisticsTest
{
    /**
     * Tests the createDateFromStringGregorian Method in Statistics class
     * 
     */
    @Test
    public void createDateFromStringGregorianTest()
    {
        try
        {
            String test = "2018-09-30T17:45:00 CDT";
            GregorianCalendar testCal = new GregorianCalendar(2018, 8, 30, 17, 45);
            Statistics testStat = new Statistics(1, "test", testCal, 10, StatsType.MINIMUM);
            GregorianCalendar actual = testStat.createDateFromString(test);

            GregorianCalendar expected = new GregorianCalendar(2018, 8, 30, 17, 45);
            Assert.assertEquals(expected, actual);
        }

        catch (Exception e)
        {
            Assert.fail("Legal expression threw an Exception: " + e.getMessage());
        }
    }

    /**
     * Tests the createZDateFromString Method in Statistics class
     */
    @Test
    public void createZDateFromStringTest()
    {
        String test = "2018-09-30T17:45:00 CDT";
        ZoneId id = ZoneId.of("America/Chicago");
        ZonedDateTime testCal = ZonedDateTime.of(2018, 9, 30, 17, 45, 0, 0, id);
        Statistics testStat = new Statistics(1, "test", testCal, 10, StatsType.MINIMUM);
        ZonedDateTime actual = testStat.createZDateFromString(test);

        ZonedDateTime expected = ZonedDateTime.of(2018, 9, 30, 17, 45, 0, 0, id);
        Assert.assertEquals(expected, actual);
    }

    /**
     * Tests the createStringFromDate using Gregorian method in Statistics class
     */
    @Test
    public void createStringFromDateGregorianTest()
    {
        try
        {
            GregorianCalendar test = new GregorianCalendar(2018, 9, 30, 17, 45);
            Statistics testStat = new Statistics(1, "test", test, 10, StatsType.MINIMUM);

            String actual = testStat.getUTCDateTimeString();
            String expected = "2018-09-30T17:45:00 CDT";

            Assert.assertEquals(expected, actual);
        }

        catch (Exception e)
        {
            Assert.fail("Legal expression threw an Exception: " + e.getMessage());
        }
    }

    /**
     * Tests the createStringFromDate using ZonedDateTime method in Statistics
     * class
     */
    @Test
    public void createStringFromDateZonedDateTimeTest()
    {
        ZoneId id = ZoneId.of("America/Chicago");
        ZonedDateTime test = ZonedDateTime.of(2018, 9, 30, 17, 45, 0, 0, id);
        Statistics testStat = new Statistics(1, "test", test, 10, StatsType.MINIMUM);

        String actual = testStat.createStringFromDate(test);
        String expected = "2018-09-30T17:45:00 CDT";

        Assert.assertEquals(expected, actual);
    }

    /**
     * Tests the newerThan using Gregorian Method in Statistics class
     */
    @Test
    public void newerThanGregorianTest()
    {
        GregorianCalendar test = new GregorianCalendar(2018, 9, 30, 17, 45);
        GregorianCalendar testDateTrue = new GregorianCalendar(2017, 9, 30, 17, 45);
        GregorianCalendar testDateFalse = new GregorianCalendar(2019, 9, 30, 17, 45);

        Statistics testStat = new Statistics(1, "test", test, 10, StatsType.MINIMUM);

        boolean actualTrue = testStat.newerThan(testDateTrue);
        boolean expectedTrue = true;
        boolean actualFalse = testStat.newerThan(testDateFalse);
        boolean expectedFalse = false;

        Assert.assertEquals(expectedTrue, actualTrue);
        Assert.assertEquals(expectedFalse, actualFalse);
    }

    /**
     * Tests the olderThan using Gregorian Method in Statistics class
     */
    @Test
    public void olderThanGregorianTest()
    {
        GregorianCalendar test = new GregorianCalendar(2018, 9, 30, 17, 45);
        GregorianCalendar testDateTrue = new GregorianCalendar(2019, 9, 30, 17, 45);
        GregorianCalendar testDateFalse = new GregorianCalendar(2017, 9, 30, 17, 45);

        Statistics testStat = new Statistics(1, "test", test, 10, StatsType.MINIMUM);

        boolean actualTrue = testStat.olderThan(testDateTrue);
        boolean actualFalse = testStat.olderThan(testDateFalse);
        boolean expectedTrue = true;
        boolean expectedFalse = false;

        Assert.assertEquals(expectedTrue, actualTrue);
        Assert.assertEquals(expectedFalse, actualFalse);
    }

    /**
     * Tests the sameAs using Gregorian Method in Statistics class
     */
    @Test
    public void sameAsGregorianTest()
    {
        GregorianCalendar test = new GregorianCalendar(2018, 9, 30, 17, 45);
        GregorianCalendar testDateTrue = new GregorianCalendar(2018, 9, 30, 17, 45);
        GregorianCalendar testDateFalse = new GregorianCalendar(2017, 9, 30, 17, 45);

        Statistics testStat = new Statistics(1, "test", test, 10, StatsType.AVERAGE);

        boolean actualTrue = testStat.sameAs(testDateTrue);
        boolean expectedTrue = true;
        boolean actualFalse = testStat.sameAs(testDateFalse);
        boolean expectedFalse = false;

        Assert.assertEquals(expectedTrue, actualTrue);
        Assert.assertEquals(expectedFalse, actualFalse);
    }

    /**
     * Tests the newerThan using ZonedDateTime Method in Statistics class
     */
    @Test
    public void newerThanZonedDateTimeTest()
    {
        ZoneId id = ZoneId.of("America/Chicago");
        ZonedDateTime test = ZonedDateTime.of(2018, 9, 30, 17, 45, 0, 0, id);
        ZonedDateTime testDateTrue = ZonedDateTime.of(2017, 9, 30, 17, 45, 0, 0, id);
        ZonedDateTime testDateFalse = ZonedDateTime.of(2019, 9, 30, 17, 45, 0, 0, id);

        Statistics testStat = new Statistics(1, "test", test, 10, StatsType.MINIMUM);

        boolean actualTrue = testStat.newerThan(testDateTrue);
        boolean expectedTrue = true;
        boolean actualFalse = testStat.newerThan(testDateFalse);
        boolean expectedFalse = false;

        Assert.assertEquals(expectedTrue, actualTrue);
        Assert.assertEquals(expectedFalse, actualFalse);
    }

    /**
     * Tests the olderThan using ZonedDateTime method in Statistics class
     */
    @Test
    public void olderThanZonedDateTimeTest()
    {
        ZoneId id = ZoneId.of("America/Chicago");
        ZonedDateTime test = ZonedDateTime.of(2018, 9, 30, 17, 45, 0, 0, id);
        ZonedDateTime testDateTrue = ZonedDateTime.of(2019, 9, 30, 17, 45, 0, 0, id);
        ZonedDateTime testDateFalse = ZonedDateTime.of(2017, 9, 30, 17, 45, 0, 0, id);

        Statistics testStat = new Statistics(1, "test", test, 10, StatsType.MINIMUM);

        boolean actualTrue = testStat.olderThan(testDateTrue);
        boolean actualFalse = testStat.olderThan(testDateFalse);
        boolean expectedTrue = true;
        boolean expectedFalse = false;

        Assert.assertEquals(expectedTrue, actualTrue);
        Assert.assertEquals(expectedFalse, actualFalse);
    }

    /**
     * Tests the sameAs using ZonedDateTime method in Statistics class
     */
    @Test
    public void sameAsZonedDateTimeTest()
    {
        ZoneId id = ZoneId.of("America/Chicago");
        ZonedDateTime test = ZonedDateTime.of(2018, 9, 30, 17, 45, 0, 0, id);
        ZonedDateTime testDateTrue = ZonedDateTime.of(2018, 9, 30, 17, 45, 0, 0, id);
        ZonedDateTime testDateFalse = ZonedDateTime.of(2017, 9, 30, 17, 45, 0, 0, id);

        Statistics testStat = new Statistics(1, "test", test, 10, StatsType.AVERAGE);

        boolean actualTrue = testStat.sameAs(testDateTrue);
        boolean expectedTrue = true;
        boolean actualFalse = testStat.sameAs(testDateFalse);
        boolean expectedFalse = false;

        Assert.assertEquals(expectedTrue, actualTrue);
        Assert.assertEquals(expectedFalse, actualFalse);
    }

    /**
     * Tests the toString for the Statistics class
     */
    @Test
    public void toStringTest()
    {
        GregorianCalendar test = new GregorianCalendar(2018, 9, 30, 17, 45);
        Statistics testStat = new Statistics(1, "test", test, 10, StatsType.TOTAL);
        String actual = testStat.toString();
        String expected = "2018-09-30T17:45:00 CDT TOTAL";
        Assert.assertEquals(expected, actual);
    }

    /**
     * Tests the NumberOfStation getter
     */
    @Test
    public void getNumberOfStationsTest()
    {
        GregorianCalendar test = new GregorianCalendar(2018, 9, 30, 17, 45);
        Statistics testStat = new Statistics(1, "test", test, 10, StatsType.MAXIMUM);
        int expected = 10;
        int actual = testStat.getNumberOfReportingStations();
        Assert.assertEquals(expected, actual);
    }
}
