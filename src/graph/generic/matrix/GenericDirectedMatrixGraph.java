package graph.generic.matrix;

import java.util.*;

import graph.*;
import graph.generic.GenericGraphIF;

public class GenericDirectedMatrixGraph<T> implements GenericGraphIF<T> {

	protected HashMap<T, Integer> verticeIndices = new HashMap<T, Integer>();
	protected int currentCapacity = 32;
	protected boolean[][] adjacencyMatrix = new boolean[currentCapacity][currentCapacity];

	@Override
	public int size() {
		return verticeIndices.size();
	}

	@Override
	public void addVertex(T vertexToAdd) {
		verticeIndices.put(vertexToAdd, verticeIndices.size());
		if (verticeIndices.size() == currentCapacity) {
			doubleCapacity();
		}
	}

	private void doubleCapacity() {
		boolean[][] newAdjacencyMatrix = new boolean[currentCapacity * 2][currentCapacity * 2];
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
		int vertexFromIndex = verticeIndices.get(vertexFrom);
		int vertexToIndex = verticeIndices.get(vertexTo);
		adjacencyMatrix[vertexFromIndex][vertexToIndex] = true;
	}

	@Override
	public boolean containsEdge(T vertexFrom, T vertexTo) {
		int vertexFromIndex = verticeIndices.get(vertexFrom);
		int vertexToIndex = verticeIndices.get(vertexTo);
		return adjacencyMatrix[vertexFromIndex][vertexToIndex];
	}

	@Override
	public List<T> getAdjacentVertices(T vertex) {

		List<T> adjacentVertices = new ArrayList<>();
		int index = verticeIndices.get(vertex);
		for (int i = 0; i < adjacencyMatrix[index].length; i++) {
			if (adjacencyMatrix[index][i]) {
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