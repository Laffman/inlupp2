import java.awt.Color;

import javax.swing.*;


class CategoryForm extends JPanel {
	private JTextField nameField;
	private JColorChooser colorChooser;

	CategoryForm() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		JPanel rad1 = new JPanel();
		add(rad1);
		rad1.add(new JLabel("Name: "));
		nameField = new JTextField(10);
		rad1.add(nameField);

		JPanel rad2 = new JPanel();
		add(rad2);
		colorChooser = new JColorChooser();
		rad2.add(colorChooser);

	}

	public String getName() {
		return nameField.getText();
	}

	public Color getColor() {
		return colorChooser.getColor();
	}

}