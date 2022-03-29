package mazesolver.controller;

import java.awt.Point;
import java.util.*;

import mazesolver.controller.strategies.BreadthFirstExcavationStrategy;
import mazesolver.controller.strategies.DepthFirstExcavationStrategy;
import mazesolver.controller.strategies.ExcavationStrategyIF;
import mazesolver.controller.strategies.RandomNextPointBreadthFirstExcavationStrategy;
import mazesolver.model.Maze;

public class Excavator implements ExcavatorIF {

	private Maze maze;
	private ExcavationStrategyIF strategy;

	@Override
	public Maze getMaze() {return maze;}

	@Override
	public Point getCurrentPoint() {
		return getStrategy().getCurrentPoint();
	}

	@Override
	public boolean isDone() {return getStrategy().isDone();}
	
	@Override
	public List<Point> getTilesToCheck() {return getStrategy().getTilesToCheck();}
	
	public ExcavationStrategyIF getStrategy() {return this.strategy;}
	public void setStrategy(ExcavationStrategyIF strategy) {this.strategy = strategy;}

	public Excavator(int width, int height, Point startingPoint, Point endPoint) {
		this (new Maze(width, height, startingPoint, endPoint));
		
	}
	
	public Excavator(Maze maze) {
		this(maze, new DepthFirstExcavationStrategy(maze));
	}
	
	public Excavator(Maze maze, ExcavationStrategyIF strategy) {
		this.maze = maze;
		setStrategy(strategy);
	}

	@Override
	public void excavateNext() {getStrategy().excavateNext();}
}