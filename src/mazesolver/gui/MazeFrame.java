package mazesolver.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowStateListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;

import graph.generic.GenericGraphIF;
import mazesolver.controller.ExcavatorFactory;
import mazesolver.controller.ExcavatorIF;
import mazesolver.model.Maze;
import mazesolver.tools.MazeConverter;

public class MazeFrame extends JFrame implements ExternalPainterIF {

	private static final long serialVersionUID = 1L;
	private static MazePanel panel;
	private static boolean done = false;
	private static int columns, rows, tileSizeInPixels = 32;
	private static ExcavatorIF excavator;
	private static int msSleep = 100, delayBetweenGeneration = 3000;

	public static void main(String[] args) {

		Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
		columns = size.width / tileSizeInPixels;
		rows = size.height / tileSizeInPixels;
		if (columns % 2 == 0) {
			columns--;
		}
		if (rows % 2 == 0) {
			rows--;
		}
		columns = rows = 19;
		Runnable runner = new Runnable() {
			public void run() {
				MazeFrame window = new MazeFrame();
				window.setTitle("Maze visualizer");
				// window.setExtendedState(JFrame.MAXIMIZED_BOTH);
				// window.setUndecorated(true);
				window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
				Rectangle r = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
				// window.setSize(r.width, r.height);
				window.pack();
				window.setLocationRelativeTo(null);
				window.setVisible(true);
				KeyListener keyAdapter = new KeyAdapter() {
					public void keyPressed(KeyEvent evt) {
						if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
							System.exit(0);
						}
					}
				};
				window.addKeyListener(keyAdapter);
				done = true;
			}
		};

		EventQueue.invokeLater(runner);
		while (!done) {
			System.out.println("not done");
		}
		
		while (true) {
			Maze maze = new Maze(columns, rows, new Point(1, 1), new Point(columns - 2, rows - 2));
			excavator = ExcavatorFactory.getExcavatorWithRandomStrategy(maze);
			 //excavator = ExcavatorFactory.getDepthFirstExcavator(maze);
			// excavator = ExcavatorFactory.getBreadthFirstExcavator(maze);
			// excavator =
			//ExcavatorFactory.getRandomNextPointBreadthFirstExcavationStrategy(maze);
			panel.setMaze(maze);
		
			while (!excavator.isDone()) {
				excavator.excavateNext();
				panel.repaint();
				try {
					Thread.sleep(msSleep);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			try {
				Thread.sleep(delayBetweenGeneration);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public MazeFrame() {
		getRootPane().setLayout(new BorderLayout());
		panel = new MazePanel(columns, rows, tileSizeInPixels);
		panel.setExternalPainter(this);
		JScrollPane scrollPane = new JScrollPane(panel);
		getRootPane().add(scrollPane);
	}

	@Override
	public void addPaint(Graphics g) {

		if (excavator != null) {
			if (!excavator.isDone()) {
				drawTile(g, excavator.getCurrentPoint(), Color.orange);
			}

			// copy tilesToCheck to new arraylist (shallow copy), because painting is done
			// asynchronously
			// and the excavator may be changing the list while we're iterating over it
			for (Point toDoPoint : new ArrayList<Point>(excavator.getTilesToCheck())) {
				drawTile(g, toDoPoint, Color.gray);
			}
		}
		if (excavator.isDone()) {
			GenericGraphIF<Point> graph = MazeConverter.mazeToGraph(panel.getMaze());
			java.util.List<Point> allVertices = graph.getAllVertices();
			g.setColor(Color.blue);
			for (Point pointToDraw : allVertices) {
				int nodeDiameter = (int) (tileSizeInPixels * .3);
				g.fillOval(pointToDraw.x * tileSizeInPixels + tileSizeInPixels / 2 - nodeDiameter / 2,
						pointToDraw.y * tileSizeInPixels + tileSizeInPixels / 2 - nodeDiameter / 2, nodeDiameter,
						nodeDiameter);
				for (Point pointToDrawTo : allVertices) {
					if (graph.containsEdge(pointToDraw, pointToDrawTo)) {
						g.drawLine(pointToDraw.x * tileSizeInPixels + tileSizeInPixels / 2,
								pointToDraw.y * tileSizeInPixels + tileSizeInPixels / 2,
								pointToDrawTo.x * tileSizeInPixels + tileSizeInPixels / 2,
								pointToDrawTo.y * tileSizeInPixels + tileSizeInPixels / 2);
					}
				}
			}
		}
	}

	public void drawTile(Graphics g, Point pointToDraw, Color colorToUse) {
		g.setColor(colorToUse);
		g.fillRect(pointToDraw.x * tileSizeInPixels, pointToDraw.y * tileSizeInPixels, tileSizeInPixels,
				tileSizeInPixels);
	}
}