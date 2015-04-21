import java.awt.*;

import javax.swing.*;

class DescriptionPane extends JPanel {
	private JTextArea text = new JTextArea();

	public DescriptionPane(Place p) {
		setBounds(p.getX(), p.getY(), 150, 150);
		setLayout(new BorderLayout());
		add(new JScrollPane(text), BorderLayout.CENTER);
		text.setBackground(Color.YELLOW);
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	}
}