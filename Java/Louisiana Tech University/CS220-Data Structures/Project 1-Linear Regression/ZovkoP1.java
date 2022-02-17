
import javax.swing.JOptionPane;

import java.awt.EventQueue;
/**
 * @author Ante Zovko
 * @version October 11th, 2020
 * 
 * Calculates the equation for the best fit line of given coordinates
 *  
 */
public class ZovkoP1 {

    public static void main(String[] args) {


        try {
			EventQueue.invokeLater(new Runnable() {

                @Override
                public void run() {
                    
                   // Starts the Program
                   new ZovkoP1();

                }


			});
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
       

        

    }

    public ZovkoP1() {
        // Starts the GUI
        new GuiBuilder();

    }

    

}
