package mazesolver.controller;

import java.util.Random;

import mazesolver.controller.strategies.BreadthFirstExcavationStrategy;
import mazesolver.controller.strategies.DepthFirstExcavationStrategy;
import mazesolver.controller.strategies.RandomBreadthFirstExcavationStrategy;
import mazesolver.model.Maze;

public class ExcavatorFactory {
	private static Random random = new Random();
	private ExcavatorFactory() {}
	
	public static ExcavatorIF getDepthFirstExcavator(Maze maze) {
		return new Excavator(maze);
	}
	
	public static ExcavatorIF getBreadthFirstExcavator(Maze maze) {
		return new Excavator(maze, new BreadthFirstExcavationStrategy(maze));
	}
	
	public static ExcavatorIF getRandomBreadthFirstExcavator(Maze maze) {
		return new Excavator(maze, new RandomBreadthFirstExcavationStrategy(maze));
	}
	
	public static ExcavatorIF getExcavatorWithRandomStrategy(Maze maze) {

		switch(random.nextInt(3)) {
			case 0 : return new Excavator(maze, new DepthFirstExcavationStrategy(maze));
			case 1 : return new Excavator(maze, new RandomBreadthFirstExcavationStrategy(maze));
			case 2 : return new Excavator(maze, new BreadthFirstExcavationStrategy(maze));
		}
		return null;
		
	}
	
	
}