import java.util.Iterator;
import java.util.ArrayList;

public class Graph implements GraphADT{
	
	private Node[] arrNode;  // instance variable representing the array of nodes
	private Edge[][] arrEdge;  // instance variable representing the multidimensional array of edges
	int numNodes;  // an int representing the number of nodes
	
	// a constructor that creates nodes with the given size, and no edges
	public Graph(int n) {
		
		arrNode = new Node[n];  // create node array of given size
		arrEdge = new Edge[n][n];  // create edge multidimensional array of given size
		numNodes = n;  // store the size in its variable
		
		// create nodes from 0, to n-1
		for(int i = 0; i < numNodes; i++) {
			
			arrNode[i] = new Node(i);
		}
		
	}
	
	// a method to add an edge to the graph, takes in two nodes and edge type
	public void addEdge(Node u, Node v, String edgeType) throws GraphException {
		
		// if the first node does not exist
		if(u.getId() < 0 || u.getId() >= numNodes) {
			
			throw new GraphException("Invalid id, Node does not exist");
		}
		
		// if the second node does not exist
		else if(v.getId() < 0 || v.getId() >= numNodes) {
			
			throw new GraphException("Invalid id, Node does not exist");
		}
		
		// if the edge exists already
		else if(arrEdge[u.getId()][v.getId()] != null) {
			
			throw new GraphException("Edge already exists!");
		}
		
		else {
			
			// create edge and add it to the array of edges
			arrEdge[u.getId()][v.getId()] = new Edge(u,v,edgeType);
			arrEdge[v.getId()][u.getId()] = new Edge(u,v,edgeType);
		}
	}

	// a method to get a node
	public Node getNode(int id) throws GraphException {
		
		// if the node does not exist
		if(id < 0 || id >= numNodes) {
			
			throw new GraphException("Invalid id, Node does not exist");
		}
		
		// if it does, return it
		else {
			
			return arrNode[id];
		}
	}

	public Iterator<Edge> incidentEdges(Node u) throws GraphException {
		
		// if the node does not exist
		if(u.getId() < 0 || u.getId() >= numNodes) {
			
			throw new GraphException("Invalid id, Node does not exist");
		}
		
		else {
			
			ArrayList<Edge> incident = new ArrayList<>();  // create an array list
			
			// loop through the edges of the node, and add them to the list
			for(int i = 0; i < numNodes; i++) {
				
				if(arrEdge[u.getId()][i] != null) {
					
					incident.add(arrEdge[u.getId()][i]);
				}
			}
			
			return incident.iterator(); // return the iterator of that list
		}
	}

	// a method to get the edge
	public Edge getEdge(Node u, Node v) throws GraphException {
		
		// if the first node does not exist
		if(u.getId() < 0 || u.getId() >= numNodes) {
			
			throw new GraphException("Invalid id, Node does not exist");
		}
		
		// if the second node does not exist
		else if(v.getId() < 0 || v.getId() >= numNodes) {
			
			throw new GraphException("Invalid id, Node does not exist");
		}
		
		// if the edge does not exist
		else if(arrEdge[u.getId()][v.getId()] == null) {
			
			throw new GraphException("Invalid edge, Edge already exists!");
		}
		
		else {
			
			return arrEdge[u.getId()][v.getId()];  // return the edge
		}
	}

	// check if two nodes have an edge between them
	public boolean areAdjacent(Node u, Node v) throws GraphException {
		
		// if the first node does not exist
		if(u.getId() < 0 || u.getId() >= numNodes) {
			
			throw new GraphException("Invalid id, Node does not exist");
		}
		
		// if the second node does not exist
		else if(v.getId() < 0 || v.getId() >= numNodes) {
			
			throw new GraphException("Invalid id, Node does not exist");
		}
		
		// if the edge exists, return true
		else if(arrEdge[u.getId()][v.getId()] != null) {
			
			return true;
		}
		
		// else return false
		else {
			
			return false;
		}
		
	}

}
