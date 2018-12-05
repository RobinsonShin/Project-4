import java.time.ZonedDateTime;
import java.util.GregorianCalendar;

/**
 * The interface for the Statistics class
 * 
 * @author Robinson Shin
 * @version 2018-10-28
 */

public interface DateTimeComparable
{

    /**
     * Abstract method for newerThan using GregorianCalendar
     * 
     * @param inDateTimeUTC
     *            using GregorianCalendar
     * @return true if data date is newer than compared date
     * @return false if data date is older than compared date
     */
    public abstract boolean newerThan(GregorianCalendar inDateTimeUTC);

    /**
     * Abstract method for olderThan using GregorianCalendar
     * 
     * @param inDateTimeUTC
     *            using GregorianCalendar
     * @return true if data date is older than compared date
     * @return false if data date is newer than compared date
     */
    public abstract boolean olderThan(GregorianCalendar inDateTimeUTC);

    /**
     * Abstract method for sameAs using GregorianCalendar
     * 
     * @param inDateTimeUTC
     *            using GregorianCalendar
     * @return true if data is the same as compared date
     * @return false if the data is not the same as compared date
     */
    public abstract boolean sameAs(GregorianCalendar inDateTimeUTC);

    /**
     * Abstract method for newerThan using ZonedDateTime
     * 
     * @param inDateTimeUTC
     *            using ZonedDateTime
     * @return true if data date is newer than compared date
     * @return false if data date is older than compared date
     */
    public abstract boolean newerThan(ZonedDateTime inDateTimeUTC);

    /**
     * Abstract method for olderThan using ZonedDateTime
     * 
     * @param inDateTimeUTC
     *            using ZonedDateTime
     * @return true if data date is older than compared date
     * @return false if data date is newer than compared date
     */
    public abstract boolean olderThan(ZonedDateTime inDateTimeUTC);

    /**
     * Abstract method for sameAs using ZonedDateTime
     * 
     * @param inDateTimeUTC
     *            using ZonedDateTime
     * @return true if data is the same as compared date
     * @return false if the data is not the same as compared date
     */
    public abstract boolean sameAs(ZonedDateTime inDateTimeUTC);
}
