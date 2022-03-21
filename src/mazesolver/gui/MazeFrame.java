package mazesolver.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Point;

import javax.swing.*;

import org.junit.validator.PublicClassValidator;

import mazesolver.controller.MazeTool;

public class MazeFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private static MazePanel panel;
	private static boolean done = false;
	public static void main(String[] args) {
		Runnable runner = new Runnable() {
			public void run() {
				
				MazeFrame window = new MazeFrame();
				window.setTitle("Maze visualizer");
				window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
				window.pack();
				window.setVisible(true);
				done = true;
			}
		};
			
		EventQueue.invokeLater(runner);
		while(!done) {
			System.out.println("not done");
		};
		MazeTool mazeTool = new MazeTool(17,  11,new Point(1,1) , new Point(15,9));
		panel.setMaze(mazeTool.getMaze());
		
		while(!mazeTool.isDone()) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			panel.repaint();
			mazeTool.excavateNext();
		}
	}
	
	public MazeFrame () {
		getRootPane().setLayout(new BorderLayout());
		panel = new MazePanel(17, 11, 50);
		//panel.getMaze().addBorders();
		JScrollPane scrollPane = new JScrollPane(panel);
		getRootPane().add(scrollPane);
	}
}