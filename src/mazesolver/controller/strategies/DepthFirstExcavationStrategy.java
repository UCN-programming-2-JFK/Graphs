package mazesolver.controller.strategies;

import java.awt.Point;
import java.util.List;
import java.util.Stack;

import mazesolver.controller.MazeTool;
import mazesolver.model.Maze;

public class DepthFirstExcavationStrategy extends AbstractExcavationStrategy {
	
	private Stack<Point> tilesToCheck = new Stack<Point>();
	
	@Override
	public List<Point> getTilesToCheck() {
		return tilesToCheck;
	}

	public DepthFirstExcavationStrategy(Maze mazeToExcavate) {
		super(mazeToExcavate);
		tilesToCheck.push(getCurrentPoint());
	}
		
	@Override
	public void excavateNext() {		
		
		getMaze().setTile(getCurrentPoint(), false);
		
		List<Point> validNeighbors = MazeTool.getValidNeighbors(getMaze(), getCurrentPoint());
			
		if(validNeighbors.size() > 0 && !getCurrentPoint().equals(getMaze().getEndPoint())) {
			tilesToCheck.push(getCurrentPoint());
			setCurrentPoint(validNeighbors.get(getRandom().nextInt(validNeighbors.size())));
		}
		else
			{
				setCurrentPoint( tilesToCheck.pop());
			}
		
	}
}