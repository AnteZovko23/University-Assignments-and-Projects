/**
 * @author Ante Zovko
 * @version May 12, 2020
 * 
 */

/**
 * Shapes Testing
 */
public class ShapesMain{
    public static void main(String[] args) {

        Rectangle r = new Rectangle(12,4);
        System.out.println(r);

        Square s = new Square(6);
        System.out.println(s);

        Triangle t = new Triangle(7);
        System.out.println(t);

        Parallelogram p = new Parallelogram(10,3);
        System.out.println(p);

        Rectangle r2 = new Rectangle(0, 0);
        System.out.println(r2);

        p.setWidth(2);
        p.setWidth(-1);
        p.setHeight(2);
        System.out.println(p);
    }
}