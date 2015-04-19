import javax.swing.*;

import java.awt.*;

public abstract class Place extends JComponent {

	protected String name; // Variablerna är satta som protected eftersom de
							// behöver kommas åt av subklasserna men inget annat
	private boolean shown = false;

	protected Position pos;

	// protected Category category; //behövs sen när alla platsobjekt skapas
	// med en kategori vald

	protected Place(String name, Position pos) {
		this.name = name;
		// this.category = category; //behövs sen..
		setBounds(pos.getX(), pos.getY(), 50, 50);
		Dimension d = new Dimension(50, 50);
		setPreferredSize(d);
		setMaximumSize(d);
		setMinimumSize(d);
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.BLUE);
		int[] xes = { 0, 25, 50 };
		int[] yes = { 50, 0, 50 };
		g.fillPolygon(xes, yes, 3);
	}

	public String getName() {
		return name;
	}

	public void setVisible() {
		if (!isVisible()) {
			setVisible(true);
		}
	}

	public void setInvisible() {
		if (isVisible()) {
			setVisible(false);
		}
	}

	public void setShown(boolean b) {
		shown = b;
		repaint();
	}

	public boolean isShown() {
		return shown;
	}

}