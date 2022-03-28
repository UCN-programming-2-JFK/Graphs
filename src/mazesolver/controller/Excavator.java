package mazesolver.controller;

import java.awt.Point;
import java.util.*;

import mazesolver.model.Maze;

public class Excavator {

	private Maze maze;
	public ExcavationStrategyIF strategy;

	public Maze getMaze() {return maze;}
	public void setMaze(Maze maze) {this.maze = maze;}

	public Point getCurrentPoint() {
		return getStrategy().getCurrentPoint();
	}

	public boolean isDone() {return getStrategy().isDone();}
	
	public List<Point> getTilesToCheck() {return getStrategy().getTilesToCheck();}
	
	public ExcavationStrategyIF getStrategy() {return this.strategy;}
	public void setStrategy(ExcavationStrategyIF strategy) {this.strategy = strategy;}

	public Excavator(int width, int height, Point startingPoint, Point endPoint) {
		setMaze(new Maze(width, height, startingPoint, endPoint));
		maze.fillAllTiles(true);
		setStrategy(new DepthFirstExcavationStrategy(maze));
	}
	
	public void excavateNext() {getStrategy().excavateNext();}
}