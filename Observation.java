
/**
 * Determines if the single data is usable or not. If it isn't then it gets
 * flagged as unusable.
 * 
 * @author Robinson Shin, Referenced Dan Tran and Jordan Dewberry
 * @version 2018-10-03
 */
public class Observation extends AbstractObservation
{
    // The value associated with this Observation
    private double value;

    // The STID associated with this Observation
    private String stid;

    /**
     * This is the constructor for the Observation class
     * 
     * @param value
     *            the associated value
     * @param stid
     *            the associated stid
     */
    public Observation(double value, String stid)
    {
        this.value = value;
        this.stid = stid;
    }

    /**
     * Returns the current stored value
     * 
     * @return value as a double
     */
    public double getValue()
    {
        return this.value;
    }

    /**
     * Tests if the value is valid. If the value is less than or equal to -900,
     * it is an invalid value All other values are valid.
     * 
     * @return valid as a boolean
     */
    public boolean isValid()
    {
        if (value <= -900)
        {
            valid = false;
        }

        else
        {
            valid = true;
        }
        return valid;
    }

    /**
     * Returns the current stored stid
     * 
     * @return stid as a String
     */
    public String getStid()
    {
        return stid;
    }

    /**
     * The toString for the Observation class
     * 
     * @return the string in the format: "stid: (stid), Value: (value)"
     */
    public String toSting()
    {
        return String.format("stid: %s, Value: %.2f", this.stid, this.value);
    }
}
