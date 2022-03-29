package graph.list.generic;

import java.util.List;

public interface GenericGraphIF<T> {
	
	int size();

	void addVertex(T vertexToAdd);
	
	void addVertices(List<T> verticesToAdd);

	List<T> getAllVertices ();

	void addEdge(T vertexFrom, T vertexTo);

	boolean containsEdge(T vertexFrom, T vertexTo);
	
	boolean containsVertex(T vertex);

	List<T> getAdjacentVertices(T vertex);
}