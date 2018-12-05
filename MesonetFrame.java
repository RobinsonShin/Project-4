import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/**
 * 
 * @author Robinson Shin
 * 
 */

public class MesonetFrame extends JFrame
{
    // Panel for the text at the top of frame
    JPanel panel0 = new JPanel();
    
    // StatisticsPanel for the frame
    StatisticsPanel panel1 = new StatisticsPanel();
    
    // ParameterPanel for the frame
    ParameterPanel panel2 = new ParameterPanel();
    
    // Panel for the calculate and exit button
    JPanel panel3 = new JPanel();
    
    // Panel for the 
    TablePanel panel4 = new TablePanel();
    
    // Calculate button
    JButton calculate = new JButton("Calculate");
    
    // Exit button
    JButton exit = new JButton("Exit");
    
    // Text for the north section of the frame
    JLabel northLabel = new JLabel("Mesonet - We don't set records, we report them!");
       
    class FileMenuBar extends JMenuBar
    {
        public FileMenuBar()
        {
            JMenuItem openDataFile = new JMenuItem("Open Data File");
            JMenuItem exit = new JMenuItem("Exit");
            JMenu fileMenu = new JMenu("File");
            fileMenu.add(openDataFile);
            fileMenu.add(exit);
            add(fileMenu);
        }   
    }
    
    /**
     * 
     * @param title
     */
    public MesonetFrame(String title) 
    {
        super(title);
        
        // Sets the menuBar
        FileMenuBar menuBar = new FileMenuBar();
        setJMenuBar(menuBar);
        
        // Add the panels into the frame.
        panel0.add(northLabel);
        
        panel3.add(calculate);
        
        panel3.add(exit);
        
        add(panel0, BorderLayout.NORTH);
        
        add(panel1.getPanel(), BorderLayout.WEST);
        
        add(panel2.getPanel(), BorderLayout.EAST);
        
        add(panel3, BorderLayout.SOUTH);
        
        add(panel4.getDataPanel(), BorderLayout.CENTER);
        
        // Configuring of the frame
        setVisible(true);
        setResizable(true);
        setSize(800,400);
    }
    
    public static void main(String[] args) 
    {
        new MesonetFrame("Oklahoma Mesonet - Statistics Calculator");
    }
}