package graph.list;

import java.util.*;


import graph.*;

public class UndirectedListGraph implements GraphIF {

	private HashMap<Vertex, List<Vertex>> vertices = new HashMap<>();

	@Override
	public int size() {
		return vertices.size();
	}

	@Override
	public void addVertex(Vertex vertexToAdd) {
		vertices.put(vertexToAdd, new ArrayList<Vertex>());
	}

	public void addVertices(List<Vertex> verticesToAdd) {
		for (Vertex vertexToAdd : verticesToAdd) {
			addVertex(vertexToAdd);
		}
	}

	@Override
	public List<Vertex> getAllVertices() {
		return new ArrayList<Vertex>( vertices.keySet());
	}
	
	@Override
	public boolean containsVertex(Vertex vertex) {
		return vertices.containsKey(vertex);
	}

	@Override
	public void addEdge(Vertex oneVertex, Vertex anotherVertex) {
		vertices.get(oneVertex).add(anotherVertex);
		vertices.get(anotherVertex).add(oneVertex);
	}

	@Override
	public boolean containsEdge(Vertex oneVertex, Vertex anotherVertex) {
		return vertices.get(oneVertex).contains(anotherVertex);
	}

	@Override
	public List<Vertex> getAdjacentVertices(Vertex vertex) {		
		return vertices.get(vertex);
	}

	@Override
	public List<Vertex> depthFirstRepresentation(Vertex initialVertex) {
		List<Vertex> visitedVertices = new ArrayList<Vertex>();
		addAdjacentUnvisitedVerticesDepthFirst(initialVertex, visitedVertices);
		return visitedVertices;
	}
	
	private void addAdjacentUnvisitedVerticesDepthFirst(Vertex vertex, List<Vertex> visitedVertices){
		visitedVertices.add(vertex);
		List<Vertex> adjacentVertices = vertices.get(vertex);
		
		for(Vertex adjacentCandidate : adjacentVertices) {
			if(containsEdge(vertex, adjacentCandidate) && !visitedVertices.contains(adjacentCandidate)) {
				addAdjacentUnvisitedVerticesDepthFirst(adjacentCandidate, visitedVertices);
			}
		}
	}

	@Override
	public List<Vertex> breadthFirstRepresentation(Vertex initialVertex) {
		List<Vertex> visitedVertices = new ArrayList<Vertex>();
		List<Vertex> verticesToVisit = new ArrayList<Vertex>();
		verticesToVisit.add(initialVertex);
		do {
			Vertex vertexToInspect = verticesToVisit.remove(0);
			visitedVertices.add(vertexToInspect);
			for(Vertex vertexCandidate : getAdjacentVertices(vertexToInspect)) {
				if(!visitedVertices.contains(vertexCandidate) && !verticesToVisit.contains(vertexCandidate)) {
					verticesToVisit.add(vertexCandidate);
				}
			}
		}
		while(verticesToVisit.size() > 0);
		
		return visitedVertices;
	}
}