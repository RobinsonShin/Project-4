import java.awt.GridLayout;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/**
 * The ParameterPanel
 * 
 * @author Robinson Shin
 * @version 2018-12-4
 */
public class ParameterPanel extends JPanel
{
    JLabel label = new JLabel("Parameter");

    // MINIMUM check box
    JCheckBox MINIMUM = new JCheckBox("MINIMUM");

    // MAXIMUM check box
    JCheckBox MAXIMUM = new JCheckBox("MAXIMUM");

    // AVERAGE check box
    JCheckBox AVERAGE = new JCheckBox("AVERAGE");

    // JPanel for the ParameterFrame
    JPanel panel1 = new JPanel(new GridLayout(4, 0));

    public ParameterPanel()
    {
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