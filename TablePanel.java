import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * The TablePanel to create the data table and puts it in the panel.
 * 
 * @author Robinson Shin
 * @version 2018-12-5
 */
public class TablePanel extends JPanel
{
    // JPanel for the Data
    JPanel data = new JPanel();
    
    // The array that holds all the data information
    String[][] dataData; 
    
    // Table for the panel
    JTable table;
    
    // TableModel to use
    DefaultTableModel tableModel;
    
    /**
     * The constructor for the TablePanel class
     */
    public TablePanel()
    {     
        // Sets the layout
        setLayout(new BorderLayout());
       
        // Array to hold the column names
        String[] columnNames = new String[6];
        
        // Adds the column names into the string array
        columnNames[0] = "Station";
        columnNames[1] = "Parameter";
        columnNames[2] = "Statistics";
        columnNames[3] = "Value";
        columnNames[4] = "Reporting Stations";
        columnNames[5] = "Date";
        
        // Creates the tableModel with data and column names
        tableModel = new DefaultTableModel(dataData,columnNames);
        
        // Creates the JTable using the tableModel
        table = new JTable(tableModel);
        
        // Gets rid of the gridlines
        table.setShowGrid(false);
        
        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);
        
        // Sets the table sizes
        table.getColumnModel().getColumn(0).setPreferredWidth(5);
        table.getColumnModel().getColumn(1).setPreferredWidth(5);
        table.getColumnModel().getColumn(2).setPreferredWidth(5);
        table.getColumnModel().getColumn(3).setPreferredWidth(5);
        table.getColumnModel().getColumn(4).setPreferredWidth(40);
        table.getColumnModel().getColumn(5).setPreferredWidth(100);
        table.setPreferredScrollableViewportSize(new Dimension(600,300));
        
        data.add(scrollPane);
        
        // Sets the panel to the center
        add(data, BorderLayout.CENTER);
    }
    
    /**
     * Obtains data from the input MapData and creates a String array with the data
     * 
     * @param data the MapData that is read in
     * @param stats The StatsType
     * @param paramId The String value of the paramId
     * @return a string array with all the data
     */
    public String[] retrieveData(MapData data, StatsType stats, String paramId)
    {
        String station = "";
        String parameter = "";
        String statistics = "";
        String value = "";
        String reportingStation = "";
        String date = "";
        
        // Convert values to string
        station = data.getStatistics(stats, paramId).getStid();
        parameter = paramId;
        statistics = stats.toString();
        value = Double.toString((double)Math.round(data.getStatistics(stats, paramId).getValue() * 10d) / 10d);
        reportingStation = Integer.toString(data.getStatistics(stats, paramId).getNumberOfReportingStations());
        date = data.getStatistics(stats, paramId).getUTCDateTimeString();
        
        // Creates a string array with the given parameters
        String[] output = {station, parameter, statistics, value, reportingStation, date};
        
        // Adds the data to the table
        tableModel.addRow(output);
        
        return output;
    }
}
