package mazesolver.model;

import java.awt.Point;
import java.util.Iterator;

import mazesolver.controller.MazeTool;

public class Maze implements Iterable<Point> {

	private int columns, rows;
	private boolean[][] tiles;
	private Point startingPoint = null, endPoint = null;

	public Maze(int columns, int rows) {
		this.columns = columns;
		this.rows = rows;
		createTiles();
		addBorders();
	}
	
	public Maze(int columns, int rows, Point startingPoint, Point endPoint) {
		this(columns, rows);
		this.setStartingPoint(startingPoint);
		this.setEndPoint(endPoint);
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

	public Point getStartingPoint() {
		return startingPoint;
	}

	public void setStartingPoint
	(Point startingTile) {
		this.startingPoint = startingTile;
	}

	public Point getEndPoint() {
		return endPoint;
	}

	public void setEndPoint(Point goalTile) {
		this.endPoint = goalTile;
	}

	public void setAllTiles(boolean blocked) {
		for (Point tile : this) {
			setTile(tile, blocked);
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