package graph.generic.list;

public class GenericUndirectedListGraph<T> extends GenericDirectedListGraph<T> {

	@Override
	public void addEdge(T vertexFrom, T vertexTo) {
		vertices.get(vertexFrom).add(vertexTo);
		vertices.get(vertexTo).add(vertexFrom);
	}
}