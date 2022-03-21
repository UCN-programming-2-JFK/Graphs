package mazesolver.controller;

import java.awt.Point;
import java.util.*;


import mazesolver.model.Maze;

public class MazeTool {

	private final static Random random = new Random();

	public static enum Direction{UP , RIGHT , DOWN , LEFT };
	public static final Point UpDeltas = new Point(0,-1),RightDeltas = new Point(1,0), DownDeltas = new Point(0,1), LeftDeltas = new Point(-1,0); 
	
	private Maze maze;
	private Stack<Point> tilesToCheck = new Stack<Point>(); 
	public Maze getMaze() {return maze;}
	public void setMaze(Maze maze) {this.maze = maze;}
	private Point currentPoint;
	public Point getCurrentPoint() {return currentPoint;}
	
	public MazeTool (int width, int height, Point startingPoint, Point endPoint) {
		setMaze(new Maze(width, height, startingPoint, endPoint));
		maze.fillAllTiles(true);
		currentPoint = startingPoint;
		tilesToCheck.push(currentPoint);
	}
	
	public void excavateNextd() {
		//Point tileToCheck = tilesToCheck.remove(random.nextInt(tilesToCheck.size()));
		//Point tileToCheck = tilesToCheck.remove(tilesToCheck.size()-1);
		System.out.println("Testing " + tilesToCheck);
		
		//excavateIfPossible(tilesToCheck, maze, tileToCheck);
	}
	
	public void excavateNext() {		
		
		maze.setTile(currentPoint, false);
		
		List<Point> validNeighbors = getValidNeighbors(currentPoint);
			
		if(validNeighbors.size() > 0 && !currentPoint.equals(getMaze().getEndPoint())) {
			tilesToCheck.push(currentPoint);
			currentPoint = validNeighbors.get(random.nextInt(validNeighbors.size()));
		}
		else {
			currentPoint = tilesToCheck.pop();
		}
		
		
//		if (isValidForExcavation(maze.getTiles(), tileToCheck)) {
//			maze.setTile(tileToCheck, false);
//			List<Point> deltas = getLeftRightAndForward(maze.getTiles(), tileToCheck);
//			List<Point> neighbors = new ArrayList<Point>();
//			for(Point delta : deltas) {
//				neighbors.add(new Point(tileToCheck.x + delta.x, tileToCheck.y + delta.y));
//			}
//			
//			for (Point neighborTileToCheck : neighbors) {
//				if (isValidForExcavation(maze.getTiles(), neighborTileToCheck)) {
//					tilesToCheck.add(neighborTileToCheck);
//					System.out.println("Adding neighbor: " + neighborTileToCheck);
//				}
//			}
//		}
	}
	
	private List<Point> getValidNeighbors(Point tileToCheck) {
		List<Point> neighbors = get4NeighborsNSEW(tileToCheck);
		removeBorderTilesAndTilesOutsideMaze(maze.getTiles(), neighbors);
		for(int neighBorIndex = neighbors.size()-1; neighBorIndex>= 0 ; neighBorIndex--) {
			Point neighborToTest= neighbors.get(neighBorIndex);
			if(!isValidForExcavation(neighborToTest))	 {neighbors.remove(neighBorIndex);}
			
		}
		return neighbors;
	}
	public boolean isDone() {
		return tilesToCheck.size() == 0;
	}
	
//	public static Maze createMaze(int width, int height, Point startingPoint, Point endingPoint) {
//
//		Maze maze = new Maze(width, height);
//		maze.fillAllTiles(true);
//		List<Point> tilesToCheck = new ArrayList<Point>();
//		tilesToCheck.add(startingPoint);
//		do {
//			//Point tileToCheck = tilesToCheck.remove(random.nextInt(tilesToCheck.size()));
//			Point tileToCheck = tilesToCheck.remove(tilesToCheck.size()-1);
//			System.out.println("Testing " + tilesToCheck);
//			excavateIfPossible(tilesToCheck, maze, tileToCheck);
//		} while (tilesToCheck.size() > 0);
//
//		return maze;
//	}

	private boolean isValidForExcavation( Point tileToCheck) {
		return  maze.getTiles()[tileToCheck.x][tileToCheck.y] && numberOfFilled(maze.getTiles(), get4NeighborsNSEW(tileToCheck)) >= 3
				&& !(tileToCheck.x % 2 ==0 && tileToCheck.y% 2 ==0) 
				&& maze.getTiles()[tileToCheck.x][tileToCheck.y];
	}

	private static void removeBorderTilesAndTilesOutsideMaze(boolean[][] tiles, List<Point> neighbors) {
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
	
	public static List<Point> getLeftRightAndForward(boolean[][] tiles, Point currentTile){
		if(!tiles[currentTile.x + UpDeltas.x][currentTile.y + UpDeltas.y]) {return getLeftRightAndForward(Direction.DOWN);}
		else if(!tiles[currentTile.x + RightDeltas.x][currentTile.y + RightDeltas.y]) {return getLeftRightAndForward(Direction.LEFT);}
		else if(!tiles[currentTile.x + DownDeltas.x][currentTile.y + DownDeltas.y]) {return getLeftRightAndForward(Direction.UP);}
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
}