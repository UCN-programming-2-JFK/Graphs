package mazesolver.controller;

import java.awt.Point;
import java.util.*;

import mazesolver.controller.strategies.DepthFirstExcavationStrategy;
import mazesolver.controller.strategies.ExcavationStrategyIF;
import mazesolver.model.Maze;

public class MazeTool {

	public static enum Direction{UP , RIGHT , DOWN , LEFT };
	public static final Point UpDeltas = new Point(0,-1),RightDeltas = new Point(1,0), DownDeltas = new Point(0,1), LeftDeltas = new Point(-1,0); 
	private MazeTool () {}
	
	public static List<Point> getValidNeighbors(Maze maze, Point tileToCheck) {
		List<Point> neighbors = get4NeighborsNSEW(tileToCheck);
		removeBorderTilesAndTilesOutsideMaze(maze.getTiles(), neighbors);
		for(int neighBorIndex = neighbors.size()-1; neighBorIndex>= 0 ; neighBorIndex--) {
			Point neighborToTest= neighbors.get(neighBorIndex);
			if(!isValidForExcavation(maze,neighborToTest))	 {neighbors.remove(neighBorIndex);}
			
		}
		return neighbors;
	}
	
//	public static boolean isValidForExcavation(Maze maze, Point tileToCheck) {
//		return  maze.getTiles()[tileToCheck.x][tileToCheck.y] && numberOfFilled(maze.getTiles(), get4NeighborsNSEW(tileToCheck)) >= 3
//				&& !(tileToCheck.x % 2 ==0 && tileToCheck.y% 2 ==0) 
//				&& maze.getTiles()[tileToCheck.x][tileToCheck.y];
//	}
//	
	public static boolean isValidForExcavation(Maze maze, Point tileToCheck) {
		return  maze.getTiles()[tileToCheck.x][tileToCheck.y] && MazeTool.numberOfFilled(maze.getTiles(), MazeTool.get4NeighborsNSEW(tileToCheck)) >= 3
				&& !MazeTool.hasUnconnectedOpenDiagonalNeighbor(maze, tileToCheck)
		&& !(tileToCheck.x % 2 ==0 && tileToCheck.y% 2 ==0) 
		&& maze.getTiles()[tileToCheck.x][tileToCheck.y];
	}
	
	public static void removeBorderTilesAndTilesOutsideMaze(boolean[][] tiles, List<Point> neighbors) {
		for (int tileIndex = neighbors.size() - 1; tileIndex >= 0; tileIndex--) {
			if (isOutside(tiles, neighbors.get(tileIndex)) || isBorderTile(tiles, neighbors.get(tileIndex))) {
				neighbors.remove(tileIndex);
			}
		}
	}
	
	

	public static boolean isBorderTile(boolean[][] tiles, Point pointToCheck) {

		return (pointToCheck.x == 0 || pointToCheck.y == 0 || pointToCheck.x == tiles.length - 1
				|| pointToCheck.y == tiles[0].length - 1);
	}
	
	public static boolean isOutside(boolean[][] tiles, Point pointToCheck) {

		return (pointToCheck.x < 0 || pointToCheck.y < 0 || pointToCheck.x >= tiles.length 
				|| pointToCheck.y >= tiles[0].length );
	}

	public static List<Point> get4NeighborsNSEW(Point pointToGetNeighborsFor) {
		List<Point> neighbors = new ArrayList<>();
		neighbors.add(new Point(pointToGetNeighborsFor.x, pointToGetNeighborsFor.y - 1));
		neighbors.add(new Point(pointToGetNeighborsFor.x + 1, pointToGetNeighborsFor.y));
		neighbors.add(new Point(pointToGetNeighborsFor.x, pointToGetNeighborsFor.y + 1));
		neighbors.add(new Point(pointToGetNeighborsFor.x - 1, pointToGetNeighborsFor.y));
		return neighbors;
	}

