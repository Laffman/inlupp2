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

	private Color getColor() {
		return color;
	}

	public int compareTo(Category other) {
		return name.compareTo(other.name);
	}

	public String toString() {
		return name;
	}
}