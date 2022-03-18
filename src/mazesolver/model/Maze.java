package mazesolver.model;

import java.awt.Point;

public class Maze {
	
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

	public void createBorder(boolean blocked) {
		for (int x = 0; x < getColumns(); x++) {
			for (int y = 0; y < getRows(); y++) {
				getTiles()[y] = new boolean[getRows()];
			}
		}
	}
	
	public void setTile(int column, int row, boolean blocked) {
		getTiles()[column][row] = blocked;
	}
	
	public boolean contains(int x, int y) {
		return contains(new Point(x, y));
	}

	public boolean contains(Point point) {
		return point.x >= 0 && point.x < getColumns() && point.y >= 0 && point.y < getRows();
	}
	
	public void addBorders() {
		for (int x = 0; x < getColumns(); x++) {
			for (int y = 0; y < getRows(); y++) {
				if(x == 0 || y == 0 || x == getColumns()-1 || y == getRows()-1) {
					setTile(x, y, true);
				}
			}
		}
	}
}