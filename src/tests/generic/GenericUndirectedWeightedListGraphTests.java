package tests.generic;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import graph.generic.list.GenericDirectedListGraph;
import graph.generic.matrix.GenericUndirectedWeightedMatrixGraph;
import graph.list.DirectedListGraph;
import graph.generic.GenericGraphIF;
import graph.generic.GenericGraphPathFinder;
import graph.generic.GenericGraphTraverser;
import graph.generic.GenericWeightedGraphIF;
import graph.tools.GraphTraverser;

class GenericUndirectedWeightedListGraphTests {
	
	private static GenericWeightedGraphIF<City> graph;

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
		
		graph = new GenericUndirectedWeightedMatrixGraph<City>();
		
		graph.addVertices(verticesList);
		assertEquals(verticesList.size(), graph.size());
		
		graph.addEdge(aalborgVertex, aarhusVertex, 119);
		graph.addEdge(aalborgVertex, herningVertex, 129);
		graph.addEdge(aarhusVertex, vejleVertex, 72);
		graph.addEdge(aarhusVertex, herningVertex, 86);
		graph.addEdge(ringkøbingVertex, herningVertex, 48);
		graph.addEdge(herningVertex, vejleVertex, 75);
		graph.addEdge(vejleVertex, odenseVertex, 79);
		graph.addEdge(odenseVertex, københavnVertex, 165);
		graph.addEdge(københavnVertex, aarhusVertex, 187);
	}
	
	@Test
	void correctPathFromAalborgToKøbenhavnIsFound() {
		List<City> shortestPath = GenericGraphPathFinder.getShortestPathUsingDijkstra(graph, aalborgVertex, københavnVertex);
		System.out.println();
		System.out.println("Final path:");
		for(City city : shortestPath) {
			System.out.println(" - " + city);
		}
		assertEquals(shortestPath.get(0), aalborgVertex);
		assertEquals(shortestPath.get(1), aarhusVertex);
		assertEquals(shortestPath.get(2), københavnVertex);
		
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
		assertFalse(graph.containsEdge(aarhusVertex, ringkøbingVertex));
		assertFalse(graph.containsEdge(aalborgVertex, københavnVertex));
	}
	
	
}