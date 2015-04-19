import java.awt.Color;
import java.awt.Graphics;

class NamedPlace extends Place {

	public NamedPlace(String namn, int x, int y) {
		super(namn, x, y);
	}

	protected void show(Graphics g) {
		g.setColor(Color.BLACK);
		int[] xes = { 0, 25, 50 };
		int[] yes = { 50, 0, 50 };
		g.fillPolygon(xes, yes, 3);
	}

}
