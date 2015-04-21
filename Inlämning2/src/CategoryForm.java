import java.awt.Color;

import javax.swing.*;


class CategoryForm extends JPanel {
	private JTextField nameField;
	private JColorChooser colorChooser;

	CategoryForm() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		JPanel row1 = new JPanel();
		add(row1);
		row1.add(new JLabel("Name: "));
		nameField = new JTextField(10);
		row1.add(nameField);

		JPanel row2 = new JPanel();
		add(row2);
		colorChooser = new JColorChooser();
		row2.add(colorChooser);

	}

	public String getName() {
		return nameField.getText();
	}

	public Color getColor() {
		return colorChooser.getColor();
	}

}