import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

/**
 * 
 * @author Robinson Shin
 * 
 */

public class MesonetFrame extends JFrame
{
    // JPanel for the frame
    JPanel panel0 = new JPanel();
    
    // JLabels for the frame
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
        add(panel0, BorderLayout.NORTH);
        
        // Configuring of the frame
        setVisible(true);
        setResizable(false);
        setSize(600,400);
    }
    
    public static void main(String[] args) 
    {
        new MesonetFrame("Oklahoma Mesonet - Statistics Calculator");
    }
}