package mazesolver.controller.strategies;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import mazesolver.controller.MazeTool;
import mazesolver.model.Maze;

public class BreadthFirstExcavationStrategy extends AbstractExcavationStrategy {

	private List<Point> tilesToCheck = new ArrayList<Point>();

	@Override
	public List<Point> getTilesToCheck() {
		return tilesToCheck;
	}

	
	public BreadthFirstExcavationStrategy(Maze mazeToExcavate) {
		super(mazeToExcavate);
		getTilesToCheck().add(getCurrentPoint());
	}

	@Override
	public void excavateNext() {

		if (isValidForExcavation(getCurrentPoint())) {
			getMaze().setTile(getCurrentPoint(), false);

			List<Point> validNeighbors = MazeTool.getValidNeighbors(getMaze(), getCurrentPoint());
			validNeighbors.remove(getMaze().getEndPoint());
			for (Point neighPoint : validNeighbors) {
				if (!getTilesToCheck().contains(neighPoint)) {
					getTilesToCheck().add(neighPoint);
				}
			}
		}
		setCurrentPoint(getNextPoint());
	}
}