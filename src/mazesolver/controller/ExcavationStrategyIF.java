package mazesolver.controller;

import java.awt.Point;
import java.util.Stack;

public interface ExcavationStrategyIF {

	Stack<Point> getTilesToCheck();

	Point getCurrentPoint();

	void excavateNext();

	boolean isDone();

}