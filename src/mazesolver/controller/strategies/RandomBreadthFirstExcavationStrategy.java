package mazesolver.controller.strategies;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

import mazesolver.controller.MazeTool;
import mazesolver.model.Maze;

public class RandomBreadthFirstExcavationStrategy implements ExcavationStrategyIF {

	private List<Point> tilesToCheck = new ArrayList<Point>();

	@Override
	public List<Point> getTilesToCheck() {
		return tilesToCheck;
	}

	private Point currentPoint;

	@Override
	public Point getCurrentPoint() {
		return currentPoint;
	}

	private Maze maze;
	private Random random = new Random();

	public RandomBreadthFirstExcavationStrategy(Maze mazeToExcavate) {
		this.setMaze(mazeToExcavate);
		currentPoint = maze.getStartingPoint();
		tilesToCheck.add(currentPoint);
	}

	private void setMaze(Maze mazeToExcavate) {
		this.maze = mazeToExcavate;
	}

	@Override
	public void excavateNext() {

		if (isValidForExcavation(currentPoint)) {
			maze.setTile(currentPoint, false);

			List<Point> validNeighbors = MazeTool.getValidNeighbors(maze, currentPoint);
			validNeighbors.remove(maze.getEndPoint());
			for (Point neighPoint : validNeighbors) {
				if (!tilesToCheck.contains(neighPoint)) {
					tilesToCheck.add(neighPoint);
				}
			}
		}
		currentPoint = tilesToCheck.remove(random.nextInt(tilesToCheck.size()));
	}

	private boolean isValidForExcavation(Point tileToCheck) {
		return  maze.getTiles()[tileToCheck.x][tileToCheck.y] && MazeTool.numberOfFilled(maze.getTiles(), MazeTool.get4NeighborsNSEW(tileToCheck)) >= 3
				&& !MazeTool.hasUnconnectedOpenDiagonalNeighbor(maze, tileToCheck)
		&& !(tileToCheck.x % 2 ==0 && tileToCheck.y% 2 ==0) 
		&& maze.getTiles()[tileToCheck.x][tileToCheck.y];
	}

	@Override
	public boolean isDone() {
		return tilesToCheck.size() == 0;
	}
}
