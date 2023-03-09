/* Student Name: Nour Fayadh
 * Student ID: 251205677
 * This class is designed to store node info, and return them */

public class Node {
	
	private int id;  // instance variable id of node
	private boolean mark;  // instance variable mark of node
	
	// constructor for the class, that takes in an id as a parameter, and sets it equal to the instance variable id, and set
	// mark equal to false
	public Node(int id) {
		
		this.id = id;
		this.mark = false;
		
	}
	
	// a method mark node to indicate what mark the node has
	public void markNode(boolean mark) {
		
		this.mark = mark;
	}
	
	// a method to return the mark of the node
	public boolean getMark() {
		
		return mark;
	}
	
	// a method to return the id of the node
	public int getId() {
		
		return id;
	}

}
