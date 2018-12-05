import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.EnumMap;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.TreeMap;

/**
 * A map data of the day that is read from the text file. This keeps track of
 * year, month, day, hour, and minute.
 * 
 * @author Robinson Shin
 * @version 2018-10-28
 */
public class MapData
{
    // HashMap with the Observation arrays
    private HashMap<String, ArrayList<Observation>> dataCatalog;

    // EnumMap with the StatsType and TreeMap
    private EnumMap<StatsType, TreeMap<String, Statistics>> statistics;

    // TreeMap that holds the paramPositions
    private TreeMap<String, Integer> paramPositions;

    // Number of observations that may be missing for statistics to be
    // calculated
    private static final int NUMBER_OF_MISSING_OBSERVATIONS = 10;

    // Number of valid stations
    private Integer numberOfStations = null;

    // String name to search for TA9M
    private String TA9M = "TA9M";

    // String name to search for TAIR
    private String TAIR = "TAIR";

    // String name to search for SRAD
    private String SRAD = "SRAD";

    // String name to search for STID
    private String STID = "STID";

    // The String inputed for average and total statistic Observations
    private String MESONET = "Mesonet";

    // The String for the fileName
    private String fileName;

    // The MapData date
    private GregorianCalendar utcDateTime;

    /**
     * This is the constructor for the MapData class
     * 
     * @param year
     *            the entered year
     * @param month
     *            the entered month
     * @param day
     *            the entered day
     * @param hour
     *            the entered hour
     * @param minute
     *            the entered minute
     * @param directory
     *            the entered directory
     */
    public MapData(int year, int month, int day, int hour, int minute, String directory)
    {
        utcDateTime = new GregorianCalendar(year, month, day, hour, minute);
        fileName = createFileName(year, month, day, hour, minute, directory);
    }

    /**
     * Takes the year, month, day, hour, and minute and outputs a string in the
     * format: yyyyMMddHHmm.mdf
     * 
     * @param year
     *            the inputted year
     * @param month
     *            the inputted month
     * @param day
     *            the inputted day
     * @param hour
     *            the inputted hour
     * @param minute
     *            the inputted minute
     * @param directory
     *            the inputted directory
     * @return fileName the constructed string
     */
    public String createFileName(int year, int month, int day, int hour, int minute, String directory)
    {
        String formattedMonth = String.format("%02d", month);
        String formattedDay = String.format("%02d", day);
        String formattedHour = String.format("%02d", hour);
        String formattedMinute = String.format("%02d", minute);

        fileName = directory + "/" + Integer.toString(year) + formattedMonth + formattedDay + formattedHour
                + formattedMinute + ".mdf";

        return fileName;
    }

    /**
     * Takes the string and sets the positions for SRAD, TA9M, TAIR, and STID
     * 
     * @param inParamStr
     *            the string to read for the positions
     * @throws IOException
     *             throws IOException
     */
    private void parseParamHeader(String inParamStr) throws IOException
    {
        paramPositions = new TreeMap<String, Integer>();
        String[] info;
        info = inParamStr.trim().split("\\s+");

        for (int a = 0; a < info.length; a++)
        {
            // Finds the TA9M position
            if (info[a].equalsIgnoreCase(TA9M))
            {
                paramPositions.put(TA9M, a);
            }

            // Finds the TAIR position
            else if (info[a].equalsIgnoreCase(TAIR))
            {
                paramPositions.put(TAIR, a);
            }

            // Finds the SRAD position
            else if (info[a].equalsIgnoreCase(SRAD))
            {
                paramPositions.put(SRAD, a);
            }

            // Finds the STID position
            else if (info[a].equalsIgnoreCase(STID))
            {
                paramPositions.put(STID, a);
            }
        }
    }

    /**
     * Finds the position that the inParamStr is
     * 
     * @param inParamStr
     *            String to find: SRAD, TAIR, TA9M, or STID
     * @return Integer of the position of ParamStr
     * @throws IOException
     *             throws IOException
     */
    public Integer getIndexOf(String inParamStr) throws IOException
    {
        return paramPositions.get(inParamStr);
    }

