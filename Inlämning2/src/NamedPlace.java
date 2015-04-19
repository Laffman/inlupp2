import java.awt.Color;
import java.awt.Graphics;

class NamedPlace extends Place {

	public NamedPlace(String name, Position pos) {
		super(name, pos);
	}

	protected void paintComponent(Graphics g) {
		g.setColor(Color.BLUE);
		int[] xes = { 0, 25, 50 };
		int[] yes = { 50, 0, 50 };
		g.fillPolygon(xes, yes, 3);
	}


}
