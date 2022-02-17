# Ante Zovko
# Version February 7th, 2021

# Converts unbalanced tree to AVL tree

# Reference: https://www.geeksforgeeks.org/convert-normal-bst-balanced-bst/




class Tree_Node: 
    def __init__(self, value, left=None, right=None): 
        self.value = value
        self.left=left
        self.right=right


# Recursively stores all nodes in the nodes array
def storeNodes(root,nodes): 
      
    if not root: 
        return
      
    # Store nodes in Inorder 
    storeNodes(root.left,nodes) 
    nodes.append(root) 
    storeNodes(root.right,nodes) 
  
# Construct the balanced tree
def BT2AVLHelperFunction(nodes,start,end): 
      
    # base case  
    if start>end: 
        return None
  
    # Get the middle element and make it root  
    mid=(start+end)//2
    node=nodes[mid] 
  
    # Using index in Inorder traversal, construct  
    # left and right subtress 
    node.left=BT2AVLHelperFunction(nodes,start,mid-1) 
    node.right=BT2AVLHelperFunction(nodes,mid+1,end) 
    return node 
  
# Builds balanced BST
def BT2AVL(root): 
      
    # Store nodes of given BST in sorted order  
    nodes=[] 
    BT2AVL(root,nodes) 
  
    # Constucts BST from nodes[]  
    n=len(nodes) 
    return BT2AVLHelperFunction(nodes,0,n-1) 
  

###### WE CAN SUBMIT EVERYTHING ABOVE THIS ##########




# Function to do preorder traversal of tree 
def preOrder(root): 
    if not root: 
        return

    print("{} ".format(root.data),end="") 
    preOrder(root.left) 
    preOrder(root.right) 




# Driver code 
if __name__=='__main__': 
    # Constructed binary tree 
    #         10  
    #         /  
    #         8  
    #         /  
    #     7  
    #     /  
    #     6  
    #     /  
    # 5  
    root = Tree_Node(10) 
    root.left = Tree_Node(8) 
    root.left.left = Tree_Node(7) 
    root.left.left.left = Tree_Node(6) 
    root.left.left.left.left = Tree_Node(5) 
    preOrder(root) 
    root = BT2AVL(root) 
    print("Preorder traversal of balanced BST is :") 
    preOrder(root) 

    """
    This is what i get when I run the preorder
    before and after

    10 8 7 6 5 
    7 5 6 8 10

    """