package gbst;

public class BinarySearchTree<T extends Comparable<T>> {
    private BinaryNode<T> root;
    public BinarySearchTree( )
    {
        root = null;
    }
    public BinarySearchTree(T value) {
        root = new BinaryNode<T>(value);
    }

    private static class BinaryNode<T> {
        T element;      	  // The data in the node
        BinaryNode<T> left;   // Left child
        BinaryNode<T> right;  // Right child

        BinaryNode( T theElement )
        {
            this( theElement, null, null );
        }

        BinaryNode( T theElement, BinaryNode<T> lt, BinaryNode<T> rt )
        {
            element  = theElement;
            left     = lt;
            right    = rt;
        }

        public T getValue() {
            return element;
        }

        public void setValue(T value) {
            this.element = value;
        }

        public BinaryNode<T> getLeft() {
            return left;
        }

        public void setLeft(BinaryNode<T> left) {
            this.left = left;
        }

        public BinaryNode<T> getRight() {
            return right;
        }

        public void setRight(BinaryNode<T> right) {
            this.right = right;
        }
    }

    public void insert(T key) { 
        root = add(root, key); 
    }

    BinaryNode<T> add(BinaryNode<T> root, T key) {
         if (root == null) {
             root = new BinaryNode<T>(key);
             return root;
         }

         if (key.compareTo(root.element) < 0)
             root.left = add(root.left, key);
         else
             root.right = add(root.right, key);
   
         return root; 
    }

// inorder function 
    void printInOrder()  { 
    	printInOrderHelper(root); 
    } 
  
  
    void printInOrderHelper(BinaryNode<T> root) { 
        if (root != null) {
        	printInOrderHelper(root.left);
            System.out.println(root.element);
            printInOrderHelper(root.right);
        }
    }
//postOrder function
    String postOrder() {
    	return postOrderHelper(root);
    }

    String postOrderHelper(BinaryNode<T> root) {
        if (root != null) {
            String s = new String(root.element.toString());
            String left = postOrderHelper(root.left);
            if (left.length() != 0)
            	s += ", " + left;
            String right = postOrderHelper(root.right);
            if (right.length() != 0)
            	s += ", " + right;
            return s;
        }
        return "";
    }

    //height of the tree
   public int height() {
    	return heightHelper(root) -1;
    }
    
    public int heightHelper(BinaryNode<T> node){
     	if (node == null){
     		return 0;
     	}
     	else {
    	   int leftHeight = heightHelper(node.left);
           int rightHeight = heightHelper(node.right);
           if (leftHeight > rightHeight)
               return (leftHeight + 1);
           else
               return (rightHeight + 1);
       }
    }
    
    //passedTest function
    private boolean passedTest(){
    	return passedTestHelper(root);
    }
    
    private boolean passedTestHelper(BinaryNode<T> node){
    	int leftHeight = heightHelper(node.left); 
        int rightHeight = heightHelper(node.right); 
        if ((leftHeight - rightHeight) > 1 ||(rightHeight - leftHeight) > 1 ){
        	return false;
        }
        else 
        	return true;
    }
    // allPassed function
    private boolean allPassed(){
    	boolean result = passedTestHelper(root);
    	if(result == true){
    		return true;
    	}
    	else 
    		return false;	
    }
    //Create a function which calculates number of nodes. Create a 
    //function which
    
    public int Count(){
    	return countHelper(root);
    }
    public int countHelper(BinaryNode<T> root){  
        if (root == null){
        	return 0;	
        }
        return 1 + (countHelper(root.left) + countHelper(root.right));  
    }

    public static void main(String args[]){
    	BinarySearchTree m = new BinarySearchTree<Integer>();
    	m.insert(5);
    	m.insert(4);
    	m.insert(1);
    	
    
    	System.out.println("printing inorder: ");
    	m.printInOrder();
    	String s = m.postOrder();
    	System.out.println("printing postorder: " + s);
    	int h = m.height();
    	System.out.println("printing height: " + h);
    	int c = m.Count();
    	System.out.println("printing number of nodes: " + c);
    	boolean result = m.allPassed();
    	System.out.println("printing allPassed: " + result);
    	boolean past = m.passedTest();
    	System.out.println("printing passedTest: " + past);
    	
    	
    	
	}
}
