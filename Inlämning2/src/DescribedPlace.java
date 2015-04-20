import java.awt.Color;
import java.awt.Graphics;


class DescribedPlace extends Place {

	private String description;

	public DescribedPlace(String name, String description, Position pos) {
		super(name, pos);
		this.description = description;
	}

	public DescribedPlace(String name, String description, Position pos, Category cat) {
		super(name, pos, cat);
		this.description = description;
	}
	
	protected void show(Graphics g) {
		if (cat == null) {
			g.setColor(Color.BLACK);
		}else {
			g.setColor(cat.getColor());
		}
		int[] xes = { 0, 20, 40 };
		int[] yes = { 0, 40, 0 };
		g.fillPolygon(xes, yes, 3);
	}
	
	public String getDescription() {
		return description;
	}
	
}