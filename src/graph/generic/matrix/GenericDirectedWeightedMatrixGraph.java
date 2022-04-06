package graph.generic.matrix;

import java.util.*;

import graph.generic.GenericWeightedGraphIF;

/*
 * This implementation of a generic, directed, weighted, adjacency-matrix backed graph
 * uses the Integer.MAX_VALUE (2.147.483.647) in the matrix to represent "no edge" between vertices.
 * The matrix is initialized with space for 32 vertices (with room for all connecting edges)
 * and is doubled in size (64, 128, etc.) every time a vertice is added which exceeds the current capacity.
 */
public class GenericDirectedWeightedMatrixGraph<T> implements GenericWeightedGraphIF<T> {

	protected HashMap<T, Integer> verticeIndices = new HashMap<T, Integer>();
	protected int currentCapacity = 32;
	protected int[][] adjacencyMatrix = new int[currentCapacity][currentCapacity];

	@Override
	public int size() {
		return verticeIndices.size();
	}

	public GenericDirectedWeightedMatrixGraph() {
		//Initialize all edges as "non-existing" by using "infinity" (Integer.MAX_VALUE)
		//for the cost of moving from vertex to vertex along that edge
		for (int i = 0; i < currentCapacity; i++) {
			for (int j = 0; j < currentCapacity; j++) {
				adjacencyMatrix[i][j] = Integer.MAX_VALUE;
			}
		}
	}

	@Override
	public void addVertex(T vertexToAdd) {
		verticeIndices.put(vertexToAdd, verticeIndices.size());
		if (verticeIndices.size() == currentCapacity) {
			doubleCapacity();
		}
	}

	private void doubleCapacity() {
		int[][] newAdjacencyMatrix = new int[currentCapacity * 2][currentCapacity * 2];
		for (int i = 0; i < currentCapacity; i++) {
			for (int j = 0; j < currentCapacity; j++) {
				newAdjacencyMatrix[i][j] = adjacencyMatrix[i][j];
			}
		}
		currentCapacity *= 2;
		adjacencyMatrix = newAdjacencyMatrix;
	}

	public void addVertices(List<T> verticesToAdd) {
		for (T vertexToAdd : verticesToAdd) {
			addVertex(vertexToAdd);
		}
	}

	@Override
	public List<T> getAllVertices() {
		return new ArrayList<T>(verticeIndices.keySet());
	}

	@Override
	public boolean containsVertex(T vertex) {
		return verticeIndices.containsKey(vertex);
	}

	@Override
	public void addEdge(T vertexFrom, T vertexTo) {
		addEdge(vertexFrom, vertexTo, 1);
	}
	
	@Override
	public void addEdge(T vertexFrom, T vertexTo, int weight) {
		int vertexFromIndex = verticeIndices.get(vertexFrom);
		int vertexToIndex = verticeIndices.get(vertexTo);
		adjacencyMatrix[vertexFromIndex][vertexToIndex] = weight;
	}

	@Override
	public boolean containsEdge(T vertexFrom, T vertexTo) {
		return getEdgeWeight(vertexFrom, vertexTo) != Integer.MAX_VALUE;
	}
	
	@Override
	public int getEdgeWeight(T vertexFrom, T vertexTo) {
		int vertexFromIndex = verticeIndices.get(vertexFrom);
		int vertexToIndex = verticeIndices.get(vertexTo);
		return adjacencyMatrix[vertexFromIndex][vertexToIndex];
	}

	@Override
	public List<T> getAdjacentVertices(T vertex) {

		List<T> adjacentVertices = new ArrayList<>();
		int index = verticeIndices.get(vertex);
		for (int i = 0; i < adjacencyMatrix[index].length; i++) {
			if (adjacencyMatrix[index][i] != Integer.MAX_VALUE) {
				for (T key : verticeIndices.keySet()) {
					if (verticeIndices.get(key) == i) {
						adjacentVertices.add(key);
					}
				}
			}
		}
		return adjacentVertices;
	}
}