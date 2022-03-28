package mazesolver.controller;

import java.awt.Point;
import java.util.List;
import java.util.Random;
import java.util.Stack;

import mazesolver.model.Maze;

public class DepthFirstExcavationStrategy implements ExcavationStrategyIF {
	
	private Stack<Point> tilesToCheck = new Stack<Point>();
	@Override
	public Stack<Point> getTilesToCheck() {
		return tilesToCheck;
	}

	private Point currentPoint;
	@Override
	public Point getCurrentPoint() {return currentPoint;}
	private Maze maze;
	private Random random = new Random();
	
	public DepthFirstExcavationStrategy(Maze mazeToExcavate) {
		this.setMaze(mazeToExcavate);
		currentPoint = maze.getStartingPoint();
		tilesToCheck.push(currentPoint);
	}
	

	private void setMaze(Maze mazeToExcavate) {
		this.maze = mazeToExcavate;
	}
	
	@Override
	public void excavateNext() {		
		
		maze.setTile(currentPoint, false);
		
		List<Point> validNeighbors = MazeTool.getValidNeighbors(maze, currentPoint);
			
		if(validNeighbors.size() > 0 && !currentPoint.equals(maze.getEndPoint())) {
			tilesToCheck.push(currentPoint);
			currentPoint = validNeighbors.get(random.nextInt(validNeighbors.size()));
		}
		else {
			currentPoint = tilesToCheck.pop();
		}
	}
	
	@Override
	public boolean isDone() {
		return tilesToCheck.size() == 0;
	}


}
