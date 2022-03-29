package mazesolver.controller.strategies;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import mazesolver.model.Maze;

public class RecursiveDivisionExcavationStrategy extends AbstractExcavationStrategy {
	
	private ArrayList<Rectangle> areasToDivide = new ArrayList<Rectangle>();
	private ArrayList<Point> tilesToCheckDummy = new ArrayList<Point>();
	@Override
	public List<Point> getTilesToCheck() {
		return tilesToCheckDummy;
	}

	public RecursiveDivisionExcavationStrategy(Maze mazeToExcavate) {
		super(mazeToExcavate);
		if(getMaze().getColumns()%2 != 1 || getMaze().getRows()%2 != 1) {
			throw new IllegalArgumentException("RecursiveDivisionExcavationStrategy works with mazes with odd number of rows and columns.");
		}
		areasToDivide.add(new Rectangle(1,1,getMaze().getColumns()-2, getMaze().getRows()-2));
	}
		
	@Override
	public void excavateNext() {			
		Rectangle areaToSplit = areasToDivide.remove(0);
		System.out.println(	"area to split: " + areaToSplit);
		if(areaToSplit.width >= 3) {
			int x = areaToSplit.x + areaToSplit.width/2;
			for(int y = areaToSplit.y; y < areaToSplit.y + areaToSplit.height; y++) {
				getMaze().setTile(new Point(x, y), true);
			}
		}
		
		if(areaToSplit.width >= 7) {
			Rectangle leftHalf = new Rectangle(areaToSplit.x, areaToSplit.y, areaToSplit.width/2, areaToSplit.height);
			areasToDivide.add(leftHalf);
			System.out.println(	"   Added: " + leftHalf);
			Rectangle rightHalf = new Rectangle(areaToSplit.x +  areaToSplit.width/2+1, areaToSplit.y, areaToSplit.width/2, areaToSplit.height);
			areasToDivide.add(rightHalf);
			System.out.println(	"   Added: " + rightHalf);
		}
	}
	
	@Override
	public boolean isDone() {
	
		return areasToDivide.size()== 0;
	}
}