/**
 * ImagePanel - A panel that contains an SImage (picture) and buttons to load
 *  and save said SImage.
 *  
 *  @author David Levine
 *  @version May 28, 2009
 */

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import squint.SImage;


public class ImagePanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private static final int MAX_IMAGE_WIDTH = 400;
    private static final int MAX_IMAGE_HEIGHT = 500;
    private static JFileChooser jfc = new JFileChooser();
    private  JLabel image;
    
    /**
     * Build the panel (image with two buttons below it)
     */
    public ImagePanel() {
        super();
        image = new JLabel();
        JScrollPane jsp = new JScrollPane(image);
        JPanel buttons = new JPanel(new FlowLayout());
        
        JButton load = new JButton("Load Image");
        load.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent ae) {
                ImagePanel.this.handleOpen(); 
            }});
        buttons.add(load);
            
        JButton save = new JButton("Save Image");
        save.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent ae) {
                ImagePanel.this.handleSave(); 
            }});
        buttons.add(save);
        
        this.setLayout(new BorderLayout());
        this.add(jsp, BorderLayout.CENTER);
        this.add(buttons, BorderLayout.SOUTH);
    }
    
    public void setImage(Icon img) {
        image.setIcon(img);
        revalidate();
        repaint();
    }
    
    public SImage getImage() {
        return (SImage) image.getIcon();
    }
    
    public Dimension getPreferredSize() {
        SImage img = getImage();
        if (img == null) {
             return new Dimension(300,300);
        } else {
           int width = Math.min(img.getWidth()+20, MAX_IMAGE_WIDTH);
           int height = Math.min(img.getHeight()+80, MAX_IMAGE_HEIGHT);
           return new Dimension(width,height);
       }
    }
    
    /**
     * Open (and display) an image in this panel
     */
    public void handleOpen() {
        int returnVal = jfc.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            String fname = jfc.getSelectedFile().getAbsolutePath();
            SImage si = new SImage(fname);
            setImage(si);
         }
    }
    
    /**
     * Save the image in this panel to a file (in .png format)
     */
    public  void handleSave() {
        int returnVal = jfc.showSaveDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            String fname = jfc.getSelectedFile().getAbsolutePath(); 
            if (! fname.endsWith(".png"))
                fname += ".png";
            this.getImage().saveAs(fname);
        }
    }
}