	public static List<Point> get4DiagonalNeighbors(Point pointToGetNeighborsFor) {
		List<Point> diagonalNeighbors = new ArrayList<Point>();
		diagonalNeighbors.add(new Point(pointToGetNeighborsFor.x - 1, pointToGetNeighborsFor.y - 1));
		diagonalNeighbors.add(new Point(pointToGetNeighborsFor.x + 1, pointToGetNeighborsFor.y + 1));
		diagonalNeighbors.add(new Point(pointToGetNeighborsFor.x - 1, pointToGetNeighborsFor.y + 1));
		diagonalNeighbors.add(new Point(pointToGetNeighborsFor.x + 1, pointToGetNeighborsFor.y - 1));
		System.out.println(diagonalNeighbors.size() + " number of diagonal neighbors");
		return diagonalNeighbors;
	}
	
	public static List<Point> get8Neighbors(Point pointToGetNeighborsFor) {
		List<Point> neighbors = get4NeighborsNSEW(pointToGetNeighborsFor);
		neighbors.add(new Point(pointToGetNeighborsFor.x - 1, pointToGetNeighborsFor.y - 1));
		neighbors.add(new Point(pointToGetNeighborsFor.x + 1, pointToGetNeighborsFor.y + 1));
		neighbors.add(new Point(pointToGetNeighborsFor.x - 1, pointToGetNeighborsFor.y + 1));
		neighbors.add(new Point(pointToGetNeighborsFor.x + 1, pointToGetNeighborsFor.y - 1));
		return neighbors;
	}

	public static boolean areFilled(boolean[][] tiles, List<Point> tilesToCheck) {
		for (Point tile : tilesToCheck) {
			if (!tiles[tile.x][tile.y]) {
				return false;
			}
		}
		return true;
	}

	public static int numberOfFilled(boolean[][] tiles, List<Point> tilesToCheck) {
		int filled = 0;
		for (Point tile : tilesToCheck) {
			if (tiles[tile.x][tile.y]) {
				filled++;
			}
		}
		return filled;
	}
	
	public static List<Point> getLeftRightAndForward(Maze maze, Point currentTile){
		if(!maze.getTiles()[currentTile.x + UpDeltas.x][currentTile.y + UpDeltas.y]) {return getLeftRightAndForward(Direction.DOWN);}
		else if(!maze.getTiles()[currentTile.x + RightDeltas.x][currentTile.y + RightDeltas.y]) {return getLeftRightAndForward(Direction.LEFT);}
		else if(!maze.getTiles()[currentTile.x + DownDeltas.x][currentTile.y + DownDeltas.y]) {return getLeftRightAndForward(Direction.UP);}
		else {return getLeftRightAndForward(Direction.RIGHT);}
	}
	
	public static List<Point> getLeftRightAndForward(Direction direction){
		if(direction == Direction.UP) {return new ArrayList<Point>(Arrays.asList(LeftDeltas, UpDeltas, RightDeltas));}
		else if(direction == Direction.RIGHT) {return new ArrayList<Point>(Arrays.asList(UpDeltas, RightDeltas, DownDeltas));}
		else if(direction == Direction.DOWN) {return new ArrayList<Point>(Arrays.asList(RightDeltas, DownDeltas, LeftDeltas));}
		else {return new ArrayList<Point>(Arrays.asList(DownDeltas, LeftDeltas, UpDeltas));}
	}
	
	public static List<Point> getAllDirectionDeltas(){
		return new ArrayList<Point>(Arrays.asList(UpDeltas, RightDeltas, DownDeltas, LeftDeltas));
	}
	
	public static boolean hasUnconnectedOpenDiagonalNeighbor(Maze maze, Point tileToCheck) {
		List<Point> diagonalNeighbors = get4DiagonalNeighbors(tileToCheck);
		for(Point neighbor : diagonalNeighbors) {
			 if(!maze.getTiles()[neighbor.x][neighbor.y] &&
					 !(!maze.getTiles()[tileToCheck.x][neighbor.y] ||
					  !maze.getTiles()[neighbor.x][tileToCheck.y])) {
				return true;
			 }
		}
		return false;
	}
	
}