import java.awt.Color;
import java.awt.Graphics;

class DescribedPlace extends Place {

	private String description;

	public DescribedPlace(String namn, String description, int x, int y) {
		super(namn, x, y);
		this.description = description;
	}
	
	protected void paintComponent(Graphics g) {
		g.setColor(Color.GREEN);
		int[] xes = { 0, 25, 50 };
		int[] yes = { 50, 0, 50 };
		g.fillPolygon(xes, yes, 3);
	}
	
	public String getDescription() {
		return description;
	}
	
}
