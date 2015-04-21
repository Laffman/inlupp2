import java.awt.*;

public class Category implements Comparable<Category> {
	private Color color;
	private String name;

	public Category(String name, Color color) {
		this.name = name;
		this.color = color;
	}

	public String getName() {
		return name;
	}

	public Color getColor() {
		return color;
	}

	public int compareTo(Category other) { //för att sortera listan
		return name.compareTo(other.name);
	}
	
		
	public String toString() {
		return name;
	}
}