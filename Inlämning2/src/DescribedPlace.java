import java.awt.Color;
import java.awt.Graphics;

class DescribedPlace extends Place {

	private String description;
	

	public DescribedPlace(String name, String description, Position pos) {
		super(name, pos);
		this.description = description;
	}

	public DescribedPlace(String name, String description, Position pos,
			Category cat) {
		super(name, pos, cat);
		this.description = description;
	}

	protected void show(Graphics g) {
		DescriptionPane dp = new DescriptionPane(this); //mycket att g�ra p� det h�r..
		this.add(dp); //l�gger till describedpane rutan p� det h�r objektet
		setBounds(this.getX(), this.getY(), 200, 200); 
		}

	public String getDescription() {
		return description;
	}
}