/**
 * @author Ante Zovko
 * @version May 12, 2020
 * 
 */

/**
 * Creates a Parellelogram
 */
public class Parallelogram extends Shape{


    public Parallelogram(int width, int height){
        super(width, height);
    }

    @Override
    public String toString(){
        String drawing = "";
        for(int i = this.getHeight(); i > 0;i--){
            drawing += "  ".repeat(i - 1) + "* ".repeat(this.getWidth()) + "\n"; 
        }
        return drawing;
    }
}