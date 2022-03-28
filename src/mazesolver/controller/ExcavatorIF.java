package mazesolver.controller;

import java.awt.Point;
import java.util.List;

import mazesolver.controller.strategies.ExcavationStrategyIF;
import mazesolver.model.Maze;

public interface ExcavatorIF {

	Maze getMaze();

	Point getCurrentPoint();

	boolean isDone();

	List<Point> getTilesToCheck();

	void excavateNext();
	
	void setStrategy(ExcavationStrategyIF strategy);

}