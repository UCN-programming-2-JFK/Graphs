package mazesolver.tools;

import java.awt.Point;
import java.util.List;


import graph.generic.list.*;
import graph.generic.GenericGraphIF;
import mazesolver.controller.MazeTool;
import mazesolver.model.Maze;

public class MazeConverter {
	
	private MazeConverter() {}
	
	public static GenericGraphIF<Point> mazeToGraph(Maze mazeToConvert){
		
		GenericGraphIF<Point> mazeGraph = new GenericUndirectedListGraph<Point>();
		for(int x = 1; x < mazeToConvert.getColumns()-1; x++) {
			for(int y = 1; y < mazeToConvert.getRows()-1; y++) {
				if(MazeTool.hasPerpendicularConnection(mazeToConvert, x, y) || MazeTool.isDeadEnd(mazeToConvert, new Point(x,y))) {
//					System.out.println("has perpendicular: (" + x+ ", " + y + ")");
					mazeGraph.addVertex(new Point(x,y));
				}
			}	
		}
		addEdges(mazeToConvert, mazeGraph);
		return mazeGraph;
	}
	
	private static void addEdges(Maze maze, GenericGraphIF<Point> graph) {
		
		List<Point> allVerticesList = graph.getAllVertices();
		
		for(int verticeCounter = 0; verticeCounter< allVerticesList.size()-1; verticeCounter++) {

			for(int candidateVerticeCounter = verticeCounter+1; candidateVerticeCounter < allVerticesList.size(); candidateVerticeCounter ++) {
				Point pointFrom = allVerticesList.get(verticeCounter);
				Point pointTo = allVerticesList.get(candidateVerticeCounter);
				
				if(MazeTool.hasConnectingLine(maze, pointFrom, pointTo)) {
					graph.addEdge(pointFrom, pointTo);
				}
			}

		}
	}
}