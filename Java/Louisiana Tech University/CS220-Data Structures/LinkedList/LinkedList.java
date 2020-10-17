
/**
 * @author Ante Zovko
 * @version October 15th, 2020
 * 
 * LinkedList implementation
 * 
 */
public class LinkedList {

    private Node head;
    private Node currentNode;


    public LinkedList() {

        this.head = null;
        this.currentNode = head;

    }

    /**
     * Checks if the linked list is empty
     *
     * @return true if the list is empty
     */
    public boolean isEmpty() {

        return this.head == null;

    }

    /**
     * Displays contents of the linked list
     * 
     */
    public void display() {
            

        this.currentNode = this.head;

        while(this.currentNode != null) {

            System.out.print(this.currentNode.getData() + " ");
            this.currentNode = this.currentNode.getNext();


        }

        System.out.printf("\nEnd of List\nSize:%d\n\n",this.length());


    }

    /**
     * Returns the length of the list
     * 
     * @return length of the list
     */ 
    public int length() {

        this.currentNode = this.head;

        int counter = 0;

        while(this.currentNode != null) {

            counter++;
            this.currentNode = this.currentNode.getNext();



        }

        return counter;

    }

    /**
     * Generates a sequence of numbers in the list starting from one up to a given
     * size
     * 
     * @param size the given size
     * @throws IllegalArgumentException if the given size is less than 1
     */
    public void generateSequence(int size) {

        if(size < 1)
            throw new IllegalArgumentException("Size needs to be bigger than 0");


        this.head = new Node();
        this.currentNode = this.head;

        for(int i = 1; i < size; i++){
            
            this.currentNode.setData(i);
            this.currentNode.setNext(new Node());

            this.currentNode = this.currentNode.getNext();



        }
        this.currentNode.setData(size);


    }

    /**
     * Inserts before the current head of the list
     * 
     * @param data given data
     */
    public void insertFront(int data){

        Node newNode = new Node();
        newNode.setData(data);

        this.currentNode = newNode;
        this.currentNode.setNext(head);
        this.head = this.currentNode;

    }

     /**
     * Generates a random sequence of numbers in the list from 0 up to a given
     * upper limit for each number
     * 
     * @param size the given size of the list
     * @param upperLimit the given upper limit of each number
     * @throws IllegalArgumentException if the given size is less than 1 or the upperLimit is less than 1
     */
    public void generateRandomSequence(int size, int upperLimit) {

        if(size < 1 || upperLimit < 1)
        throw new IllegalArgumentException("Size needs to be bigger than 0");

        this.head = new Node();
        this.currentNode = this.head;

        for(int i = 1; i < size; i++){

            this.currentNode.setData((int)(Math.random()*upperLimit));
            this.currentNode.setNext(new Node());

            this.currentNode = this.currentNode.getNext();

        }

        this.currentNode.setData((int) (Math.random()*upperLimit));


    }

    /**
     * Goes to the end of the list
     * 
     */
    private void goToEnd() {

        this.currentNode = this.head;

        while(this.currentNode.getNext() != null){

            this.currentNode = this.currentNode.getNext();

        }

    }

    /**
     * Inserts at the end of the current list
     * 
     * @param data given data
     */
    public void insertEnd(int data) {

        this.goToEnd();
        this.currentNode.setNext(new Node());
        this.currentNode = this.currentNode.getNext();
        this.currentNode.setData(data);

    }

    /**
     * Deletes an element from the list based on value
     * 
     * @param data the data to be deleted
     * @return true iff element is found
     */
    public boolean delete(int data) {

        this.currentNode = this.head;


        if(this.head.getData() == data){
            this.head = this.head.getNext();
            this.currentNode = this.head;
            return true;
        }

        
        
        if(!this.isEmpty()) {

            while(this.currentNode.getNext() != null){

                Node temp = this.currentNode;
                this.currentNode = this.currentNode.getNext();
    
                if(this.currentNode.getData() == data){
                    temp.setNext(this.currentNode.getNext());
                    return true;
                }
    
    
            }
        }

        return false;
        

    }

    
    
}
