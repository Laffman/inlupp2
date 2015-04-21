import java.awt.*;

import javax.swing.*;

class DescriptionPane extends JPanel {
	private JTextArea text = new JTextArea();

	public DescriptionPane(Place p) {
		setBounds(this.getX(), this.getY(), 150, 150);
		setLayout(new BorderLayout());
		add(new JScrollPane(text), BorderLayout.CENTER);
		text.setBackground(Color.YELLOW);
		//text.setText("text test test test text");
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	}

	public String getText() {
		return text.getText();
	}
}