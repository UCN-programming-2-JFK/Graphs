package mazesolver.model;

import java.awt.Point;
import java.util.Iterator;

import mazesolver.controller.MazeTool;

public class Maze implements Iterable<Point> {

	private int columns, rows;
	private boolean[][] tiles;
	private Point startingTile = null, goalTile = null;

	public Maze(int columns, int rows) {
		this.columns = columns;
		this.rows = rows;
		createTiles();
	}

	private void createTiles() {
		setTiles(new boolean[getColumns()][]);
		for (int x = 0; x < getColumns(); x++) {
			getTiles()[x] = new boolean[getRows()];
		}
	}

	public int getColumns() {
		return columns;
	}

	public int getRows() {
		return rows;
	}

	public boolean[][] getTiles() {
		return tiles;
	}

	public void setTiles(boolean[][] tiles) {
		this.tiles = tiles;
	}

	public Point getStartingTile() {
		return startingTile;
	}

	public void setStartingTile(Point startingTile) {
		this.startingTile = startingTile;
	}

	public Point getGoalTile() {
		return goalTile;
	}

	public void setGoalTile(Point goalTile) {
		this.goalTile = goalTile;
	}

	public void clearAllTiles(boolean blocked) {
		for (Point tile : this) {
			setTile(tile, false);
		}
	}

	public void fillAllTiles(boolean blocked) {
		for (Point tile : this) {
			setTile(tile, true);
		}
	}

	public void setTile(int column, int row, boolean blocked) {
		getTiles()[column][row] = blocked;
	}

	public void setTile(Point tile, boolean blocked) {
		setTile(tile.x, tile.y, blocked);
	}

	public boolean contains(int x, int y) {
		return contains(new Point(x, y));
	}

	public boolean contains(Point point) {
		return point.x >= 0 && point.x < getColumns() && point.y >= 0 && point.y < getRows();
	}

	public void addBorders() {		
		for(Point tile: this) {
			if (MazeTool.isBorderTile(getTiles(), tile)) {
				setTile(tile, true);
			}
		}
	}

	@Override
	public Iterator<Point> iterator() {

		return new Iterator<Point>() {

			private int tileIndex = 0;

			@Override
			public boolean hasNext() {
				return tileIndex < getColumns() * getRows();
			}

			@Override
			public Point next() {
				Point currentPoint = new Point(tileIndex % getColumns(), tileIndex / getColumns());
				tileIndex++;
				return currentPoint;
			}
		};
	}
}