public class TestLinkedList {
    

    public static void main(String[] args) {

        LinkedList list = new LinkedList();
        // list.display();

        list.generateSequence(9);
        list.display();

        list.insertFront(4);
        list.display();

        list.insertFront(10);
        list.display();


        list.generateRandomSequence(15, 50);
        list.display();

        list.generateRandomSequence(15, 50);
        list.display();

        list.insertEnd(20);
        list.display();

        list.delete(12);
        list.display();

        list.delete(9);
        list.display();

        


    }
    
}
