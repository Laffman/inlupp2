import javax.swing.*;


class DescribedPlaceForm extends JPanel {
	private JTextField nameField;
	private JTextArea descriptionField;
	
	DescribedPlaceForm() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		JPanel row = new JPanel();
		add(row);
		row.add(new JLabel("Name: "));
		nameField = new JTextField(10);
		row.add(nameField);
		
		JPanel row2 = new JPanel();
		add(row2);
		row2.add(new JLabel("Description: "));
		descriptionField = new JTextArea(12, 12);
		row2.add(descriptionField);		
		
	}

	public String getName() {
		return nameField.getText();
	}
	
	public String getDescription() {
		return descriptionField.getText();
	}
	
}