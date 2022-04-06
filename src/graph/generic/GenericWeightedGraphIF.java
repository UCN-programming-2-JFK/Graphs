package graph.generic;

public interface GenericWeightedGraphIF<T> extends GenericGraphIF<T> {
	
	void addEdge(T vertexFrom, T vertexTo, int weight);

	public int getEdgeWeight(T vertexFrom, T vertexTo);
}