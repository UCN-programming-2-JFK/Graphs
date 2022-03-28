package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import graph.GraphIF;
import graph.Vertex;
import graph.list.DirectedListGraph;
import graph.tools.DirectedGraphTraverser;

class DirectedListGraphTests {
	
	private static GraphIF graph;

	private static final Vertex aalborgVertex = new Vertex("Aalborg");
	private static final Vertex aarhusVertex = new Vertex("Aarhus");
	private static final Vertex herningVertex = new Vertex("Herning");
	private static final Vertex ringkøbingVertex = new Vertex("Ringkøbing");
	private static final Vertex vejleVertex = new Vertex("Vejle");
	private static final Vertex odenseVertex = new Vertex("Odense");
	private static final Vertex københavnVertex = new Vertex("København");

	private static final List<Vertex> verticesList = new ArrayList<Vertex>(
			Arrays.asList(aalborgVertex, aarhusVertex, herningVertex, ringkøbingVertex, vejleVertex, odenseVertex, københavnVertex));

	@BeforeAll
	static void createGraph() {
		
		graph = new DirectedListGraph();
		
		graph.addVertices(verticesList);
		assertEquals(verticesList.size(), graph.size());
		
		graph.addEdge(aalborgVertex, aarhusVertex);
		graph.addEdge(aalborgVertex, herningVertex);
		graph.addEdge(aarhusVertex, vejleVertex);
		graph.addEdge(aarhusVertex, herningVertex);
		graph.addEdge(ringkøbingVertex, herningVertex);
		graph.addEdge(herningVertex, vejleVertex);
		graph.addEdge(vejleVertex, odenseVertex);
		graph.addEdge(odenseVertex, københavnVertex);
		graph.addEdge(københavnVertex, aarhusVertex);
	}
	
	@Test
	void testVerticesExist() {
		for(Vertex vertex : verticesList) {
			assertTrue(graph.containsVertex(vertex));
		}
	}
	
	@Test
	void testEdgesExist() {
		assertTrue(graph.containsEdge(aalborgVertex, aarhusVertex));
		assertTrue(graph.containsEdge(herningVertex, vejleVertex));
		assertTrue(graph.containsEdge(odenseVertex, københavnVertex));
	}

	@Test
	void testEdgesDontExist() {
		assertFalse(graph.containsEdge(aarhusVertex, aalborgVertex));
		assertFalse(graph.containsEdge(aalborgVertex, københavnVertex));
	}
	
	@Test
	void testDepthFirstSearch() {
		Vertex beginningVertex = aalborgVertex;
		List<Vertex> allVerticesInGraph = DirectedGraphTraverser.getDepthFirstRepresentation(graph, beginningVertex);
		assertEquals(verticesList.size()-1, allVerticesInGraph.size());
		System.out.println("depth first from " + beginningVertex);
		for(Vertex vertex :allVerticesInGraph) {
			System.out.println(vertex);
		}
		System.out.println();
	}
	
	
	@Test
	void testDepthFirstSearch2() {
		Vertex beginningVertex = ringkøbingVertex;
		List<Vertex> allVerticesInGraph = DirectedGraphTraverser.getDepthFirstRepresentation(graph, beginningVertex);
		assertEquals(verticesList.size()-1, allVerticesInGraph.size());
		System.out.println("depth first from " + beginningVertex);
		for(Vertex vertex :allVerticesInGraph) {
			System.out.println(vertex);
		}
		System.out.println();
	}

	@Test
	void testBreadthFirstSearch() {
		Vertex beginningVertex = aalborgVertex;
		List<Vertex> allVerticesInGraph = DirectedGraphTraverser.getBreadthFirstRepresentation(graph, beginningVertex) ;
		assertEquals(verticesList.size()-1, allVerticesInGraph.size());
		System.out.println("breadth first from " + beginningVertex);
		for(Vertex vertex :allVerticesInGraph) {
			System.out.println(vertex);
		}
		System.out.println();
	}
	
	@Test
	void testBreadthFirstSearch2() {
		Vertex beginningVertex = ringkøbingVertex;
		List<Vertex> allVerticesInGraph = DirectedGraphTraverser.getBreadthFirstRepresentation(graph,beginningVertex);
		assertEquals(verticesList.size()-1, allVerticesInGraph.size());
		System.out.println("breadth first from " + beginningVertex);
		for(Vertex vertex :allVerticesInGraph) {
			System.out.println(vertex);
		}
		System.out.println();
	}

}