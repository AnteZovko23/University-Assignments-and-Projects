/*
**** PROJECT 2 ****
Complete this class to implement Binary Search Trees.
Uses the TreeNode class we designed in lecture.

Names of group members: Ante Zovko and Chance Jackson
(This serves as your signature that you did not plagiarize
 someone else's code.)

Date: November 9th, 2020

Class/Section: CSC 220, Section 003, Fall 2020

Instructor: Jason Terry

Group Name: ReturnToSleep;


*/

public class BST {

	/* Receives the root of a BST. Returns true if
	   the tree is empty and false otherwise. */
	public static boolean isEmpty(TreeNode n) {

        return n == null;

	}

	/* Receives the root of a BST. Visits the tree
	   nodes in preorder and displays data stored
	   in each node of the tree to screen. Must
	   be a recursive method. */
	public static void preOrderDisplay(TreeNode n) {

		if (n == null) 
            return; 
  
        /* first print data of n */
        System.out.print(n.getData() + " "); 
  
        /* then recur on left subtree */
        preOrderDisplay(n.getLLink()); 
  
        /* now recur on right subtree */
        preOrderDisplay(n.getRLink()); 
	
	}

	/* Receives the root of a BST. Visits all tree
	   nodes in inorder and displays data stored in
	   each node of the tree to screen. Must
	   be a recursive method. */
	public static void inOrderDisplay(TreeNode n) {

		if (n == null) 
            return; 
  
        /* first recur on left child */
        inOrderDisplay(n.getLLink()); 
  
        /* then print the data of n */
        System.out.print(n.getData() + " "); 
  
        /* now recur on right child */
        inOrderDisplay(n.getRLink()); 
    

	}

	/* Receives the root of a BST. Visits the tree
	   nodes in postorder and displays data stored in
	   each node of the tree to screen. Must
	   be a recursive method. */
	public static void postOrderDisplay(TreeNode n) {

		if (n == null) 
		return; 

		// first recur on left subtree 
		postOrderDisplay(n.getLLink()); 

		// then recur on right subtree 
		postOrderDisplay(n.getRLink()); 

		// now deal with the n 
		System.out.print(n.getData() + " "); 

	}

	/* Receives the root of a BST and returns the 
	   smallest value in the tree. Returns -999
	   if the tree is empty. */
	public static int getMin(TreeNode n) {

		if(isEmpty(n))
			return -999;

		if(n.getLLink() != null) {
            return getMin(n.getLLink());
        }
        return n.getData();
		
	}

	/* Receives the root of a BST and returns the 
	   largest value in the tree. Returns -999
	   if the tree is empty. */
	public static int getMax(TreeNode n) {
		
		if(isEmpty(n))
			return -999;

		if(n.getRLink() != null) {
            return getMax(n.getRLink());
        }
        return n.getData();

	}

	/* Receives the root of a BST and an integer x.	
       Creates a new tree node containing data value x and 
       inserts it correctly into the tree. Returns the 
       root of the adjusted tree. */
	public static TreeNode insert(TreeNode root, int x) {
			
			if(isEmpty(root))
				return null;

			if (x < root.getData()) {
				if (root.getLLink() != null) {
					insert(root.getLLink(), x);
				} 
				else {     
					TreeNode temp = new TreeNode();
					temp.setData(x);
					root.setLLink(temp);
				}
			} 
			else if (x > root.getData()) {
				if (root.getRLink() != null) {
					insert(root.getRLink(), x);
				}
				else {
					TreeNode temp = new TreeNode();
					temp.setData(x);
					root.setRLink(temp);
				}
			}

			return root;
	}
	
	/* Receives the root of a BST and an integer x.
	   Deletes the tree node containing data value x, if it 
	   exists, and adjusts nodes so that the tree remains
	   a BST. Returns the root of the (adjusted) tree. Uses 
	   the method BST.getMin. Can use the method BST.delete
	   recursively. */
	public static TreeNode delete(TreeNode n, int x) {
		if(n == null)
		 return n;
 
        if(x < n.getData()) {

			n.setLLink(delete(n.getLLink(), x));
			
        } else if(x > n.getData()) {

			n.setRLink(delete(n.getRLink(), x));
			
        } else {
          
            if(n.getLLink() == null && n.getRLink() == null) {
				return null;
				
            } else if(n.getLLink() == null) {

				return n.getRLink();
				
            } else if(n.getRLink() == null) {

				return n.getLLink();
				
            } else {
     
                int minValue = getMin(n.getRLink());
                n.setData(minValue);
                n.setRLink(delete(n.getRLink(), minValue));
            }
        }
 
        return n;
    }

	

	/* Receives an integer k. Creates a BST
	   containing k random integers from 0-100.
	   Returns the root of the tree created. Uses
	   the method BST.insert. Returns null if a non-
	   positive integer is received. */
	public static TreeNode generateRandom(int k) {

		if(k < 0)
			return null;

		TreeNode head = new TreeNode();
		// head.setData((int)(Math.random()*100));
		head.setData((int)(Math.random()*100));

		for(int i = 0; i < k-1; i++){

			insert(head, (int)(Math.random()*100));


		}

		
		

		return head;


	}


	/* The following two methods are clever ways to 
	   display a BST to the screen (sideways). You
	   are welcome to use them for your testing and/or
	   edit them if you think you can improve them. */
	public static void displayTree1(TreeNode n, int tab) {
		if (n != null) {
			//go down the right subtree and increase
			//the number of tabs i'll have to make
			displayTree1(n.getRLink(), tab+1); 

			for (int i = 0; i < tab; i++)
				System.out.print("\t"); //print out the appropriate no. of tabs
			
			System.out.println(n.getData());//and the data, and move to next line
			displayTree1(n.getLLink(), tab+1); //go down the left subtree and increase tabs
		}
	}
	
	public static void displayTree2(TreeNode n, int space) {
			if (n != null) {
				space += 10;
				displayTree2(n.getRLink(), space);
				System.out.println();

				for (int i = 10; i<space; i++)
					System.out.print(" ");

				System.out.print(n.getData() + "\n");
				
				displayTree2(n.getLLink(), space);
			}
	}
}