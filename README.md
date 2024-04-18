# Graphs  
This Eclipse project contains assorted implementations (generic and non-generic) of Graphs and algorithms for traversing graphs.

## Graph implementations
The following four classes are implementations of the following interface:  
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
### Undirected graphs
- [Undirected graph with adjacency list implementation](https://github.com/UCN-programming-2-JFK/Graphs/blob/master/src/graph/list/UndirectedListGraph.java)
- [Directed graph with matrix implementation](https://github.com/UCN-programming-2-JFK/Graphs/blob/master/src/graph/matrix/UndirectedMatrixGraph.java)  
### Directed graphs
- [Directed graph with adjacency list implementation](https://github.com/UCN-programming-2-JFK/Graphs/blob/master/src/graph/list/DirectedListGraph.java)
- [Directed graph with matrix implementation](https://github.com/UCN-programming-2-JFK/Graphs/blob/master/src/graph/matrix/DirectedMatrixGraph.java)
