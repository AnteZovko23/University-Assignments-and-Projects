// Binary Trees demo
// Create a TreeNode class (non-static class)
public class TreeNode {
	
	private int data;
	private TreeNode llink;		//left link
	private TreeNode rlink;		//right link
	
	// Constructor
	public TreeNode() {
		data = -999;
		llink = null;
		rlink = null;
	}
	
	// Set and get data methods
	public void setData(int k) {
		data = k;
	}
	
	public int getData() {
		return data;
	}
	
	// Set and get left link methods
	public void setLLink(TreeNode n) {
		llink = n;
	}
	
	public TreeNode getLLink() {
		return llink;
	}
	
	// Set and get right link methods
	public void setRLink(TreeNode n) {
		rlink = n;
	}
	
	public TreeNode getRLink() {
		return rlink;
	}
}
