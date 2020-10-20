
/**
 * @author Ante Zovko
 * @version October 20th, 2020
 * 
 * Deletes a Node from a linked list
 * 
 */
public class ZovkoA7 {


    /**
     * Deletes a Node if given parameter is found
     * 
     * @param h the head node
     * @param k the given parameter
     * 
     * @return the head node
     */
    public static Node delete(Node h, int k) {

        Node currentNode = h;

        // If list is empty
        if(isEmpty(h)){
            return null;
        }
        // If the head is removed
        else if(h.getData() == k){
            h = h.getLink();
            return h;
        }
        else {

            while(currentNode.getLink() != null){

                Node temp = currentNode;
                currentNode = currentNode.getLink();
    
                if(currentNode.getData() == k){
                    temp.setLink(currentNode.getLink());
                }

        }
       
        return h;

    }



    }

}