import java.awt.GridLayout;
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
    // Text that describes this panel
    JLabel label = new JLabel("Parameter");

    // TAIR radio button
    JRadioButton TAIR = new JRadioButton("TAIR");

    // TA9M radio button
    JRadioButton TA9M = new JRadioButton("TA9M");

    // SRAD radio button
    JRadioButton SRAD = new JRadioButton("SRAD");

    // WSPD radio button
    JRadioButton WSPD = new JRadioButton("WSPD");

    // PRES radio button
    JRadioButton PRES = new JRadioButton("PRES");

    // JPanel for the ParameterFrame
    JPanel panel1 = new JPanel(new GridLayout(6, 0));

    public ParameterPanel()
    {        
        // Adds the buttons to the panel
        panel1.add(label);
        panel1.add(TAIR);
        panel1.add(TA9M);
        panel1.add(SRAD);
        panel1.add(WSPD);
        panel1.add(PRES);    
    }
    
    public JPanel getPanel()
    {
        return this.panel1;
    }
}
