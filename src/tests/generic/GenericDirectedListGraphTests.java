package tests.generic;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import graph.generic.list.GenericDirectedListGraph;
import graph.list.DirectedListGraph;
import graph.generic.GenericGraphIF;
import graph.generic.GenericGraphTraverser;
import graph.tools.GraphTraverser;

class GenericDirectedListGraphTests {
	
	private static GenericGraphIF<City> graph;

	private static final City aalborgVertex = new City("Aalborg");
	private static final City aarhusVertex = new City("Aarhus");
	private static final City herningVertex = new City("Herning");
	private static final City ringkøbingVertex = new City("Ringkøbing");
	private static final City vejleVertex = new City("Vejle");
	private static final City odenseVertex = new City("Odense");
	private static final City københavnVertex = new City("København");

	private static final List<City> verticesList = new ArrayList<City>(
			Arrays.asList(aalborgVertex, aarhusVertex, herningVertex, ringkøbingVertex, vejleVertex, odenseVertex, københavnVertex));

	@BeforeAll
	static void createGraph() {
		
		graph = new GenericDirectedListGraph<City>();
		
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
		for(City vertex : verticesList) {
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
		City beginningVertex = aalborgVertex;
		List<City> allVerticesInGraph = GenericGraphTraverser.<City>getDepthFirstRepresentation(graph, beginningVertex);
		assertEquals(verticesList.size()-1, allVerticesInGraph.size());
		System.out.println("depth first from " + beginningVertex);
		for(City vertex :allVerticesInGraph) {
			System.out.println(vertex);
		}
		System.out.println();
	}
	
	
	@Test
	void testDepthFirstSearch2() {
		City beginningVertex = ringkøbingVertex;
		List<City> allVerticesInGraph = GenericGraphTraverser.<City>getDepthFirstRepresentation(graph, beginningVertex);
		assertEquals(verticesList.size()-1, allVerticesInGraph.size());
		System.out.println("depth first from " + beginningVertex);
		for(City vertex :allVerticesInGraph) {
			System.out.println(vertex);
		}
		System.out.println();
	}

	@Test
	void testBreadthFirstSearch() {
		City beginningVertex = aalborgVertex;
		List<City> allVerticesInGraph = GenericGraphTraverser.<City>getBreadthFirstRepresentation(graph, beginningVertex) ;
		assertEquals(verticesList.size()-1, allVerticesInGraph.size());
		System.out.println("breadth first from " + beginningVertex);
		for(City vertex :allVerticesInGraph) {
			System.out.println(vertex);
		}
		System.out.println();
	}
	
	@Test
	void testBreadthFirstSearch2() {
		City beginningVertex = ringkøbingVertex;
		List<City> allVerticesInGraph = GenericGraphTraverser.<City>getBreadthFirstRepresentation(graph,beginningVertex);
		assertEquals(verticesList.size()-1, allVerticesInGraph.size());
		System.out.println("breadth first from " + beginningVertex);
		for(City vertex : allVerticesInGraph) {
			System.out.println(vertex);
		}
		System.out.println();
	}	
}