package graph.generic.matrix;

public class GenericUndirectedWeightedMatrixGraph<T> extends GenericDirectedWeightedMatrixGraph<T> {

	@Override
	public void addEdge(T vertexFrom, T vertexTo, int weight) {
		int vertexFromIndex = verticeIndices.get(vertexFrom);
		int vertexToIndex = verticeIndices.get(vertexTo);
		adjacencyMatrix[vertexFromIndex][vertexToIndex] = weight;
		adjacencyMatrix[vertexToIndex][vertexFromIndex] = weight;
	}
}