import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * The StatisticsPanel 
 * 
 * @author Robinson Shin
 * @version 2018-12-4
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

    // JPanel for the ParameterFrame
    JPanel panel1 = new JPanel(new GridLayout(4, 0));

    public StatisticsPanel()
    {
        // Adds the check boxes to the button group
        group.add(MINIMUM);
        group.add(MAXIMUM);
        group.add(AVERAGE);

        // Adds the check boxes to the panel
        panel1.add(label);
        panel1.add(MINIMUM);
        panel1.add(MAXIMUM);
        panel1.add(AVERAGE);
    }
    
    public JPanel getPanel()
    {
        return this.panel1;
    }
}
