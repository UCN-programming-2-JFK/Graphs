package graph.list;

import java.util.*;


import graph.*;

public class DirectedListGraph implements GraphIF {

	protected HashMap<Vertex, List<Vertex>> vertices = new HashMap<>();

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
	public void addEdge(Vertex vertexFrom, Vertex vertexTo) {
		vertices.get(vertexFrom).add(vertexTo);
	}

	@Override
	public boolean containsEdge(Vertex vertexFrom, Vertex vertexTo) {
		return vertices.get(vertexFrom).contains(vertexTo);
	}

	//note some authors define any connected vertice (no matter direction) to be adjacent
	//https://mathoverflow.net/questions/330512/adjacency-definition-for-a-directed-graph
	@Override
	public List<Vertex> getAdjacentVertices(Vertex vertex) {		
		return vertices.get(vertex);
	}
}