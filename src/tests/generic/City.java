package tests.generic;

public class City {
		
		private String name;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public City(String name) {
			this.setName(name);
		}
	}