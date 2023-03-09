/* Student Name: Nour Fayadh
 * Student ID: 251205677
 * This class is designed to store edge info, and return them*/

public class Edge {
	
	private Node u;  // instance variable first node of the edge
	private Node v;  // instance variable second node of the edge
	private String type;  // instance variable type of the edge
	
	// a constructor method that takes the first node, second node, and type as a parameter and store it in its 
	// instance variable
	public Edge(Node u, Node v, String type) {
		
		this.u = u;
		this.v = v;
		this.type = type;
	}
	
	// a method to return the first node of the edge
	public Node firstNode() {
		
		return u;
	}
	
	// a method to return the second node of the edge
	public Node secondNode() {
		
		return v;
	}
	
	// a method to return the type of the edge
	public String getType() {
		
		return type;
	}

}
