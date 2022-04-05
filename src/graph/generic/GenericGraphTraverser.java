package graph.generic;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JMenu;

import graph.GraphIF;
import graph.Vertex;

public class GenericGraphTraverser {
	
	private GenericGraphTraverser() {}
	
	public static<T> List<T> getDepthFirstRepresentation(GenericGraphIF<T> graphToTraverse, T initialVertex) {
		List<T> visitedVertices = new ArrayList<T>();
		addAdjacentUnvisitedVerticesDepthFirst(graphToTraverse, initialVertex, visitedVertices);
		return visitedVertices;
	}
	
	private static<T> void addAdjacentUnvisitedVerticesDepthFirst(GenericGraphIF<T> graphToTraverse, T vertex, List<T> visitedVertices){
		visitedVertices.add(vertex);
		List<T> adjacentVertices = graphToTraverse.getAdjacentVertices(vertex);
		
		for(T adjacentCandidate : adjacentVertices) {
			if(graphToTraverse.containsEdge(vertex, adjacentCandidate) && !visitedVertices.contains(adjacentCandidate)) {
				addAdjacentUnvisitedVerticesDepthFirst(graphToTraverse, adjacentCandidate, visitedVertices);
			}
		}
	}

	public static<T> List<T> getBreadthFirstRepresentation(GenericGraphIF<T> graphToTraverse, T initialVertex) {
		List<T> visitedVertices = new ArrayList<T>();
		List<T> verticesToVisit = new ArrayList<T>();
		verticesToVisit.add(initialVertex);
		do {
			T vertexToInspect = verticesToVisit.remove(0);
			visitedVertices.add(vertexToInspect);
			for(T vertexCandidate : graphToTraverse.getAdjacentVertices(vertexToInspect)) {
				if(!visitedVertices.contains(vertexCandidate) && !verticesToVisit.contains(vertexCandidate)) {
					verticesToVisit.add(vertexCandidate);
				}
			}
		}
		while(verticesToVisit.size() > 0);
		
		return visitedVertices;
	}
}
