import java.awt.GridLayout;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * The StatisticsPanel to create the buttons and to the panel.
 * 
 * @author Robinson Shin
 * @version 2018-12-5
 */

public class StatisticsPanel extends JPanel
{
    // Text that describes this panel
    JLabel label = new JLabel("Statistics");

    // Button group for the statistics buttons
    ButtonGroup group = new ButtonGroup();
    
    // MINIMUM check box
    JCheckBox MINIMUM = new JCheckBox("MINIMUM");

    // MAXIMUM check box
    JCheckBox MAXIMUM = new JCheckBox("MAXIMUM");

    // AVERAGE check box
    JCheckBox AVERAGE = new JCheckBox("AVERAGE");

    // JPanel for the StatisticsFrame
    JPanel statisticsPanel = new JPanel(new GridLayout(4, 0));

    /**
     * Constructor for the StatisticsPanel class
     */
    public StatisticsPanel()
    {
        // Adds the check boxes to the button group
        group.add(MINIMUM);
        group.add(MAXIMUM);
        group.add(AVERAGE);

        // Adds the check boxes to the panel
        statisticsPanel.add(label);
        statisticsPanel.add(MINIMUM);
        statisticsPanel.add(MAXIMUM);
        statisticsPanel.add(AVERAGE);

        // Selects the MAXIMUM button as the default
        MAXIMUM.setSelected(true);
        
    }
}
