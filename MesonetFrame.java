import java.awt.BorderLayout;
import java.awt.Insets;
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
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * 
 * @author Robinson Shin
 * 
 */

public class MesonetFrame extends JFrame implements ActionListener
{
    /** Use default UID */
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

    JFileChooser fc;
    
    MapData mapData;
    String paramId = "";
    

    class FileMenuBar extends JMenuBar
    {
        JMenuItem openDataFile = new JMenuItem("Open Data File");
        JMenuItem exit = new JMenuItem("Exit");
        JMenu fileMenu = new JMenu("File");

        public FileMenuBar()
        {
            fileMenu.add(openDataFile);
            fileMenu.add(exit);
            add(fileMenu);
        }

        public JMenuItem getOpenDataFile()
        {
            return this.openDataFile;
        }

        public JMenuItem getExit()
        {
            return this.exit;
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

        // Add the panels into the frame.
        panel0.add(northLabel);

        panel3.add(calculate);

        panel3.add(exit);

        add(panel0, BorderLayout.NORTH);

        add(panel1.getPanel(), BorderLayout.EAST);

        add(panel2.getPanel(), BorderLayout.WEST);

        add(panel3, BorderLayout.SOUTH);

        add(panel4.getDataPanel(), BorderLayout.CENTER);
        
        // Configuring of the frame
        setVisible(true);
        setResizable(true);
        setSize(800, 400);

        fc = new JFileChooser();

        menuBar.getOpenDataFile().addActionListener(this);
        menuBar.getExit().addActionListener(this);
        exit.addActionListener(this);
        calculate.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e)
    {
        fc.setCurrentDirectory(new File  ("C:\\Users\\Robinson Shin\\Desktop\\School\\Fall 2018\\CS\\Project 4\\data"));
        
        // Handle open button action.
        if (e.getSource() == menuBar.getOpenDataFile())
        {
            int returnVal = fc.showOpenDialog(MesonetFrame.this);

            String filename = "";

            int YEAR = 0;

            int MONTH = 0;

            int DAY = 0;

            int HOUR = 0;

            int MINUTE = 0;

            String directory = "";
            
            if (returnVal == JFileChooser.APPROVE_OPTION)
            {
                String file = fc.getSelectedFile().getName();
                directory = fc.getCurrentDirectory().getName();
                
                filename = directory + "/" + file;
                
                String[] letters = file.split("");
                
                String numbers = letters[0] + letters[1] + letters[2] + letters[3];
                YEAR = Integer.parseInt(numbers);
                
                numbers = letters[4] + letters[5];
                MONTH = Integer.parseInt(numbers);
                
                numbers = letters[6] + letters[7];
                DAY = Integer.parseInt(numbers);
                
                numbers = letters[8] + letters[9];
                HOUR = Integer.parseInt(numbers);
                
                numbers = letters[10] + letters[11];
                MINUTE = Integer.parseInt(numbers);

                mapData = new MapData(YEAR, MONTH, DAY, HOUR, MINUTE, directory);

                try
                {
                    mapData.parseFile();
                }
                catch (IOException e1)
                {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        }

        // Handle exit menu button action.
        else if (e.getSource() == menuBar.getExit())
        {
            System.exit(0);
        }

        else if (e.getSource() == exit)
        {
            System.exit(0);
        }
        
        else if (e.getSource() == calculate)
        {
            panel4.tableModel.setRowCount(0); 
            
            if (panel1.AVERAGE.isSelected())
            {
                if (panel2.TAIR.isSelected())
                {
                panel4.retrieveData(mapData, StatsType.AVERAGE, "TAIR");
                }
                
                if (panel2.TA9M.isSelected())
                {
                    panel4.retrieveData(mapData, StatsType.AVERAGE, "TA9M");
                }
                
                if (panel2.SRAD.isSelected())
                {
                    panel4.retrieveData(mapData, StatsType.AVERAGE, "SRAD");
                }
                
                if (panel2.WSPD.isSelected())
                {
                   panel4.retrieveData(mapData, StatsType.AVERAGE, "WSPD");
                }
                
                if (panel2.PRES.isSelected())
                {
                    panel4.retrieveData(mapData, StatsType.AVERAGE, "PRES");
                }
            }

            else if (panel1.MAXIMUM.isSelected())
            {
                if (panel2.TAIR.isSelected())
                {
                    panel4.retrieveData(mapData, StatsType.MAXIMUM, "TAIR");
                }
                
                if (panel2.TA9M.isSelected())
                {
                    panel4.retrieveData(mapData, StatsType.MAXIMUM, "TA9M");
                }
                
                if (panel2.SRAD.isSelected())
                {
                    panel4.retrieveData(mapData, StatsType.MAXIMUM, "SRAD");
                }
                
                if (panel2.WSPD.isSelected())
                {
                    panel4.retrieveData(mapData, StatsType.MAXIMUM, "WSPD");
                }
                
                if (panel2.PRES.isSelected())
                {
                    panel4.retrieveData(mapData, StatsType.MAXIMUM, "PRES");
                }
            }
            
            else if (panel1.MINIMUM.isSelected())
            {
                if (panel2.TAIR.isSelected())
                {
                    panel4.retrieveData(mapData, StatsType.MINIMUM, "TAIR");
                }
                
                if (panel2.TA9M.isSelected())
                {
                    panel4.retrieveData(mapData, StatsType.MINIMUM, "TA9M");
                }
                
                if (panel2.SRAD.isSelected())
                {
                    panel4.retrieveData(mapData, StatsType.MINIMUM, "SRAD");
                }
                
                if (panel2.WSPD.isSelected())
                {
                    panel4.retrieveData(mapData, StatsType.MINIMUM, "WSPD");
                }
                
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