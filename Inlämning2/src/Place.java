import javax.swing.*;
import javax.swing.border.*;

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
		if (marked && this instanceof NamedPlace) {
			g.setColor(Color.RED);
			g.drawRect(0, 0, getWidth() -1, getHeight() - 1); //ritar nån form av rektangel.. 
			g.setFont(new Font("TimesRoman", Font.BOLD, 18)); //font-inställningar
			g.drawString(name, 15, 20); //skriver namnet i rektangeln.. måste göra så att rektangeln ändrar storlek beroende på namnets längd?
			//setBorder(new LineBorder(Color.RED)); //sätter en border runt objektet
		}else if (marked && this instanceof DescribedPlace) {
			//DescriptionPane dp = new DescriptionPane(pos); //tänkte man kunde göra såhär.. nope!
			show(g);
		}else {
			show(g);
		}
	}

	public Position getPos() {
		return pos;
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

//Det här funkar inte:
class DescriptionPane extends JPanel {
	private JTextArea text = new JTextArea();

	public DescriptionPane(Position p) {
		setBounds(p.getX(), p.getY(), 50, 50);
		setLayout(new BorderLayout());
		add(new JScrollPane(text), BorderLayout.CENTER);
		text.setBackground(Color.YELLOW);
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	}
}

