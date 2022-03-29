package graph.matrix;

import graph.Vertex;

public class UndirectedMatrixGraph extends DirectedMatrixGraph {

	@Override
	public void addEdge(Vertex vertexFrom, Vertex vertexTo) {
		super.addEdge(vertexFrom, vertexTo);
		super.addEdge(vertexTo, vertexFrom);
	}
}