    /**
     * Reads the file, stores the data into their respective arrays, then runs
     * the statistics for tair, ta9m, and srad
     * 
     * @throws IOException
     *             throws IOException
     */
    public void parseFile() throws IOException
    {
        String strg; // String containing a line of data from the file.
        String[] info;
        numberOfStations = 0;
        ArrayList<Observation> sradData = new ArrayList<Observation>();
        ArrayList<Observation> tairData = new ArrayList<Observation>();
        ArrayList<Observation> ta9mData = new ArrayList<Observation>();

        BufferedReader br = new BufferedReader(new FileReader(fileName));
        // Throws out the first 3 lines
        strg = br.readLine().trim();
        strg = br.readLine().trim();
        strg = br.readLine().trim();

        // Finds the positions
        parseParamHeader(strg);

        // Reads the first line of data
        strg = br.readLine();

        while (strg != null)
        {
            info = strg.trim().split("\\s+");
            sradData.add(new Observation(Integer.parseInt(info[getIndexOf(SRAD)]), info[getIndexOf(STID)]));
            tairData.add(new Observation(Float.parseFloat(info[getIndexOf(TAIR)]), info[getIndexOf(STID)]));
            ta9mData.add(new Observation(Float.parseFloat(info[getIndexOf(TA9M)]), info[getIndexOf(STID)]));

            numberOfStations++;
            strg = br.readLine();
        }

        br.close();

        prepareDataCatalog();
        dataCatalog.put(SRAD, sradData);
        dataCatalog.put(TAIR, tairData);
        dataCatalog.put(TA9M, ta9mData);

        calculateStatistics();
    }

