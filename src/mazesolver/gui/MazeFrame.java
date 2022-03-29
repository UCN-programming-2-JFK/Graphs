package mazesolver.gui;

import java.awt.*;
import java.awt.event.*;

import javax.management.loading.PrivateClassLoader;
import javax.swing.*;

import org.junit.runners.model.FrameworkField;
import org.junit.validator.PublicClassValidator;

import mazesolver.controller.Excavator;
import mazesolver.controller.ExcavatorFactory;
import mazesolver.controller.ExcavatorIF;
import mazesolver.controller.MazeTool;
import mazesolver.model.Maze;

public class MazeFrame extends JFrame implements ExternalPainterIF {

	private static final long serialVersionUID = 1L;
	private static MazePanel panel;
	private static boolean done = false;
	private static int columns, rows, tileSizeInPixels = 32;
	private static ExcavatorIF excavator;
	private static int msSleep = 20;

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
		columns = rows = 41;
		Runnable runner = new Runnable() {
			public void run() {
				MazeFrame window = new MazeFrame();
				window.setTitle("Maze visualizer");
				//window.setUndecorated(true);
				window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
				Rectangle r = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
				window.setSize(r.width, r.height);
				window.pack();
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
		;
		while (true) {
			Maze maze = new Maze(columns, rows, new Point(1, 1), new Point(columns - 2, rows - 2));
			//excavator = ExcavatorFactory.getExcavatorWithRandomStrategy(maze);
			excavator = ExcavatorFactory.getDepthFirstExcavator(maze); 
			// excavator = ExcavatorFactory.getBreadthFirstExcavator(maze);
			// excavator = ExcavatorFactory.getRandomNextPointBreadthFirstExcavationStrategy(maze);
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
				Thread.sleep(3000);
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
			for (Point toDoPoint : excavator.getTilesToCheck()) {
				drawTile(g, toDoPoint, Color.gray);
			}
		}
	}

	public void drawTile(Graphics g, Point pointToDraw, Color colorToUse) {
		g.setColor(colorToUse);
		g.fillRect(pointToDraw.x * tileSizeInPixels, pointToDraw.y * tileSizeInPixels, tileSizeInPixels,
				tileSizeInPixels);
	}
}