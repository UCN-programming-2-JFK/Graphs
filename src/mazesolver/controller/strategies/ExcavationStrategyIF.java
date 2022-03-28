package mazesolver.controller.strategies;

import java.awt.Point;
import java.util.List;
import java.util.Stack;

public interface ExcavationStrategyIF {

	List<Point> getTilesToCheck();

	Point getCurrentPoint();

	void excavateNext();

	boolean isDone();

}