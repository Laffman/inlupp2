import java.awt.*;


class DescribedPlace extends Place {

	private String description;
	DescriptionPane dp = new DescriptionPane(this);
	
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
		this.add(dp); //lägger till describedpane rutan på det här objektet
		setBounds(this.getX(), this.getY(), 200, 200); 
	}

	public String getDescription() {
		return description;
	}
}

//protected void show(Graphics g) { // mycket att göra.. funkar inte..
//	setBounds(this.getX(), this.getY(), 150, 150);
//	dp.setLayout(new BorderLayout());
//	dp.add(sp, BorderLayout.CENTER);
//	add(dp); // lägger till describedpane rutan på det här objektet
//	text.setBackground(Color.PINK);
//	text.setText(description);
//	setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));	
//}
