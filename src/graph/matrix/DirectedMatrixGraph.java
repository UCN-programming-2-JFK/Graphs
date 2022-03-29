package graph.matrix;

import java.util.*;

import graph.*;

public class DirectedMatrixGraph implements GraphIF {

	protected HashMap<Vertex, Integer> verticeIndices = new HashMap<Vertex, Integer>();
	protected int currentCapacity = 32;
	protected boolean[][] adjacencyMatrix = new boolean[currentCapacity][currentCapacity];

	@Override
	public int size() {
		return verticeIndices.size();
	}

	@Override
	public void addVertex(Vertex vertexToAdd) {
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

	public void addVertices(List<Vertex> verticesToAdd) {
		for (Vertex vertexToAdd : verticesToAdd) {
			addVertex(vertexToAdd);
		}
	}

	@Override
	public List<Vertex> getAllVertices() {
		return new ArrayList<Vertex>(verticeIndices.keySet());
	}

	@Override
	public boolean containsVertex(Vertex vertex) {
		return verticeIndices.containsKey(vertex);
	}

	@Override
	public void addEdge(Vertex vertexFrom, Vertex vertexTo) {
		int vertexFromIndex = verticeIndices.get(vertexFrom);
		int vertexToIndex = verticeIndices.get(vertexTo);
		adjacencyMatrix[vertexFromIndex][vertexToIndex] = true;
	}

	@Override
	public boolean containsEdge(Vertex vertexFrom, Vertex vertexTo) {
		int vertexFromIndex = verticeIndices.get(vertexFrom);
		int vertexToIndex = verticeIndices.get(vertexTo);
		return adjacencyMatrix[vertexFromIndex][vertexToIndex];
	}

	// note some authors define any connected vertice (no matter direction) to be adjacent
	// here we define adjacent vertices to be only those that this vertex points to
	@Override
	public List<Vertex> getAdjacentVertices(Vertex vertex) {

		List<Vertex> adjacentVertices = new ArrayList<>();
		int index = verticeIndices.get(vertex);
		for (int i = 0; i < adjacencyMatrix[index].length; i++) {
			if (adjacencyMatrix[index][i]) {
				for (Vertex key : verticeIndices.keySet()) {
					if (verticeIndices.get(key) == i) {
						adjacentVertices.add(key);
					}
				}
			}
		}
		return adjacentVertices;
	}
}