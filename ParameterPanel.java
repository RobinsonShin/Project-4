import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/**
 * The ParameterPanel to create the buttons and adds to the panel.
 * 
 * @author Robinson Shin
 * @version 2018-12-5
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
    JPanel parameterPanel = new JPanel(new GridLayout(6, 0));
    
    /**
     * The constructor for the ParameterPanel class
     */
    public ParameterPanel()
    {        
        // Adds the buttons to the panel
        parameterPanel.add(label);
        parameterPanel.add(TAIR);
        parameterPanel.add(TA9M);
        parameterPanel.add(SRAD);
        parameterPanel.add(WSPD);
        parameterPanel.add(PRES);    
    }
}
