package graph.generic.matrix;

public class GenericUndirectedWeightedMatrixGraph<T> extends GenericDirectedWeightedMatrixGraph<T> {

	@Override
	public void addEdge(T vertexFrom, T vertexTo, int weight) {
		super.addEdge(vertexFrom, vertexTo, weight);
		super.addEdge(vertexTo, vertexFrom, weight);
		
	}
}