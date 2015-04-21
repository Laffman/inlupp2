import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

class NamedPlace extends Place {

	public NamedPlace(String name, Position pos) {
		super(name, pos);
	}

	public NamedPlace(String name, Position pos, Category cat) {
		super(name, pos, cat);
	}

	protected void show(Graphics g) {
			g.setColor(Color.RED);
			g.drawRect(0, 0, getWidth() -1, getHeight() -1); //ritar nån form av rektangel.. 
			g.setFont(new Font("TimesRoman", Font.BOLD, 18)); //font-inställningar
			g.drawString(name, 10, 30); //skriver namnet i rektangeln.. 
			setBounds(this.getX(), this.getY(), (name.length() * 8 + 50), name.length() * 4 + 25); //inte optimalt.. inte snyggt.. inte bra..
			//setBorder(new LineBorder(Color.RED)); //sätter en border runt objektet		
	}
}

