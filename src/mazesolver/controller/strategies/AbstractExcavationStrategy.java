package mazesolver.controller.strategies;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

import mazesolver.controller.MazeTool;
import mazesolver.model.Maze;

public abstract class AbstractExcavationStrategy implements ExcavationStrategyIF {

	private Random random = new Random();
	private Maze maze;
	private Point currentPoint;

	@Override
	public Point getCurrentPoint() {
		return currentPoint;
	}
	
	protected void setCurrentPoint(Point currentPoint) {
		this.currentPoint = currentPoint;
	}
	
	protected Point getNextPoint() {
		return getTilesToCheck().remove(0);
	}

	public AbstractExcavationStrategy(Maze mazeToExcavate) {
		this.setMaze(mazeToExcavate);
		currentPoint = maze.getStartingPoint();
	}

	private void setMaze(Maze mazeToExcavate) {
		this.maze = mazeToExcavate;
	}
	
	protected Maze getMaze() {
		return this.maze;
	}
	protected Random getRandom() {
		return this.random;
	}

	protected boolean isValidForExcavation(Point tileToCheck) {
//		System.out.println("is valid for excavation? " +  tileToCheck);
		return  maze.getTiles()[tileToCheck.x][tileToCheck.y] && MazeTool.numberOfFilled(maze, MazeTool.get4NeighborsNSEW(tileToCheck)) >= 3
				&& !MazeTool.hasUnconnectedOpenDiagonalNeighbor(maze, tileToCheck)
		&& !(tileToCheck.x % 2 ==0 && tileToCheck.y% 2 ==0) 
		&& maze.getTiles()[tileToCheck.x][tileToCheck.y];
	}

	@Override
	public boolean isDone() {
		return getTilesToCheck().size() == 0;
	}
}