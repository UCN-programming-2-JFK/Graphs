package graph.tools;

import java.util.ArrayList;
import java.util.List;

import graph.GraphIF;
import graph.Vertex;

public class GraphTraverser {

	private GraphTraverser() {}
	
	public static List<Vertex> getDepthFirstRepresentation(GraphIF graphToTraverse, Vertex initialVertex) {
		List<Vertex> visitedVertices = new ArrayList<Vertex>();
		addAdjacentUnvisitedVerticesDepthFirst(graphToTraverse, initialVertex, visitedVertices);
		return visitedVertices;
	}
	
	private static void addAdjacentUnvisitedVerticesDepthFirst(GraphIF graphToTraverse, Vertex vertex, List<Vertex> visitedVertices){
		visitedVertices.add(vertex);
		List<Vertex> adjacentVertices = graphToTraverse.getAdjacentVertices(vertex);
		
		for(Vertex adjacentCandidate : adjacentVertices) {
			if(graphToTraverse.containsEdge(vertex, adjacentCandidate) && !visitedVertices.contains(adjacentCandidate)) {
				addAdjacentUnvisitedVerticesDepthFirst(graphToTraverse, adjacentCandidate, visitedVertices);
			}
		}
	}

	public static List<Vertex> getBreadthFirstRepresentation(GraphIF graphToTraverse, Vertex initialVertex) {
		List<Vertex> visitedVertices = new ArrayList<Vertex>();
		List<Vertex> verticesToVisit = new ArrayList<Vertex>();
		verticesToVisit.add(initialVertex);
		do {
			Vertex vertexToInspect = verticesToVisit.remove(0);
			visitedVertices.add(vertexToInspect);
			for(Vertex vertexCandidate : graphToTraverse.getAdjacentVertices(vertexToInspect)) {
				if(!visitedVertices.contains(vertexCandidate) && !verticesToVisit.contains(vertexCandidate)) {
					verticesToVisit.add(vertexCandidate);
				}
			}
		}
		while(verticesToVisit.size() > 0);
		
		return visitedVertices;
	}
	
}
