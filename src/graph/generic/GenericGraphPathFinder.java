package graph.generic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class GenericGraphPathFinder {

	// this implementation follows the Wikipedia description as close as possible
	// https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm

	public static <T> List<T> getShortestPathUsingDijkstra(GenericWeightedGraphIF<T> graph, T startVertex,
			T endVertex) {
		
		// Mark all nodes unvisited.
		// Create a set of all the unvisited nodes called the unvisited set.
		List<T> unvisitedSet = graph.getAllVertices();
		
		HashMap<T, T> previous = new HashMap<T, T>();

		// Assign to every node a tentative distance value: set it to zero for our
		// initial node and to infinity for all other nodes.
		// The tentative distance of a node v is the length of the shortest path
		// discovered so far between the node v and the starting node.
		// Since initially no path is known to any other vertex than the source itself
		// (which is a path of length zero), all
		// other tentative distances are initially set to infinity.
		HashMap<T, Integer> tentativeWeights = new HashMap<T, Integer>();
		
		for (T key : graph.getAllVertices()) {
			tentativeWeights.put(key, Integer.MAX_VALUE);
		}
		// 
		//Set the initial node as current
		T currentVertex = startVertex;
		tentativeWeights.put(currentVertex, 0);
		boolean isDone = false;

		while (!isDone) {
			int distanceToCurrentNode = tentativeWeights.get(currentVertex);
			
			// For the current node, consider all of its unvisited neighbors
			for (T adjacentVertex : graph.getAdjacentVertices(currentVertex)) {
				System.out.println("Looking at " + adjacentVertex + " from " + currentVertex);
				if (unvisitedSet.contains(adjacentVertex)) {
					System.out.println(adjacentVertex + " is unvisited");
					// and calculate their tentative distances through the current node.
					int newDistanceToAdjacentNodeThroughCurrentNode = distanceToCurrentNode
							+ graph.getEdgeWeight(currentVertex, adjacentVertex);
					int currentDistanceToAdjacentNode = tentativeWeights.get(adjacentVertex);
					System.out.print("Previous distance to " + adjacentVertex + " was " + currentDistanceToAdjacentNode);
					System.out.println(", new distance via " + currentVertex + " is " + newDistanceToAdjacentNodeThroughCurrentNode);
					// Compare the newly calculated tentative distance to the current assigned value
					if(newDistanceToAdjacentNodeThroughCurrentNode < currentDistanceToAdjacentNode) {
						System.out.println("setting route to " + adjacentVertex + " to go from " + currentVertex);
						int smallestValue = newDistanceToAdjacentNodeThroughCurrentNode;
						//and assign the smaller one.
						tentativeWeights.put(adjacentVertex, smallestValue);
						previous.put(adjacentVertex, currentVertex);
					}
				}
			}
			//When we are done considering all of the unvisited neighbors of the current node,
			//mark the current node as visited and remove it from the unvisited set. 
			//A visited node will never be checked again.
			System.out.println("Removing " + currentVertex);
			unvisitedSet.remove(currentVertex);
			currentVertex = getVertexWithLowestTentativeWeight(tentativeWeights, unvisitedSet);
			//If the destination node has been marked visited (when planning a route between two specific nodes) 
			//or if the smallest tentative distance among the nodes in the unvisited set is infinity (when planning a complete traversal
			//- occurs when there is no connection between the initial node and remaining unvisited nodes), then stop. 
			//The algorithm has finished.
			if(!unvisitedSet.contains(endVertex) || getSmallestTentativeValue(unvisitedSet, tentativeWeights) == Integer.MAX_VALUE) {
				isDone = true;
			}
		}
		
		//Once you have marked the destination as visited,
		//you have determined the shortest path to it from the starting point 
		//and can trace your way back following the arrows in reverse.
		
		List<T> shortestPath = buildPathFromPreviousVerticeCollection(previous, endVertex);
		return shortestPath;
	}

	//Helper method for finding the most promising vertex in the map of tentativeWeights
	private static<T> T getVertexWithLowestTentativeWeight(HashMap<T, Integer> tentativeWeights, List<T> unvisitedSet) {
		T vertexWithLowestTentativeWeight = null;
		int currentLowestWeightFound = Integer.MAX_VALUE;
		for(T vertex : unvisitedSet) {
			if(tentativeWeights.get(vertex) < currentLowestWeightFound) {
				vertexWithLowestTentativeWeight = vertex;
			}
		}
		return vertexWithLowestTentativeWeight;
	}

	//Helper method for iterating through the map of previous vertices and reconstructing the path from end to beginning
	private static<T> List<T> buildPathFromPreviousVerticeCollection(HashMap<T, T> previous, T endVertex) {
		List<T> shortestPath = new ArrayList<T>();
		T currentVertex = endVertex;
		do{
			shortestPath.add(currentVertex);
			currentVertex = previous.get(currentVertex);
		}while(currentVertex != null);
		
		Collections.reverse(shortestPath);
		return shortestPath;
	}

	//helper method for finding the smallest tentative value in the unvisited set
	private static<T> int getSmallestTentativeValue(List<T> unvisitedSet, HashMap<T, Integer> tentativeWeights) {
		int smallestValueSoFar = Integer.MAX_VALUE;
		
		for(T vertex : unvisitedSet) {
			smallestValueSoFar = (int)Math.min(smallestValueSoFar, tentativeWeights.get(vertex));
		}
		return smallestValueSoFar;
	}
}