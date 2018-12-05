import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class TablePanel extends JPanel
{
    // JPanel for the Data
    JPanel data = new JPanel();
    
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
        
        Object[][] dataData = {{"","","","","",""}, {"","","","","",""}}; 
        
        JTable table = new JTable(dataData,columnNames);
        
        table.setShowGrid(true);
        
        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);
        
        data.add(scrollPane);
        
        add(data, BorderLayout.CENTER);
    }
    
    public JPanel getDataPanel()
    {
        return this.data;
    }
}
