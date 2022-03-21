package mazesolver.gui;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import org.junit.jupiter.params.shadow.com.univocity.parsers.common.ColumnMap;
import org.junit.validator.PublicClassValidator;

import mazesolver.controller.MazeTool;

public class MazeFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private static MazePanel panel;
	private static boolean done = false;
	private static int columns = 151, rows = 71, tileSizeInPixels = 12;

	public static void main(String[] args) {

		Dimension size = Toolkit. getDefaultToolkit(). getScreenSize();
		columns = size.width / tileSizeInPixels;
		rows = size.height/ tileSizeInPixels;
		if(columns % 2 == 0) {columns --;}
		if(rows% 2 == 0) {rows--;}
		Runnable runner = new Runnable() {
			public void run() {

				MazeFrame window = new MazeFrame();
				window.setTitle("Maze visualizer");
				window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); 
				window.setUndecorated(true);
				Rectangle r = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
				window.setSize(r.width, r.height);
				window.pack();
				window.setVisible(true);
				
				KeyListener keyAdapter=new KeyAdapter()
				 {
				  public void keyPressed(KeyEvent evt)
				  {
					  //if(evt.getKeyCode()==KeyEvent.VK_ESCAPE)
				   {
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

//		for (int mazeCounter = 0; mazeCounter < 200; mazeCounter++) 
		while(true){
			MazeTool mazeTool = new MazeTool(columns, rows, new Point(1, 1), new Point(columns - 2, rows - 2));
			panel.setMaze(mazeTool.getMaze());

			while (!mazeTool.isDone()) {
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				mazeTool.excavateNext();
				panel.repaint();
			}
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public MazeFrame() {
		getRootPane().setLayout(new BorderLayout());
		panel = new MazePanel(columns, rows, tileSizeInPixels);
		// panel.getMaze().addBorders();
		JScrollPane scrollPane = new JScrollPane(panel);
		getRootPane().add(scrollPane);
	}
}