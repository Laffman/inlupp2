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
		DescriptionPane dp = new DescriptionPane(this); //mycket att göra på det här..
		this.add(dp); //lägger till describedpane rutan på det här objektet
		setBounds(this.getX(), this.getY(), 200, 200); 
		}

	public String getDescription() {
		return description;
	}
}