# Graphs  
This Eclipse project contains assorted implementations (generic and non-generic) of Graphs and algorithms for traversing graphs.

## Unweighted graph implementations
The following classes are implementations of the following interface:  
```java
public interface GraphIF {
    int size();
    void addVertex(Vertex vertexToAdd);
    void addVertices(List<Vertex> verticesToAdd);
    List<Vertex> getAllVertices ();
    void addEdge(Vertex vertexFrom, Vertex vertexTo);
    boolean containsEdge(Vertex vertexFrom, Vertex vertexTo);
    boolean containsVertex(Vertex vertex);
    List<Vertex> getAdjacentVertices(Vertex vertex);
}
```
### Undirected unweighted graphs
- [Undirected graph with adjacency list implementation](https://github.com/UCN-programming-2-JFK/Graphs/blob/master/src/graph/list/UndirectedListGraph.java)
- [Undirected graph with matrix implementation](https://github.com/UCN-programming-2-JFK/Graphs/blob/master/src/graph/matrix/UndirectedMatrixGraph.java)  
### Directed unweighted graphs
- [Directed graph with adjacency list implementation](https://github.com/UCN-programming-2-JFK/Graphs/blob/master/src/graph/list/DirectedListGraph.java)
- [Directed graph with matrix implementation](https://github.com/UCN-programming-2-JFK/Graphs/blob/master/src/graph/matrix/DirectedMatrixGraph.java)

## Generic unweighted graph implementations

The following classes are implementations of the class
```java
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
```
The weighted implementations furthermore implement the following interface
```java
public interface GenericWeightedGraphIF<T> extends GenericGraphIF<T> {	
	void addEdge(T vertexFrom, T vertexTo, int weight);
	public int getEdgeWeight(T vertexFrom, T vertexTo);
}
```


### Undirected unweighted graphs
- [Undirected graph with adjacency list implementation](https://github.com/UCN-programming-2-JFK/Graphs/blob/master/src/graph/generic/list/GenericUndirectedListGraph.java)
- [Undirected weighted graph with matrix implementation](https://github.com/UCN-programming-2-JFK/Graphs/blob/master/src/graph/generic/matrix/GenericUndirectedWeightedMatrixGraph.java)  
### Directed unweighted graphs
- [Directed graph with adjacency list implementation](https://github.com/UCN-programming-2-JFK/Graphs/blob/master/src/graph/generic/list/GenericDirectedListGraph.java)
- [Directed weighted graph with matrix implementation](https://github.com/UCN-programming-2-JFK/Graphs/blob/master/src/graph/generic/matrix/GenericDirectedWeightedMatrixGraph.java)

  Note
