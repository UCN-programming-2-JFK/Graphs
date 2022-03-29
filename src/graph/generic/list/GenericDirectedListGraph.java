package graph.generic.list;

import java.util.*;


import graph.*;
import graph.list.generic.GenericGraphIF;

public class GenericDirectedListGraph<T> implements GenericGraphIF<T> {

	protected HashMap<T, List<T>> vertices = new HashMap<>();

	@Override
	public int size() {
		return vertices.size();
	}

	@Override
	public void addVertex(T vertexToAdd) {
		vertices.put(vertexToAdd, new ArrayList<T>());
	}

	public void addVertices(List<T> verticesToAdd) {
		for (T vertexToAdd : verticesToAdd) {
			addVertex(vertexToAdd);
		}
	}

	@Override
	public List<T> getAllVertices() {
		return new ArrayList<T>( vertices.keySet());
	}
	
	@Override
	public boolean containsVertex(T vertex) {
		return vertices.containsKey(vertex);
	}

	@Override
	public void addEdge(T vertexFrom, T vertexTo) {
		vertices.get(vertexFrom).add(vertexTo);
	}

	@Override
	public boolean containsEdge(T vertexFrom, T vertexTo) {
		return vertices.get(vertexFrom).contains(vertexTo);
	}

	@Override
	public List<T> getAdjacentVertices(T vertex) {		
		return vertices.get(vertex);
	}
}