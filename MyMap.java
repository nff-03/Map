import java.io.*;
import java.lang.Math;
import java.util.*;

public class MyMap {
	
	private Graph map; // an instance variable of the graph that represents the map
	private int scale;  // an instance variable representing the scale
	private int start;  // an instance variable representing the start node's id
	private int end;  // an instance variable representing the end node's id
	private int width; // an instance variable representing the width of the graph
	private int length;  // an instance variable representing the length of the graph
	private int maxPrivate;  // an instance variable representing the maximum number of private roads
	private int maxConst;  // an instance variable representing the maximum number of construction roads
	private String[] roadTypes = new String[100];  // // an instance variable representing the strings describing the roads
	
	Stack<Node> p = new Stack<Node>();  // a stack to store the path in
	
	// a constructor that takes in an input file and creates a graph of it
	public MyMap(String inputFile) throws MapException {
		
		try {
			
			// open file and start scanning it
			File file = new File(inputFile);
			
			if(file.exists() == false) {
				
				throw new MapException("Invlaid input file!");
			}
			
			Scanner scanner = new Scanner(file);
			
			String line = scanner.nextLine();  // look at the first line
			
			scale = Integer.parseInt(line);  // store its int in the scale
						
			line = scanner.nextLine();  // look at the next line
			
			start = Integer.parseInt(line);  // store its int in the start node's id
			
			line = scanner.nextLine(); // look at the next line
			
			end = Integer.parseInt(line);  // store its int in the end node's id
			
			line = scanner.nextLine(); // look at the next line

			width = Integer.parseInt(line);  // store its int in the width
			
			line = scanner.nextLine(); // look at the next line

			length = Integer.parseInt(line); // store its int in the length
			
			line = scanner.nextLine(); // look at the next line

			maxPrivate = Integer.parseInt(line);  // store its int in the maximum number of private roads
			
			line = scanner.nextLine(); // look at the next line
			
			maxConst = Integer.parseInt(line);  // store its int in the maximum number of construction roads
			
			line = scanner.nextLine(); // look at the next line
			
			int i = 0; // an int indicator to the position in the array
						
			// while there is lines
			while(scanner.hasNext()) {
				
				roadTypes[i++] = line;  // store the line in the roadTypes array in position i
				
				line = scanner.nextLine(); // look at the next line
				
			}
			
			roadTypes[i] = line; // store the last line into roadTypes
			
			scanner.close();  // close the file
			
			map = new Graph(width * length);  // create a graph with the size width * length
			
			boolean horiz;  // a boolean indicator of whether the line is horizontal or vertical
			int c;  // indicator of what node we are processing
			int n;  // indicator of what character we are processing
			
			int j = 0;  // an int indicator to the position in the array 
			
			// while the int indicator is less than the length of the array and the array at position j is not null
			while (j < roadTypes.length && roadTypes[j] != null) {
				
				c = 0 + (width * Math.floorDiv(j, 2)); // process the node at zero plus whatever j is divided by 2
				
				// if j is even
				if(j % 2 == 0) {
					
					horiz = true;  // set the horizontal indicator to true
				}
				
				// if j is odd
				else {
					
					horiz = false;   // set the horizontal indicator to false
				}
				
				// if it is a horizontal edge
				if(horiz == true) {
					
					n = 1;  // process the character at 1
					
					// for the length of the string
					for(int f = 0; f < roadTypes[j].length(); f++) {
						
						if(roadTypes[j].charAt(n) != 'B') {  // if the character at n is not B
						
							// if it is P, add a public edge
							if(roadTypes[j].charAt(n) == 'P') {
								
								map.addEdge(map.getNode(c), map.getNode(c+1), "public");
							}
							
							// if it is V, add a private edge
							else if(roadTypes[j].charAt(n) == 'V') {
								
								map.addEdge(map.getNode(c), map.getNode(c+1), "private");
							}

							// if it is C, add a construction edge
							else if(roadTypes[j].charAt(n) == 'C') {
								
								map.addEdge(map.getNode(c), map.getNode(c+1), "construction");
							}
							
							
						}
						
						c = c + 1;  // increment node we are looking at by one
						n = n + 2;  // increment the position character we are looking at by two
						
						if(n >= roadTypes[j].length()) {
							break;
						}
					}
				}
				
				else {
					
					n = 0; // process the character at 0
					
					// for the length of the string
					for(int f = 0; f < roadTypes[j].length(); f++) {
						
						if(roadTypes[j].charAt(n) != 'B') { // if the character at n is not B
							
							// if it is P, add a public edge
							if(roadTypes[j].charAt(n) == 'P') {
								
								map.addEdge(map.getNode(c), map.getNode(c+width), "public");
							}
							
							// if it is V, add a private edge
							else if(roadTypes[j].charAt(n) == 'V') {
								
								map.addEdge(map.getNode(c), map.getNode(c+width), "private");
							}
							
							// if it is C, add a construction edge
							else if(roadTypes[j].charAt(n) == 'C') {
								
								map.addEdge(map.getNode(c), map.getNode(c+width), "construction");
							}
							
						}
						
						c = c + 1; // increment node we are looking at by one
						n = n + 2; // increment the position character we are looking at by two
						
						if(n >= roadTypes[j].length()) {
							break;
						}
					}
				}
				
				j = j + 1;  // increment the indicator by one
			}
			
		}
	    
		// catch an exception when opening the file
		catch (Exception e) {
			
			e.printStackTrace();
	    }
	}
	
