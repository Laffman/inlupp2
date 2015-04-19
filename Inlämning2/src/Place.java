import javax.swing.*;

import java.awt.*;

public abstract class Place extends JComponent {

	protected String namn; // Variablerna �r satta som protected eftersom de
							// beh�ver kommas �t av subklasserna men inget annat
	private boolean shown = false;
	//protected Category category; //beh�vs sen n�r alla kategoriobjekt skapas med en kategori vald

	protected Place(String namn, int x, int y) {
		this.namn = namn;
		//this.category = category; //beh�vs sen..
		setBounds(x, y, 50, 50);
		Dimension d = new Dimension(50, 50);
		setPreferredSize(d);
		setMaximumSize(d);
		setMinimumSize(d);
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	}

	public String getNamn() {
		return namn;
	}
}
