/**
 * @author Ante Zovko
 * @version October 15th, 2020
 * 
 * Holds integer data and a link to another node
 * 
 */
public class Node {
    
    private int data;
    private Node next;

    public Node(){

        this.data = 0;
        this.next = null;

    }

    public Node(int data, Node next){

        this.data = data;
        this.next = next;

    }


    public int getData() {

        return this.data;

    }

    public Node getNext() {

        return this.next;

    }

    public void setData(int data){

        this.data = data;

    }

    public void setNext(Node next){

        this.next = next;

    }

}
