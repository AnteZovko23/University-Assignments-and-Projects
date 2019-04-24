import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import CS132Images.*;

/**
 * Creates a Graphical User Interface that supports monadic image transformations, i.e. 
 * transformations that take one image as input and produce one as output, such as a rotation,
 * a reflection, or a blurring.
 * 
 * @author David Levine
 * @version May 28, 2009
 * 
 */
public class ImageTestBed extends JFrame {

    private static final long serialVersionUID = 1L;
    private static JFileChooser jfc;
    private static ImagePanel leftImage;
    private static ImagePanel rightImage;

    public ImageTestBed() {
        super("Fun with images");
        
        jfc = new JFileChooser(".");
        jfc.setFileFilter(new ImageFilter());
        
        leftImage = new ImagePanel();
        leftImage.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.red),
                "Source Image"));
 
        rightImage = new ImagePanel();
        rightImage.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.red),
                "Transformed Image"));
        
       JPanel buttons = createButtons();
       setSize(new Dimension(2000,400));  
       setLayout(new FlowLayout());  
       this.add(leftImage);
       this.add(buttons);
       this.add(rightImage);
       this.setVisible(true);
    }

    private JPanel createButtons() {
        JPanel buttons = new JPanel();
             
        ActionPanel actions = new ActionPanel(leftImage, rightImage);
    
        actions.addAction(new ImageClearBits(1), "Clear 1 bit");
        actions.addAction(new ImageClearBits(2), "Clear 2 bits");
        actions.addAction(new ImageClearBits(3), "Clear 3 bits");
        actions.addAction(new ImageClearBits(4), "Clear 4 bits");
        actions.addAction(new ImageClearBits(5), "Clear 5 bits");
        actions.addAction(new ImageClearBits(6), "Clear 6 bits");
        actions.addAction(new ImageClearBits(7), "Clear 7 bits");
        actions.addAction(new ImageClearBits(8), "Clear 8 bits");
        actions.addAction(new ImageStegExtractor(4), "Extract 4 bits");
        actions.addAction(new ImageStegExtractor(3), "Extract 3 bits");
        
        
            actions.addAction(new ImageVerticalInverter(), "Flip top/bottom");
        actions.addAction(new ImageRotateClockwise(), "Rotate clockwise");
        actions.addAction(new ImagePixelate(), "Pixelate");
        actions.addAction(new ImageNegator(), "Negate");
        actions.addAction(new ImageBlurrer(), "Blur");
        actions.addAction(new ImageBlurrer(3), "Blur Less");
        actions.addAction(new ImageToEightColor(), "Eight Color");
        actions.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.red), "Transformations"));
         
        JButton copyLeft = new JButton("" + ((char) 8592) + " Copy");
        copyLeft.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                leftImage.setImage(rightImage.getImage());
            }
        });
        
        buttons.setLayout( new BorderLayout());
        buttons.add(actions, BorderLayout.NORTH);
        buttons.add(copyLeft, BorderLayout.SOUTH);
        return buttons;
     }

 
    
    public static void main(String[] args) {
        createAndShowGUI();
    }


    private static void createAndShowGUI() {
        ImageTestBed guiWindow = new ImageTestBed();
        guiWindow.setPreferredSize(new Dimension(650, 450));
        guiWindow.setJMenuBar(createMenuBar());
        guiWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        guiWindow.setLocationRelativeTo(null);
        guiWindow.pack();
        guiWindow.setVisible(true);
    }

    
    
 //   *********  CREATE THE MENU  **************   
    
    private static JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar(); 
        buildFileMenu(menuBar);
        buildHelpMenu(menuBar);
        return menuBar;
    }

    
    private static void buildFileMenu(JMenuBar menuBar) {
        JMenu file = new JMenu("File");
        file.setMnemonic(KeyEvent.VK_F);
        menuBar.add(file);
       
        addMenuItem(file, "Open (Left)", KeyEvent.VK_O, new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                leftImage.handleOpen();
            }
        });
        
        addMenuItem(file, "Save (Right)", KeyEvent.VK_S, new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                rightImage.handleSave();
            }
        });
        
        addMenuItem(file, "Quit", KeyEvent.VK_Q, new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                System.exit(0);
            }
        });
        
     }

    
    private static void buildHelpMenu(JMenuBar menuBar) {
        JMenu help = new JMenu("Help");
        help.setMnemonic(KeyEvent.VK_H);
        menuBar.add(help);
        addMenuItem(help, "Help", KeyEvent.VK_H, new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                JOptionPane.showMessageDialog(null,"No help available");
             }
        });
        addMenuItem(help, "About", KeyEvent.VK_A, new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                ImageIcon icon = new ImageIcon("images/mypic.jpg");
                JOptionPane.showMessageDialog(null,
                        "ImageTestBed\nBy: David Levine\nMay 27, 2009",
                        "About ImageTestBed", 0, icon);
             }
        });
        
        
    }
 
    
    private static void addMenuItem(JMenu menu, String menuLabel, int menuShortCut, ActionListener menuHandler) {
        JMenuItem menuItem = new JMenuItem(menuLabel, menuShortCut);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(menuShortCut,
                ActionEvent.ALT_MASK));
        menuItem.addActionListener(menuHandler);  
        menu.add(menuItem);
    }
     
   
}
