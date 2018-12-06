import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

/**
 * Main frame class that creates the GUI
 * 
 * @author Robinson Shin
 * @version 2018-12-5
 */

public class MesonetFrame extends JFrame implements ActionListener
{
    // Use default UID
    private static final long serialVersionUID = 1L;

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

    // MenuBar for the frame
    FileMenuBar menuBar = new FileMenuBar();

    // Text for the north section of the frame
    JLabel northLabel = new JLabel("Mesonet - We don't set records, we report them!");

    // The fileChooser for the menuBar
    JFileChooser fc;
    
    // MapData of the given file
    MapData mapData;

    /**
     * An inner class of MesonetFrame that creates the FileMenuBar
     */
    class FileMenuBar extends JMenuBar
    {
        JMenuItem openDataFile = new JMenuItem("Open Data File");
        JMenuItem exit = new JMenuItem("Exit");
        JMenu fileMenu = new JMenu("File");

        /**
         * The constructor for the FileMenuBar inner class
         */
        public FileMenuBar()
        {
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
        setJMenuBar(menuBar);

        // Add the panels to the panel
        panel0.add(northLabel);
        
        // Adds the calculate button to the panel
        panel3.add(calculate);
        
        // Adds the main exit button to the panel
        panel3.add(exit);

        // Adds the panels to the frame
        add(panel0, BorderLayout.NORTH);
        add(panel1.statisticsPanel, BorderLayout.EAST);
        add(panel2.parameterPanel, BorderLayout.WEST);
        add(panel3, BorderLayout.SOUTH);
        add(panel4.data, BorderLayout.CENTER);
        
        // Configuring of the frame
        setVisible(true);
        setResizable(true);
        setSize(800, 400);

        // Initializes the file chooser
        fc = new JFileChooser();

        // The actionListeners
        menuBar.openDataFile.addActionListener(this);
        menuBar.exit.addActionListener(this);
        exit.addActionListener(this);
        calculate.addActionListener(this);
    }

    /**
     * Runs an action given a specific input
     */
    public void actionPerformed(ActionEvent e)
    {
        fc.setCurrentDirectory(new File  ("C:\\Users\\Robinson Shin\\Desktop\\School\\Fall 2018\\CS\\Project 4\\data"));
        
        // Handle open button action
        if (e.getSource() == menuBar.openDataFile)
        {
            int returnVal = fc.showOpenDialog(MesonetFrame.this);
            String filename = "";
            int YEAR = 0;
            int MONTH = 0;
            int DAY = 0;
            int HOUR = 0;
            int MINUTE = 0;
            String directory = "";
            
            // If the button is pressed
            if (returnVal == JFileChooser.APPROVE_OPTION)
            {
                String file = fc.getSelectedFile().getName();
                directory = fc.getCurrentDirectory().getName();
                
                filename = directory + "/" + file;
                
                String[] letters = file.split("");
                
                // Creates the year
                String numbers = letters[0] + letters[1] + letters[2] + letters[3];
                YEAR = Integer.parseInt(numbers);
                
                // Creates the month
                numbers = letters[4] + letters[5];
                MONTH = Integer.parseInt(numbers);
                
                // Creates the day
                numbers = letters[6] + letters[7];
                DAY = Integer.parseInt(numbers);
                
                //Creates the hour
                numbers = letters[8] + letters[9];
                HOUR = Integer.parseInt(numbers);
                
                // Creates the minute
                numbers = letters[10] + letters[11];
                MINUTE = Integer.parseInt(numbers);

                mapData = new MapData(YEAR, MONTH, DAY, HOUR, MINUTE, directory);

                try
                {
                    mapData.parseFile();
                }
                catch (IOException e1)
                {
                    e1.printStackTrace();
                }
            }
        }

        // Handle exit menu button action.
        else if (e.getSource() == menuBar.exit)
        {
            System.exit(0);
        }

        // Handles the main exit button action
        else if (e.getSource() == exit)
        {
            System.exit(0);
        }
        
        // Handles the calculate button
        else if (e.getSource() == calculate)
        {
            panel4.tableModel.setRowCount(0); 
            
            // Checks if AVERAGE button is selected
            if (panel1.AVERAGE.isSelected())
            {
                // Checks if TAIR button is selected
                if (panel2.TAIR.isSelected())
                {
                panel4.retrieveData(mapData, StatsType.AVERAGE, "TAIR");
                }
                
                // Checks if TA9M button is selected
                if (panel2.TA9M.isSelected())
                {
                    panel4.retrieveData(mapData, StatsType.AVERAGE, "TA9M");
                }
                
                // Checks if SRAD button is selected
                if (panel2.SRAD.isSelected())
                {
                    panel4.retrieveData(mapData, StatsType.AVERAGE, "SRAD");
                }
                
                // Checks if WSPD button is selected
                if (panel2.WSPD.isSelected())
                {
                   panel4.retrieveData(mapData, StatsType.AVERAGE, "WSPD");
                }
                
                // Checks if PRES button is selected
                if (panel2.PRES.isSelected())
                {
                    panel4.retrieveData(mapData, StatsType.AVERAGE, "PRES");
                }
            }

            // Checks if MAXIMUM button is selected
            else if (panel1.MAXIMUM.isSelected())
            {
                // Checks if TAIR button is selected
                if (panel2.TAIR.isSelected())
                {
                    panel4.retrieveData(mapData, StatsType.MAXIMUM, "TAIR");
                }
               
                // Checks if TA9M button is selected
                if (panel2.TA9M.isSelected())
                {
                    panel4.retrieveData(mapData, StatsType.MAXIMUM, "TA9M");
                }
  
                // Checks if SRAD button is selected
                if (panel2.SRAD.isSelected())
                {
                    panel4.retrieveData(mapData, StatsType.MAXIMUM, "SRAD");
                }
  
                // Checks if WSPD button is selected
                if (panel2.WSPD.isSelected())
                {
                    panel4.retrieveData(mapData, StatsType.MAXIMUM, "WSPD");
                }
                
                // Checks if PRES button is selected
                if (panel2.PRES.isSelected())
                {
                    panel4.retrieveData(mapData, StatsType.MAXIMUM, "PRES");
                }
            }
            
            // Checks if MINIMUM button is selected
            else if (panel1.MINIMUM.isSelected())
            {
                // Checks if TAIR button is selected
                if (panel2.TAIR.isSelected())
                {
                    panel4.retrieveData(mapData, StatsType.MINIMUM, "TAIR");
                }
                
                // Checks if TA9M button is selected
                if (panel2.TA9M.isSelected())
                {
                    panel4.retrieveData(mapData, StatsType.MINIMUM, "TA9M");
                }
                
                // Checks if SRAD button is selected
                if (panel2.SRAD.isSelected())
                {
                    panel4.retrieveData(mapData, StatsType.MINIMUM, "SRAD");
                }
                
                // Checks if WSPD button is selected
                if (panel2.WSPD.isSelected())
                {
                    panel4.retrieveData(mapData, StatsType.MINIMUM, "WSPD");
                }
                
                // Checks if PRES button is selected
                if (panel2.PRES.isSelected())
                {
                    panel4.retrieveData(mapData, StatsType.MINIMUM, "PRES");
                }
            }
        }
    }

    public static void main(String[] args)
    {
        new MesonetFrame("Oklahoma Mesonet - Statistics Calculator");
    }
}