package graph.list;

import graph.*;

public class UndirectedListGraph extends DirectedListGraph {

	@Override
	public void addEdge(Vertex oneVertex, Vertex anotherVertex) {
		super.addEdge(oneVertex, anotherVertex);
		super.addEdge(anotherVertex, oneVertex);
	}
}