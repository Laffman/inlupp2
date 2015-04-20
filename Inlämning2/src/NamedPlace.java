import java.awt.Color;
import java.awt.Graphics;

class NamedPlace extends Place {

	public NamedPlace(String name, Position pos) {
		super(name, pos);
	}

	public NamedPlace(String name, Position pos, Category cat) {
		super(name, pos, cat);
	}

	protected void show(Graphics g) {
		if (cat == null) {
			g.setColor(Color.BLACK);
		} else {
			g.setColor(cat.getColor());
		}
		int[] xes = { 0, 20, 40 };
		int[] yes = { 0, 40, 0 };
		g.fillPolygon(xes, yes, 3);
	}

}
