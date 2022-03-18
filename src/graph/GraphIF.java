package graph;

import java.util.List;

public interface GraphIF {
	
	int size();

	void addVertex(Vertex vertexToAdd);
	
	void addVertices(List<Vertex> verticesToAdd);

	List<Vertex> getAllVertices ();

	void addEdge(Vertex vertexFrom, Vertex vertexTo);

	boolean containsEdge(Vertex vertexFrom, Vertex vertexTo);
	
	boolean containsVertex(Vertex vertex);

	List<Vertex> getAdjacentVertices(Vertex vertex);

	List<Vertex> depthFirstRepresentation(Vertex initialVertex);
	
	List<Vertex> breadthFirstRepresentation(Vertex initialVertex);
}