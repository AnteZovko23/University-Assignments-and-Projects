/**
 * ActionPanel - a GUI widget that contains a set of buttons that act upon images.
 *     The panel a column of buttons along with two ImagePanels thought to be "left" and "right"
 *     Each button will take the image stored in the left panel, transform it, and
 *     then draw the new image in the right panel.
 *     
 * @author David Levine
 * @version May 28, 2009
 * 
 */


import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import squint.SImage;
import CS132Images.ImageTransformer;

public class ActionPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private GroupLayout.ParallelGroup mainHorizontalGroup;
    private GroupLayout.SequentialGroup mainVerticalGroup;
    private final ImagePanel leftImage;
    private final ImagePanel rightImage;
   
    /**
     * Create a new ActionPanel with all of the appropriate layout features
     * to allow for a single column of buttons
     * 
     * @param leftImage
     * @param rightImage
     */
    public ActionPanel(ImagePanel leftImage, ImagePanel rightImage) {
        
        this.leftImage = leftImage;
        this.rightImage = rightImage;
        
        GroupLayout actionsLayout = new GroupLayout(this);
        actionsLayout.setAutoCreateGaps(true);
        actionsLayout.setAutoCreateContainerGaps(true);
        
        mainHorizontalGroup = actionsLayout.createParallelGroup(GroupLayout.Alignment.CENTER);
        actionsLayout.setHorizontalGroup(mainHorizontalGroup);
        mainVerticalGroup = actionsLayout.createSequentialGroup();
        actionsLayout.setVerticalGroup(mainVerticalGroup);
        
        this.setLayout(actionsLayout);
        this.setPreferredSize(new Dimension(190, 350));
    }
    
    /**
     * Create a button for a given action and add that button to the panel
     * 
     * @param transformer   the class with the action to perform when the button is clicked
     * @param buttonLabel   
     */
    public void addAction(final ImageTransformer transformer, String buttonLabel) {
        JButton button = new JButton(buttonLabel);
        button.addActionListener(new ActionListener()
        
        {
            public void actionPerformed(ActionEvent ae) {
                SImage left = leftImage.getImage();
                SImage right = transformer.transform(left);
                rightImage.setImage(right);
            }
        });
        mainHorizontalGroup.addComponent(button);
        mainVerticalGroup.addComponent(button);
    }

	
}
