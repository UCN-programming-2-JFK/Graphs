package mazesolver.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.*;

public class MazeFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		
		Runnable runner = new Runnable() {
			public void run() {
				MazeFrame window = new MazeFrame();
				window.setTitle("Maze visualizer");
				window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
				window.pack();
				window.setVisible(true);
			}
			};
			
			EventQueue.invokeLater(runner);
	}
	
	public MazeFrame () {
		getRootPane().setLayout(new BorderLayout());
		MazePanel panel = new MazePanel(17, 11, 50);
		panel.getMaze().addBorders();
		JScrollPane scrollPane = new JScrollPane(panel);
		getRootPane().add(scrollPane);
	}
}