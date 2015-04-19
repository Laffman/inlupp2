import javax.swing.*;


class NamedPlaceForm extends JPanel {
	private JTextField nameField;

	NamedPlaceForm() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		JPanel row = new JPanel();
		add(row);
		row.add(new JLabel("Name: "));
		nameField = new JTextField(10);
		row.add(nameField);
	}

	public String getName() {
		return nameField.getText();
	}
}