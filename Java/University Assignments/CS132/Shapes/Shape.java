/**
 * @author Ante Zovko
 * @version May 12, 2020
 * 
 */

/**
 * Creates a shape class
 */
public class Shape {
    private int width;
    private int height;

    public Shape(){
        this.width = 1;
        this.height = 1;
    }

    public Shape(int width, int height){
        if(width > 0){
            this.width = width;
        }
        else{
            this.width = 1;
        }
        if(height > 0){
            this.height = height;
        }
        else{
            this.height = 1;
        }
        
    }

    // ######## Getters/Setters #########
    public int getWidth(){
        return this.width;
    }
    public int getHeight(){
        return this.height;
    }
    public void setWidth(int value){
        if(value > 0){
            this.width = value;
        }
    }
    public void setHeight(int value){
        if(value > 0){
            this.height = value;
        }
    }

    @Override
    public String toString(){
        String drawing = "";
        for(int i = 0; i < this.height;i++){
            drawing += "* ".repeat(this.width) + "\n"; 
        }
        return drawing;
    }

}