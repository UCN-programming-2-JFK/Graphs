package mazesolver.controller.strategies;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

import mazesolver.controller.MazeTool;
import mazesolver.model.Maze;

public class RandomBreadthFirstExcavationStrategy extends BreadthFirstExcavationStrategy {

	public RandomBreadthFirstExcavationStrategy(Maze mazeToExcavate) {
		super(mazeToExcavate);
	}

	@Override
	protected Point getNextPoint() {
		return getTilesToCheck().remove(getRandom().nextInt(getTilesToCheck().size()));
	}
}
