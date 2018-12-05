import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * The statistics class uses the Observation class and adds the
 * date,numberOfValidStations, and StatsType
 * 
 * @author Robinson Shin
 * @version 2018-10-28
 */

public class Statistics extends Observation implements DateTimeComparable
{
    // The format of the date
    protected String DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss z";

    // The format for the DateTimeFormat
    protected DateTimeFormatter format;

    // The date in GregorianCalendar format
    private GregorianCalendar utcDateTime;

    // The date in ZonedDateTime format
    private ZonedDateTime zdtDateTime;

    // The number of stations with valid data
    private int numberOfReportingStations;

    // The StatsType: MINIMUM, MAXIMUM, AVERAGE, or TOTAL
    private StatsType statType;

    /**
     * The Statistics constructor using a GregorianCalendar
     * 
     * @param value
     *            the data value
     * @param stid
     *            the data stid
     * @param dateTime
     *            the data time
     * @param numberOfValidStations
     *            the number of stations that had valid data
     * @param inStatType
     *            the StatsType of the data
     */
    public Statistics(double value, String stid, GregorianCalendar dateTime, int numberOfValidStations,
            StatsType inStatType)
    {
        super(value, stid);
        utcDateTime = dateTime;
        numberOfReportingStations = numberOfValidStations;
        statType = inStatType;
    }

    /**
     * The Statistics constructor using a ZonedDateTime
     * 
     * @param value
     *            the data value
     * @param stid
     *            the data stid
     * @param dateTime
     *            the data time
     * @param numberOfValidStations
     *            the number of stations that had valid data
     * @param inStatType
     *            the StatsType of the data
     */
    public Statistics(double value, String stid, ZonedDateTime dateTime, int numberOfValidStations,
            StatsType inStatType)
    {
        super(value, stid);
        zdtDateTime = dateTime;
        numberOfReportingStations = numberOfValidStations;
        statType = inStatType;
    }

    /**
     * Creates a GregorianCalendar from the inputted String date
     * 
     * @param dateTimeStr
     *            the date in String format
     * @return calendar the date in GregorianCalendar format
     * @throws ParseException
     *             throws ParseException
     */
    public GregorianCalendar createDateFromString(String dateTimeStr) throws ParseException
    {
        SimpleDateFormat date = new SimpleDateFormat(DATE_TIME_FORMAT);

        Date formattedDate = date.parse(dateTimeStr);
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(formattedDate);
        return calendar;
    }

    /**
     * Creates a ZonedDateTime from the inputted String date
     * 
     * @param dateTimeStr
     *            the date in String format
     * @return calendar the date in ZonedDateTime format
     */
    public ZonedDateTime createZDateFromString(String dateTimeStr)
    {
        format = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);

        ZonedDateTime calendar = ZonedDateTime.parse(dateTimeStr, format);
        return calendar;
    }

    /**
     * Creates a string from the inputted GregorianCalendar
     * 
     * @param calendar
     *            the date in GregorianCalendar format
     * @return dateString the date in String format
     */
    public String createStringFromDate(GregorianCalendar calendar)
    {
        SimpleDateFormat date = new SimpleDateFormat(DATE_TIME_FORMAT);
        String dateString = date.format(calendar.getTime());
        return dateString;
    }

    /**
     * Creates a string from the inputted ZonedDateTime
     * 
     * @param calendar
     *            the date in ZonedDateTime format
     * @return calendar the date in String format
     */
    public String createStringFromDate(ZonedDateTime calendar)
    {
        format = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);
        String dateString = calendar.format(format);
        return dateString;
    }

    /**
     * Gets the numberOfReportingStations
     * 
     * @return numberOfReportingStations
     */
    public int getNumberOfReportingStations()
    {
        return this.numberOfReportingStations;
    }

    /**
     * Gets the utcDateTime in a string format
     * 
     * @return String version of utcDateTime
     */
    public String getUTCDateTimeString()
    {
        return createStringFromDate(utcDateTime);
    }

    /**
     * Tests if the data date in GregorianCalender is newer than the compared
     * date
     * 
     * @return true if data date is newer than the compared
     * @return false if data date is older than the compared
     */
    public boolean newerThan(GregorianCalendar inDateTime)
    {
        int comparedValue = utcDateTime.compareTo(inDateTime);

        // Tests if it's newer
        return (comparedValue > 0);

    }

    /**
     * Tests if the data date in GregorianCalender is older than the compared
     * date
     * 
     * @return true if data date is older than compared date
     * @return false if data date is newer than compared date
     */
    public boolean olderThan(GregorianCalendar inDateTime)
    {
        int comparedValue = utcDateTime.compareTo(inDateTime);

        // Tests if it's older
        return (comparedValue < 0);
    }

    /**
     * Tests if the data date in GregorianCalender is the same as the compared
     * date
     * 
     * @return true if data is the same as compared date
     * @return false if the data is not the same as compared date
     */
    public boolean sameAs(GregorianCalendar inDateTime)
    {
        int comparedValue = utcDateTime.compareTo(inDateTime);

        // Tests if it's same
        return (comparedValue == 0);
    }

    /**
     * Tests if the data date in ZonedDateTime is newer than the compared date
     * 
     * @return true if data date is newer than the compared
     * @return false if data date is older than the compared
     */
    public boolean newerThan(ZonedDateTime inDateTime)
    {
        int comparedValue = zdtDateTime.compareTo(inDateTime);

        // Tests if it's newer
        return (comparedValue > 0);
    }

    /**
     * Tests if the data date in ZonedDateTime is newer than the compared date
     * 
     * @return true if data date is older than the compared
     * @return false if data date is newer than the compared
     */
    public boolean olderThan(ZonedDateTime inDateTime)
    {
        int comparedValue = zdtDateTime.compareTo(inDateTime);

        // Tests if it's older
        return (comparedValue < 0);
    }

    /**
     * Tests if the data date in ZonedDateTime is the same as the compared date
     * 
     * @return true if data is the same as compared date
     * @return false if the data is not the same as compared date
     */
    public boolean sameAs(ZonedDateTime inDateTime)
    {
        int comparedValue = zdtDateTime.compareTo(inDateTime);

        // Tests if it's same
        return (comparedValue == 0);
    }

    /**
     * The toString for the Statistics class
     * 
     * @return the utcDateTime in string format
     */
    public String toString()
    {
        return String.format(createStringFromDate(utcDateTime) + " " + statType);
    }
}
