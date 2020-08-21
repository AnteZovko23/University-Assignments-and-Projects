/**
 * @author Ante Zovko
 * @version May 12, 2020
 * 
 */

/**
 * Creates a Triangle
 */
public class Triangle extends Shape{


    public Triangle(int area){
        super(area, area);
    }

    @Override
    public String toString(){
        String drawing = "";
        for(int i = 0; i < this.getWidth();i++){
            drawing += "* ".repeat(this.getWidth() - i) + "\n"; 
        }
        return drawing;
    }
}