    /**
     * Calculates all the Statistics for TA9M, TAIR, and SRAD
     */
    private void calculateAllStatistics()
    {
        int notValid = 0;
        String stidMin = "";
        String stidMax = "";
        double minValue = Integer.MAX_VALUE;
        double maxValue = Integer.MIN_VALUE;
        double totalValue = 0;
        int numberOfValidStations = 0;
        double averageValue = 0;

        TreeMap<String, Statistics> maxValues = new TreeMap<String, Statistics>();
        TreeMap<String, Statistics> minValues = new TreeMap<String, Statistics>();
        TreeMap<String, Statistics> totalValues = new TreeMap<String, Statistics>();
        TreeMap<String, Statistics> averageValues = new TreeMap<String, Statistics>();
        statistics = new EnumMap<StatsType, TreeMap<String, Statistics>>(StatsType.class);

        // Starts calculations for SRAD Data
        // Calculates the minimum value
        for (int i = 0; i < dataCatalog.get(SRAD).size(); ++i)
        {
            // Checks if there are less than the
            // NUMBER_OF_MISSING_OBSERVATIONS to still run calculations
            if (notValid < NUMBER_OF_MISSING_OBSERVATIONS)
            {
                // Checks if the value is valid
                if (dataCatalog.get(SRAD).get(i).isValid())
                {
                    // Checks if this value is less than the current lowest
                    // stored value
                    if (dataCatalog.get(SRAD).get(i).getValue() < minValue)
                    {
                        minValue = dataCatalog.get(SRAD).get(i).getValue();
                        stidMin = dataCatalog.get(SRAD).get(i).getStid();
                    }

                    if (dataCatalog.get(SRAD).get(i).getValue() > maxValue)
                    {
                        maxValue = dataCatalog.get(SRAD).get(i).getValue();
                        stidMax = dataCatalog.get(SRAD).get(i).getStid();
                    }

                    totalValue = totalValue + dataCatalog.get(SRAD).get(i).getValue();
                }

                // If the value isn't valid then adds to a counter
                else
                {
                    notValid++;
                }
            }

            // Too many missing observations so returns data with an
            // error value of -999
            else
            {
                stidMax = "NULL";
                stidMin = "NULL";
                minValue = -999;
                maxValue = -999;
            }
        }

        numberOfValidStations = numberOfStations - notValid;
        averageValue = totalValue / numberOfValidStations;

        // Creates statistics for SRAD data
        Statistics sradMin = new Statistics(minValue, stidMin, utcDateTime, numberOfValidStations, StatsType.MINIMUM);
        Statistics sradMax = new Statistics(maxValue, stidMax, utcDateTime, numberOfValidStations, StatsType.MAXIMUM);
        Statistics sradTotal = new Statistics(totalValue, MESONET, utcDateTime, numberOfValidStations, StatsType.TOTAL);
        Statistics sradAverage = new Statistics(averageValue, MESONET, utcDateTime, numberOfValidStations,
                StatsType.AVERAGE);

        minValues.put("SRAD", sradMin);
        maxValues.put("SRAD", sradMax);
        totalValues.put("SRAD", sradTotal);
        averageValues.put("SRAD", sradAverage);

        // Starts the calculations for TAIR data
        // Resets the variables for new calculations
        notValid = 0;
        stidMin = "";
        stidMax = "";
        minValue = Integer.MAX_VALUE;
        maxValue = Integer.MIN_VALUE;
        numberOfValidStations = 0;
        averageValue = 0;
        totalValue = 0;

        // Calculates the minimum value
        for (int i = 0; i < dataCatalog.get(TAIR).size(); ++i)
        {
            // Checks if there are less than the
            // NUMBER_OF_MISSING_OBSERVATIONS to still run calculations
            if (notValid < NUMBER_OF_MISSING_OBSERVATIONS)
            {
                // Checks if the value is valid
                if (dataCatalog.get(TAIR).get(i).isValid())
                {
                    // Checks if this value is less than the current lowest
                    // stored value
                    if (dataCatalog.get(TAIR).get(i).getValue() < minValue)
                    {
                        minValue = dataCatalog.get(TAIR).get(i).getValue();
                        stidMin = dataCatalog.get(TAIR).get(i).getStid();
                    }

                    if (dataCatalog.get(TAIR).get(i).getValue() > maxValue)
                    {
                        maxValue = dataCatalog.get(TAIR).get(i).getValue();
                        stidMax = dataCatalog.get(TAIR).get(i).getStid();
                    }

                    totalValue = totalValue + dataCatalog.get(TAIR).get(i).getValue();
                }

                // If the value isn't valid then adds to a counter
                else
                {
                    notValid++;
                }
            }

            // Too many missing observations so returns data with an
            // error value of -999
            else
            {
                stidMax = "NULL";
                stidMin = "NULL";
                minValue = -999;
                maxValue = -999;
            }
        }

        numberOfValidStations = numberOfStations - notValid;
        averageValue = totalValue / numberOfValidStations;

        // Create statistics for TAIR data
        Statistics tairMin = new Statistics(minValue, stidMin, utcDateTime, numberOfValidStations, StatsType.MINIMUM);
        Statistics tairMax = new Statistics(maxValue, stidMax, utcDateTime, numberOfValidStations, StatsType.MAXIMUM);
        Statistics tairAverage = new Statistics(averageValue, MESONET, utcDateTime, numberOfValidStations,
                StatsType.AVERAGE);

        minValues.put("TAIR", tairMin);
        maxValues.put("TAIR", tairMax);
        averageValues.put("TAIR", tairAverage);

        // Starts the calculations for TA9M data
        // Resets the variables for new calculations
        notValid = 0;
        stidMin = "";
        stidMax = "";
        minValue = Integer.MAX_VALUE;
        maxValue = Integer.MIN_VALUE;
        numberOfValidStations = 0;
        averageValue = 0;
        totalValue = 0;

        // Calculates the minimum value
        for (int i = 0; i < dataCatalog.get(TA9M).size(); ++i)
        {
            // Checks if there are less than the
            // NUMBER_OF_MISSING_OBSERVATIONS to still run calculations
            if (notValid < NUMBER_OF_MISSING_OBSERVATIONS)
            {
                // Checks if the value is valid
                if (dataCatalog.get(TA9M).get(i).isValid())
                {
                    // Checks if this value is less than the current lowest
                    // stored value
                    if (dataCatalog.get(TA9M).get(i).getValue() < minValue)
                    {
                        minValue = dataCatalog.get(TA9M).get(i).getValue();
                        stidMin = dataCatalog.get(TA9M).get(i).getStid();
                    }

                    if (dataCatalog.get(TA9M).get(i).getValue() > maxValue)
                    {
                        maxValue = dataCatalog.get(TA9M).get(i).getValue();
                        stidMax = dataCatalog.get(TA9M).get(i).getStid();
                    }

                    totalValue = totalValue + dataCatalog.get(TA9M).get(i).getValue();
                }

                // If the value isn't valid then adds to a counter
                else
                {
                    notValid++;
                }
            }

            // Too many missing observations so returns data with an
            // error value of -999
            else
            {
                stidMax = "NULL";
                stidMin = "NULL";
                minValue = -999;
                maxValue = -999;
            }
        }

        numberOfValidStations = numberOfStations - notValid;
        averageValue = totalValue / numberOfValidStations;

        // Creates statistics for TA9M data
        Statistics ta9mMin = new Statistics(minValue, stidMin, utcDateTime, numberOfValidStations, StatsType.MINIMUM);
        Statistics ta9mMax = new Statistics(maxValue, stidMax, utcDateTime, numberOfValidStations, StatsType.MAXIMUM);
        Statistics ta9mAverage = new Statistics(averageValue, MESONET, utcDateTime, numberOfValidStations,
                StatsType.AVERAGE);

        minValues.put("TA9M", ta9mMin);
        maxValues.put("TA9M", ta9mMax);
        averageValues.put("TA9M", ta9mAverage);

        // Populates the statistics EnumMap
        statistics.put(StatsType.MINIMUM, minValues);
        statistics.put(StatsType.MAXIMUM, maxValues);
        statistics.put(StatsType.AVERAGE, averageValues);
        statistics.put(StatsType.TOTAL, totalValues);
    }

    /**
     * Initialized the dataCatalog HashMap
     */
    private void prepareDataCatalog()
    {
        dataCatalog = new HashMap<String, ArrayList<Observation>>();
    }

    /**
     * Calculates the statistics
     */
    private void calculateStatistics()
    {
        calculateAllStatistics();
    }

