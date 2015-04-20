import javax.swing.*;

import java.awt.*;

public abstract class Place extends JComponent {

	protected String name; // Variablerna är satta som protected eftersom de
							// behöver kommas åt av subklasserna men inget annat
	protected boolean marked = false;

	protected Position pos;
	protected Category cat;

	// protected Category category; //behövs sen när alla platsobjekt skapas
	// med en kategori vald

	protected Place(String name, Position pos) {
		this.name = name;
		setBounds(pos.getX(), pos.getY(), 50, 50);
		Dimension d = new Dimension(50, 50);
		setPreferredSize(d);
		setMaximumSize(d);
		setMinimumSize(d);
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	}

	protected Place(String name, Position pos, Category cat) {
		this.name = name;
		this.cat = cat;
		setBounds(pos.getX(), pos.getY(), 50, 50);
		Dimension d = new Dimension(50, 50);
		setPreferredSize(d);
		setMaximumSize(d);
		setMinimumSize(d);
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	}

	abstract protected void show(Graphics g);
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (marked) {
			g.setColor(Color.BLUE);
			g.drawRect(0, 0, 60, 60);
//			int[] xes = { 0, 20, 40 };
//			int[] yes = { 0, 40, 0 };
			//g.fillPolygon(xes, yes, 3);
		}else {
			show(g);
		}
	}

	public String getName() {
		return name;
	}

	public void setMarked(boolean b) {
		marked = b;
		repaint();
	}

	public boolean isMarked() {
		return marked;
	}

}