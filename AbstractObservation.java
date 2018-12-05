/**
 * The abstract for the Observation class
 * @author Robinson Shin
 * @version 2018-10-03
 */

public abstract class AbstractObservation
{
    protected boolean valid;
    
    public AbstractObservation()
    {     
        valid = true;
    }
    
    public boolean isValid()
    {
        return valid;
    }
}
