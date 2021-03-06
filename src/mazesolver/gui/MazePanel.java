package mazesolver.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import mazesolver.controller.MazeTool;
import mazesolver.model.Maze;

public class MazePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private Point lastMousePosition = new Point();
	private Point lastMouseTilePosition = new Point();
	private int tileSize;
	private Maze maze;
	private ExternalPainterIF externalPainter = null;

	public ExternalPainterIF getExternalPainter() {return externalPainter;}

	public void setExternalPainter(ExternalPainterIF externalPainter) {this.externalPainter = externalPainter;}

	public MazePanel(int columns, int rows, int tileSizeInPixels) {
		setTileSize(tileSizeInPixels);
		setMaze(new Maze(columns, rows));
		MouseAdapter adapter = getMouseAdapter();
		addMouseListener(adapter);
		addMouseMotionListener(adapter);
		setPreferredSize(new Dimension(getMaze().getColumns() * getTileSize(), getMaze().getRows() * getTileSize()));
	}

	@Override
	public void paint(Graphics g) {
		paintMaze(g);
		drawCursor(g);
		paintStartingAndEndingPoints(g);
		if(getExternalPainter() != null) {
			getExternalPainter().addPaint(g);
		}
	}

	private void paintStartingAndEndingPoints(Graphics g) {
		if(maze.getStartingPoint() != null) {
			g.setColor(Color.green);
			g.fillRect(maze.getStartingPoint().x * getTileSize(), maze.getStartingPoint().y * getTileSize(), getTileSize(), getTileSize());
		}
		if(maze.getEndPoint() != null) {
			g.setColor(Color.red);
			g.fillRect(maze.getEndPoint().x * getTileSize(), maze.getEndPoint().y * getTileSize(), getTileSize(), getTileSize());
		}
	}

	private void paintMaze(Graphics g) {
		Maze maze = getMaze();
		for (int y = 0; y < maze.getRows(); y++) {
			for (int x = 0; x < maze.getColumns(); x++) {
				g.setColor(maze.getTiles()[x][y] ? Color.black : Color.white);
				g.fillRect(x * getTileSize(), y * getTileSize(), getTileSize(), getTileSize());
			}
		}
		
	}

	private void drawCursor(Graphics g) {
		g.setColor(Color.red);
		g.drawRect(lastMouseTilePosition.x * getTileSize(), lastMouseTilePosition.y * getTileSize(), getTileSize() - 1,
				getTileSize() - 1);
	}

	public int getTileSize() {
		return tileSize;
	}

	public void setTileSize(int tileSize) {
		this.tileSize = tileSize;
	}

	public Maze getMaze() {
		return maze;
	}

	public void setMaze(Maze maze) {
		this.maze = maze;
	}

	private void updateMousePosition(MouseEvent e) {
		lastMousePosition = e.getPoint();
		Point possibleTilePosition = getTilePositionFromLastMousePosition();
		if (getMaze().contains(possibleTilePosition)) {
			lastMouseTilePosition = possibleTilePosition;
		}
		repaint();
	}

	private Point getTilePositionFromLastMousePosition() {
		int mouseColumn = lastMousePosition.x / getTileSize();
		int mouseRow = lastMousePosition.y / getTileSize();
		return new Point(mouseColumn, mouseRow);
	}
	
	private void updateTileBasedOnMousePress(MouseEvent e) {
		updateMousePosition(e);
		if (SwingUtilities.isLeftMouseButton(e)) {
			if(e.getModifiersEx() == InputEvent.CTRL_DOWN_MASK) {
					getMaze().setStartingPoint(lastMouseTilePosition);
					getMaze().getTiles()[lastMouseTilePosition.x][lastMouseTilePosition.y] = false;
				}
			else if(e.getModifiersEx() == InputEvent.SHIFT_DOWN_MASK) {
				getMaze().setEndPoint(lastMouseTilePosition);
				getMaze().getTiles()[lastMouseTilePosition.x][lastMouseTilePosition.y] = false;
			}
			else	{
				getMaze().getTiles()[lastMouseTilePosition.x][lastMouseTilePosition.y] = true;	
			}
		} else if (SwingUtilities.isRightMouseButton(e)) {
			getMaze().getTiles()[lastMouseTilePosition.x][lastMouseTilePosition.y] = false;
		}
		repaint();
	}
	
	private MouseAdapter getMouseAdapter() {
		return new MouseAdapter() {
			
			@Override
			public void mousePressed(MouseEvent e) {
				updateMousePosition(e);
			}
			
			public void mouseClicked(MouseEvent e) {
				updateTileBasedOnMousePress(e);							
			}
			
			@Override
			public void mouseMoved(MouseEvent e) {
				updateMousePosition(e);
			}

			@Override
			public void mouseDragged(MouseEvent e) {
				updateTileBasedOnMousePress(e);
			}
		};
	}
}