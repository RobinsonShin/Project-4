import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * 
 * @author Robinson Shin
 *
 */
public class TablePanel extends JPanel
{
    // JPanel for the Data
    JPanel data = new JPanel();
    
    String[][] dataData; 
    
    JTable table;
    
    DefaultTableModel tableModel;
    
    public TablePanel()
    {     
        setLayout(new BorderLayout());
       
        String[] columnNames = new String[6];
        columnNames[0] = "Station";
        columnNames[1] = "Parameter";
        columnNames[2] = "Statistics";
        columnNames[3] = "Value";
        columnNames[4] = "Reporting Stations";
        columnNames[5] = "Date";
        
        tableModel = new DefaultTableModel(dataData,columnNames);
        
        table = new JTable(tableModel);
        
        table.setShowGrid(false);
        
        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);
        
        table.getColumnModel().getColumn(0).setPreferredWidth(20);
        table.getColumnModel().getColumn(1).setPreferredWidth(20);
        table.getColumnModel().getColumn(2).setPreferredWidth(20);
        table.getColumnModel().getColumn(3).setPreferredWidth(20);
        table.getColumnModel().getColumn(4).setPreferredWidth(40);
        table.getColumnModel().getColumn(5).setPreferredWidth(50);
        table.setPreferredScrollableViewportSize(new Dimension(600,300));
        
        data.add(scrollPane);
        
        add(data, BorderLayout.CENTER);
    }
    
    public JPanel getDataPanel()
    {
        return this.data;
    }
    
    public String[] retrieveData(MapData data, StatsType stats, String paramId)
    {
        String station = "";
        String parameter = "";
        String statistics = "";
        String value = "";
        String reportingStation = "";
        String date = "";
        
        station = data.getStatistics(stats, paramId).getStid();
        parameter = paramId;
        statistics = stats.toString();
        value = Double.toString(data.getStatistics(stats, paramId).getValue());
        reportingStation = Integer.toString(data.getStatistics(stats, paramId).getNumberOfReportingStations());
        date = data.getStatistics(stats, paramId).getUTCDateTimeString();
        
        String[] output = {station, parameter, statistics, value, reportingStation, date};
        tableModel.addRow(output);
        return output;
    }
}
