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
    JTextArea log;
    static private final String newline = "\n";

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
    }

    public void actionPerformed(ActionEvent e)
    {
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
                // This is where a real application would open the file.
                filename = directory + "/" + file;
                System.out.println(filename);
                YEAR = (file.charAt(0) + file.charAt(1) + file.charAt(2) + file.charAt(3));
                System.out.println(YEAR);
                MONTH = (file.charAt(4) + file.charAt(5));
                DAY = (file.charAt(6) + file.charAt(7));
                HOUR = (file.charAt(8) + file.charAt(9));
                MINUTE = (file.charAt(10) + file.charAt(11));

                MapData mapData = new MapData(YEAR, MONTH, DAY, HOUR, MINUTE, directory);

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
            else
            {
                log.append("Open command cancelled by user." + newline);
            }
        }

        // Handle exit menu button action.
        else if (e.getSource() == menuBar.getExit())
        {
            int returnVal = fc.APPROVE_OPTION;
            if (returnVal == JFileChooser.APPROVE_OPTION)
            {
                System.exit(0);
            }
        }

        else if (e.getSource() == exit)
        {
            int returnVal = fc.APPROVE_OPTION;
            if (returnVal == JFileChooser.APPROVE_OPTION)
            {
                System.exit(0);
            }
        }
    }

    public static void main(String[] args)
    {
        new MesonetFrame("Oklahoma Mesonet - Statistics Calculator");
    }
}