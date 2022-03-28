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
}