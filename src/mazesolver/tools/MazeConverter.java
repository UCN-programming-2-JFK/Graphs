package mazesolver.tools;

import java.awt.Point;
import java.util.List;

import graph.generic.list.*;
import graph.generic.matrix.GenericUndirectedWeightedMatrixGraph;
import graph.Vertex;
import graph.generic.GenericGraphIF;
import graph.generic.GenericWeightedGraphIF;
import mazesolver.controller.MazeTool;
import mazesolver.model.Maze;

public class MazeConverter {

	private MazeConverter() {
	}

	public static GenericGraphIF<Point> mazeToGraph(Maze mazeToConvert) {

		GenericGraphIF<Point> mazeGraph = new GenericUndirectedListGraph<Point>();
		for (int x = 1; x < mazeToConvert.getColumns() - 1; x++) {
			for (int y = 1; y < mazeToConvert.getRows() - 1; y++) {
				if (MazeTool.hasPerpendicularConnection(mazeToConvert, x, y)
						|| MazeTool.isDeadEnd(mazeToConvert, new Point(x, y))) {
					mazeGraph.addVertex(new Point(x, y));
				}
			}
		}
		addEdges(mazeToConvert, mazeGraph);
		return mazeGraph;
	}

	private static void addEdges(Maze maze, GenericGraphIF<Point> graph) {

		List<Point> allVerticesList = graph.getAllVertices();

		for (int verticeCounter = 0; verticeCounter < allVerticesList.size() - 1; verticeCounter++) {

			for (int candidateVerticeCounter = verticeCounter + 1; candidateVerticeCounter < allVerticesList
					.size(); candidateVerticeCounter++) {
				Point pointFrom = allVerticesList.get(verticeCounter);
				Point pointTo = allVerticesList.get(candidateVerticeCounter);

				if (MazeTool.hasConnectingLine(maze, pointFrom, pointTo)) {
					graph.addEdge(pointFrom, pointTo);
				}
			}

		}
	}

	public static GenericWeightedGraphIF<Point> mazeToWeightedGraph(Maze maze) {
		GenericGraphIF<Point> unweightedMazeGraph = mazeToGraph(maze);
		GenericWeightedGraphIF<Point> weightedMazeGraph = new GenericUndirectedWeightedMatrixGraph<Point>();
		for (Point p : unweightedMazeGraph.getAllVertices()) {
			weightedMazeGraph.addVertex(p);
		}
		addWeightedEdges(maze, weightedMazeGraph);

		return weightedMazeGraph;
	}

	private static void addWeightedEdges(Maze maze, GenericWeightedGraphIF<Point> graph) {

		List<Point> allVerticesList = graph.getAllVertices();

		for (int verticeCounter = 0; verticeCounter < allVerticesList.size() - 1; verticeCounter++) {

			for (int candidateVerticeCounter = verticeCounter + 1; candidateVerticeCounter < allVerticesList
					.size(); candidateVerticeCounter++) {
				Point pointFrom = allVerticesList.get(verticeCounter);
				Point pointTo = allVerticesList.get(candidateVerticeCounter);

				if (MazeTool.hasConnectingLine(maze, pointFrom, pointTo) && !anyVertexExistsIn(MazeTool.getTilesBetween(maze, pointFrom, pointTo), graph)) {
					int weight = (int) Math.max(Math.abs(pointFrom.x - pointTo.x), Math.abs(pointFrom.y - pointTo.y));
					graph.addEdge(pointFrom, pointTo, weight);
					System.out.println("Adding edge from (" + pointFrom.x + ", " + pointFrom.y + ") to (" + pointTo.x + ", " + pointTo.y + ")");
//					graph.addEdge(pointFrom, pointTo);
				}
			}

		}
	}

	private static boolean anyVertexExistsIn(List<Point> tilesBetween, GenericWeightedGraphIF<Point> graph) {
		for(Point vertexToCheck : graph.getAllVertices()) {
			for(Point pointToCheck : tilesBetween) {
				if(vertexToCheck.equals(pointToCheck)) {return true;}
			}
		}
		return false;
	}
}