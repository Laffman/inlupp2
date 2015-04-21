import javax.swing.*;

import java.awt.*;

public abstract class Place extends JComponent {

	// Variablerna är satta som protected eftersom det behöver kommas åt av subklasser
	protected String name; 
	protected Position pos;
	protected Category cat;
	protected boolean marked = false;

	
	protected Place(String name, Position pos) {
		this.name = name;
		setBounds(pos.getX() -10, pos.getY()-20, 50, 50);
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	}

	protected Place(String name, Position pos, Category cat) {
		this.name = name;
		this.cat = cat;
		setBounds(pos.getX()-10, pos.getY()-20, 50, 50);
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	}

	abstract protected void show(Graphics g);
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (!marked) {	
			if (cat == null) {
				g.setColor(Color.BLACK);
			} else {
				g.setColor(cat.getColor());
			}
		int[] xes = { 0, 10, 20 };
		int[] yes = { 0, 20, 0 };
		g.fillPolygon(xes, yes, 3);
		setBounds(this.getX(), this.getY(), 20, 20); 
		//setBounds(this.getX(), this.getY()-20, 50, 50); //gör så saker flyger! COOLT! :D	
		} else if (marked){
			show(g);
		}
	}
	
	public Position getPosition() {
		return pos;
	}
	
	public String getName() {
		return name;
	}
	
	public Category getCategory() {
		return cat;
	}

	public void setMarked(boolean b) {
		marked = b;
		repaint();
	}

	public boolean isMarked() {
		return marked;
	}
}
