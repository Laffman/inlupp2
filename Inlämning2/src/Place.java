import javax.swing.*;
import javax.swing.border.*;

import java.awt.*;

public abstract class Place extends JComponent {

	// Variablerna �r satta som protected eftersom det beh�ver kommas �t av subklasser
	protected String name; 
	protected Position pos;
	protected Category cat;
	protected boolean marked = false;

	

	protected Place(String name, Position pos) {
		this.name = name;
		setBounds(pos.getX() -10, pos.getY()-20, 50, 50);
		Dimension d = new Dimension(40, 40);
		setPreferredSize(d);
		setMaximumSize(d);
		setMinimumSize(d);
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	}

	protected Place(String name, Position pos, Category cat) {
		this.name = name;
		this.cat = cat;
		setBounds(pos.getX()-10, pos.getY()-20, 50, 50);
		Dimension d = new Dimension(40, 40);
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
			g.drawRect(0, 0, getWidth() -1, getHeight() - 1); //ritar n�n form av rektangel.. 
			g.setFont(new Font("TimesRoman", Font.BOLD, 18)); //font-inst�llningar
			g.drawString(name, 10, 30); //skriver namnet i rektangeln.. m�ste g�ra s� att rektangeln �ndrar storlek beroende p� namnets l�ngd?
			//setBorder(new LineBorder(Color.RED)); //s�tter en border runt objektet
		}else if (marked && this instanceof DescribedPlace) {
			DescriptionPane dp = new DescriptionPane(); //t�nkte man kunde g�ra s�h�r.. nope!
			//show(g);
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

//Det h�r funkar inte:
class DescriptionPane extends JPanel {
	private JTextArea text = new JTextArea();

	public DescriptionPane() {
		//setBounds(p.getX(), p.getY(), 50, 50);
		JPanel pan = new JPanel();
		setLayout(new BorderLayout());
		add(new JScrollPane(text), BorderLayout.CENTER);
		text.setBackground(Color.YELLOW);
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	}
}

