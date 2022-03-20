package mazesolver.controller;

import java.awt.Point;
import java.awt.image.TileObserver;
import java.util.*;

import org.w3c.dom.views.DocumentView;

import mazesolver.model.Maze;

public class MazeTool {

	private final static Random random = new Random();

	private MazeTool() {
	}

	public static Maze createMaze(int width, int height, Point startingPoint, Point endingPoint) {

		Maze maze = new Maze(width, height);
		maze.fillAllTiles(true);
		List<Point> tilesToCheck = new ArrayList<Point>();
		tilesToCheck.add(startingPoint);
		do {
			Point tileToCheck = tilesToCheck.remove(random.nextInt(tilesToCheck.size()));
			System.out.println("Testing " + tilesToCheck);
			if (maze.contains(tileToCheck) && isValidForExcavation(maze.getTiles(), tileToCheck)) {
				maze.setTile(tileToCheck, false);
				List<Point> neighbors = get4NeighborsNSEW(tileToCheck);
				removeBorderTilesAndTilesOutsideMaze(maze.getTiles(), neighbors);
				for (Point neighborTileToCheck : neighbors) {
					if (isValidForExcavation(maze.getTiles(), neighborTileToCheck)) {
						tilesToCheck.add(neighborTileToCheck);
						System.out.println("Adding neighbor: " + neighborTileToCheck);
					}
				}
			}
		} while (tilesToCheck.size() > 0);

		return maze;
	}

	private static boolean isValidForExcavation(boolean[][] tiles, Point tileToCheck) {
		return  tiles[tileToCheck.x][tileToCheck.y] && numberOfFilled(tiles, get4NeighborsNSEW(tileToCheck)) >= 3
				&& (tileToCheck.x % 2 ==1 || tileToCheck.y% 2 ==1) ;
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
}