package tests.generic;

import java.awt.Point;

public class City {
		
		private String name;
		private Point coordinate;
		
		public Point getCoordinate() {return coordinate;}
		public void setCoordinate(Point coordinate) {this.coordinate = coordinate;}
		public String getName() {return name;}
		public void setName(String name) {this.name = name;}

		public City(String name) {
			this.setName(name);
		}
		
		public City(String name, Point coordinate) {
			this(name);
			setCoordinate(coordinate);
		}
	}