	// a method to get the graph
	public Graph getGraph() {
		
		return map;
	}
	
	// a method to get the start node's id
	public int getStartingNode() {
		
		return start;
	}
	
	// a method to get the destination node's id
	public int getDestinationNode() {
		
		return end;
	}
	
	// a method to get the maximum number of private roads
	public int maxPrivateRoads() {
		
		return maxPrivate;
	}
	
	// a method to get the maximum number of construction roads
	public int maxConstructionRoads() {
		
		return maxConst;
	}
	
	// a method to find the path from the starting node to the destination node
	public Iterator<Node> findPath(int start, int destination, int maxPrivate, int maxConstruction) {
		
		try {
			
			Node startNode = map.getNode(start);  // get the node storing the start id
			
			startNode.markNode(true);  // mark the start node
			p.push(startNode);  // push it into the stack
			
			if(start == destination) {  // if the start's id equals the destination's id
				return p.iterator();  // return the iterator of the path stack
			}
			
			Iterator<Edge> incidentEdges = map.incidentEdges(startNode);  // get the incident edges of start node
			
			// loop through the incident edges
			while(incidentEdges.hasNext()) {
				
				int privateRoads = maxPrivate;  // store the available private roads
				int constRoads = maxConstruction; // store the available construction roads
				
				Edge road = incidentEdges.next();  // get the next edge
				Node nextNode = road.secondNode();  // get the next node, which is the second node in the edge
				
				// if that node equals the start node, make next node equal the first node in the edge
				if(nextNode == startNode) {
					
					nextNode = road.firstNode();
				}
				
				// if the next node is unmarked, and the edge type is private, and we have available private roads
				if(nextNode.getMark() == false && road.getType().equals("private") && privateRoads - 1 >= 0) {
					
					privateRoads = privateRoads - 1;  // decrement the number of available private roads
					
					// make a recursive call with the next node, and updated number of available private roads
					Iterator<Node> res = findPath(nextNode.getId(), destination, privateRoads, constRoads);
					
					// if what the method return is not null, return it
					if(res != null) {
						 
						return res;
					}
				}
				
				// if the next node is unmarked, and the edge type is construction, and we have available construction roads
				else if(nextNode.getMark() == false && road.getType().equals("construction") && constRoads - 1 >= 0) {
					
					constRoads = constRoads - 1; // decrement the number of available construction roads
					
					// make a recursive call with the next node, and updated number of available construction roads
					Iterator<Node> res = findPath(nextNode.getId(), destination, privateRoads, constRoads);
					
					// if what the method return is not null, return it
					if(res != null) {
						
						return res;
					}
				}
				
				// if the next node is unmarked, and the edge type is public
				else if(nextNode.getMark() == false && road.getType().equals("public")) {
					
					// make a recursive call with the next node
					Iterator<Node> res = findPath(nextNode.getId(), destination, privateRoads, constRoads);
					
					// if what the method return is not null, return it
					if(res != null) {
						
						return res;
					}
				}
			}
			
			startNode.markNode(false);  // unmark the start node
			p.pop(); // pop it from the path stack
			
			return null;  // return null
			
		}
		
		// catch any exception
		catch (Exception e){
			e.printStackTrace();
			
		}
		
		return null;  // return null
	}

}