    /**
     * Gets the statistics data given the StatsType and paramId
     * 
     * @param type
     *            The StatsType
     * @param paramId
     *            The String value of the paramId
     * @return Statistics data that corresponds to the input values
     */
    public Statistics getStatistics(StatsType type, String paramId)
    {
        return statistics.get(type).get(paramId);
    }

    /**
     * The toString for the MapData class in the format:
     * ========================================================= ===
     * year-month-day hour:minute ===
     * ========================================================= Maximum Air
     * Temperature[1.5m] = (tairMax) C at (tairMaxStid) Minimum Air
     * Temperature[1.5m] = (tairMin) C at (tairMinStid) Average Air
     * Temperature[1.5m] = (tairAverage) C at (tairAverageStid)
     * =========================================================
     * ========================================================= Maximum Air
     * Temperature[9.0m] = (ta9mMax) C at (ta9mMaxStid) Minimum Air
     * Temperature[9.0m] = (ta9mMin) C at (ta9mMinStid) Average Air
     * Temperature[9.0m] = (ta9mAverage) C at (ta9mAverageStid)
     * =========================================================
     * ========================================================= Maximum Solar
     * Radiation[1.5m] = (sradMax) W/m^2 at (sradMaxStid) Minimum Solar
     * Radiation[1.5m] = (sradMin) W/m^2 at (sradMinStid) Average Solar
     * Radiation[1.5m] = (sradAverage) W/m^2 at (sradAverageStid)
     * =========================================================
     * 
     * @return myResult the final string
     */
    public String toString()
    {
        StringBuilder finalString = new StringBuilder();
        StringBuilder seperatingLine = new StringBuilder();
        String myChar = "=";

        int n = 57;

        // Creates the separating lines with 57 "="
        for (int i = 0; i < n; i++)
        {
            seperatingLine.append(myChar);
        }

        seperatingLine.append("\n");
        finalString.append(seperatingLine);
        finalString.append(String.format("=== %04d-%02d-%02d %02d:%02d ===\n", utcDateTime.get(Calendar.YEAR),
                utcDateTime.get(Calendar.MONTH), utcDateTime.get(Calendar.DAY_OF_MONTH),
                utcDateTime.get(Calendar.HOUR_OF_DAY), utcDateTime.get(Calendar.MINUTE)));
        finalString.append(seperatingLine);
        finalString.append(String.format("Maximum Air Temperature[1.5m] = %.1f C at %s\n",
                getStatistics(StatsType.MAXIMUM, TAIR).getValue(), getStatistics(StatsType.MAXIMUM, TAIR).getStid()));
        finalString.append(String.format("Minimum Air Temperature[1.5m] = %.1f C at %s\n",
                getStatistics(StatsType.MINIMUM, TAIR).getValue(), getStatistics(StatsType.MINIMUM, TAIR).getStid()));
        finalString.append(String.format("Average Air Temperature[1.5m] = %.1f C at %s\n",
                getStatistics(StatsType.AVERAGE, TAIR).getValue(), getStatistics(StatsType.AVERAGE, TAIR).getStid()));
        finalString.append(seperatingLine);
        finalString.append(seperatingLine);
        finalString.append(String.format("Maximum Air Temperature[9.0m] = %.1f C at %s\n",
                getStatistics(StatsType.MAXIMUM, TA9M).getValue(), getStatistics(StatsType.MAXIMUM, TA9M).getStid()));
        finalString.append(String.format("Minimum Air Temperature[9.0m] = %.1f C at %s\n",
                getStatistics(StatsType.MINIMUM, TA9M).getValue(), getStatistics(StatsType.MINIMUM, TA9M).getStid()));
        finalString.append(String.format("Average Air Temperature[9.0m] = %.1f C at %s\n",
                getStatistics(StatsType.AVERAGE, TA9M).getValue(), getStatistics(StatsType.AVERAGE, TA9M).getStid()));
        finalString.append(seperatingLine);
        finalString.append(seperatingLine);
        finalString.append(String.format("Maximum Solar Radiation[1.5m] = %.1f W/m^2 at %s\n",
                getStatistics(StatsType.MAXIMUM, SRAD).getValue(), getStatistics(StatsType.MAXIMUM, SRAD).getStid()));
        finalString.append(String.format("Minimum Solar Radiation[1.5m] = %.1f W/m^2 at %s\n",
                getStatistics(StatsType.MINIMUM, SRAD).getValue(), getStatistics(StatsType.MINIMUM, SRAD).getStid()));
        finalString.append(String.format("Average Solar Radiation[1.5m] = %.1f W/m^2 at %s\n",
                getStatistics(StatsType.AVERAGE, SRAD).getValue(), getStatistics(StatsType.AVERAGE, SRAD).getStid()));
        finalString.append(seperatingLine);

        String myResult = finalString.toString();

        return myResult;
    }

}
