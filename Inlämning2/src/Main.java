import java.awt.*;
import java.io.*;
import java.util.*;
import java.awt.event.*;

import javax.swing.*;

class Main extends JFrame {

	MapPanel mp = null; // skapar en variabel f�r kartan vi kan anv�nda senare
	PlacePlace placePlace = new PlacePlace();

	private String[] descriptions = { "DescribedPlace", "NamedPlace" }; // Descriptions
																		// till
																		// box
	JComboBox<String> box = new JComboBox<>(descriptions); // Combobox mofo

	MyListModel dataModel = new MyListModel(); // skapar en ny listmodell
	JList<Category> categoryList = new JList<>(dataModel); // lista att spara
															// kategorier i

	JFileChooser jfc = new JFileChooser("."); // skapar en filechooser till
												// "new map"

	JTextField searchField = new JTextField(20); // searchfield skapas utanf�r
													// Main f�r att den ska
													// synas i andra klasser

	// HashMap<String, Color> places = new HashMap<>(); //hashmap till att spara
	// platser i?

	Main() {
		super("Main");

		setLayout(new BorderLayout());

		// Arkiv Menyn
		JMenuBar mb = new JMenuBar();
		JMenu menu = new JMenu("File");
		setJMenuBar(mb);
		mb.add(menu);
		JMenuItem newMap = new JMenuItem("New Map");
		menu.add(newMap);
		newMap.addActionListener(new NewMap());
		JMenuItem open = new JMenuItem("Open");
		menu.add(open);
		JMenuItem save = new JMenuItem("Save");
		menu.add(save);
		JMenuItem exit = new JMenuItem("Exit");
		menu.add(exit);
		exit.addActionListener(new ExitProgram());

		// Norra Delen
		JPanel north = new JPanel();
		add(north, BorderLayout.NORTH);
		JLabel neu = new JLabel("New:");
		north.add(neu);
		north.add(box);
		box.addActionListener(new NewPlace());
		north.add(searchField);
		searchField.setText("Search");
		searchField.addMouseListener(new SearchText());
		JButton search = new JButton("Search");
		north.add(search);
		JButton hide = new JButton("Hide places");
		north.add(hide);
		JButton delete = new JButton("Delete places");
		north.add(delete);
		JButton what = new JButton("What is here?");
		north.add(what);

		// �stra Delen
		JPanel east = new JPanel();
		add(east, BorderLayout.EAST);
		east.setLayout(new BoxLayout(east, BoxLayout.Y_AXIS)); // vertikal
																// layout!

		JLabel cats = new JLabel("Categories");
		east.add(cats);
		east.add(new JScrollPane(categoryList));
		categoryList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		categoryList.setLayoutOrientation(JList.VERTICAL); // listan �r vertikal.. kanske inte beh�vs..
		categoryList.setFixedCellWidth(150); //g�r den h�r ens n�t nu? nej?
		JButton hideC = new JButton("Hide category");
		east.add(hideC);
		JButton newC = new JButton("New category");
		east.add(newC);
		newC.addActionListener(new NewCategory());
		JButton delC = new JButton("Delete category");
		east.add(delC);
		east.add(Box.createVerticalGlue()); // Limmar knapparna till botten av
		// panelen.. dont want this? tar man bort den blir kategorilistan stooor! 

		// Stuff
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack(); // s�tter allt till minium size s� alla komponenter f�r plats
		setSize(800, 650);
		setLocation(450, 200);
		setVisible(true);
		setResizable(false);
		
	}

	// Menyklasser:
	class NewMap implements ActionListener { // �ppnar en ny karta
		public void actionPerformed(ActionEvent ave) {

			int svar = jfc.showOpenDialog(Main.this); // �ppna f�nstret, det
														// alternativ man v�ljer
														// (ok/cancel etc)
														// sparas i en variabel.

			if (svar != JFileChooser.APPROVE_OPTION) { // om man vill
														// avbryta
				return;
			}
			File file = jfc.getSelectedFile();
			String filnamn = file.getAbsolutePath();
			if (mp != null) {
				remove(mp);
			}
			mp = new MapPanel(filnamn);
			mp.setLayout(null);
			add(mp, BorderLayout.CENTER);
			pack(); // s�tter storleken till minimum alla objekt kr�ver
			validate(); // validera layout
			repaint(); // m�la om allt

		}
	}

	class ExitProgram implements ActionListener { // Klass f�r att st�nga
													// programmet.
		public void actionPerformed(ActionEvent ave) {
			System.exit(0);
		}
	}

	class SearchText extends MouseAdapter { // Tar bort texten fr�n s�krutan
		public void mouseClicked(MouseEvent click) {
			searchField.setText("");
		}
	}

	// Positionsklasser:
	class PlacePlace extends MouseAdapter { // Ska placera en position p� kartan
		public void mouseClicked(MouseEvent mev) {
			int x = mev.getX(); // h�mta x koordinat i panelen
			int y = mev.getY(); // h�mta y koordinat i panelen

			if (mev.getSource() == mp) { // Anv�nder 'mp' som k�lla
				for (;;)
					switch (box.getSelectedIndex()) { // Kollar vilket
														// alternativ som �r
														// valt

					case 0:
						DescribedPlaceForm form = new DescribedPlaceForm();
						int svar = JOptionPane.showConfirmDialog(Main.this,
								form);
						if (svar != JOptionPane.OK_OPTION) {
							return;
						}

						String name = form.getName();
						String description = form.getDescription();

						Place p0 = new DescribedPlace(name, description, x, y); // testobjekt
						mp.add(p0);
						mp.validate();
						mp.repaint();
						mp.setCursor(Cursor.getDefaultCursor()); // s�tt default cursor
						mp.removeMouseListener(placePlace); // f�rhindrar fler �n 1 pos
						
						return;

					case 1:
						NamedPlaceForm form2 = new NamedPlaceForm();
						int svar2 = JOptionPane.showConfirmDialog(Main.this,
								form2);
						if (svar2 != JOptionPane.OK_OPTION) {
							return;
						}
						String name2 = form2.getName();
						
						
						Place p1 = new NamedPlace(name2, x, y); // testobjekt
						mp.add(p1);
						mp.validate();
						mp.repaint();
						mp.setCursor(Cursor.getDefaultCursor()); // s�tt default cursor
						mp.removeMouseListener(placePlace); // f�rhindrar fler �n 1 pos
						
						return;

					}

			}

		}
	}

	class NewPlace implements ActionListener { // Aktiverar PlacePlace
		public void actionPerformed(ActionEvent nw) {
			mp.addMouseListener(placePlace); // g�r s� man kan s�tta ut platser
			mp.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR)); // s�tt
																				// crosshair

		}
	}

	// Kategoriklasser:
	class NewCategory implements ActionListener { // Skapar en ny kategori
		public void actionPerformed(ActionEvent aev) {
			CategoryForm form = new CategoryForm();

			int answ = JOptionPane.showConfirmDialog(Main.this, form);
			if (answ != JOptionPane.OK_OPTION) {
				return;
			}

			String name = form.getName();
			Color color = form.getColor();

			Category cat = new Category(name, color);
			dataModel.addSorted(cat); // h�r vi l�gga i kategorin i en lista
										// med objekt!

			JOptionPane.showMessageDialog(Main.this, "Kategorin " + name
					+ " �r tillagd!");
			return;
		}
	}

	class MyListModel extends DefaultListModel<Category> { // Sorterar
															// kategorier man
															// lagt in

		public void addSorted(Category ny) {
			int pos = 0;
			while (pos < size() && get(pos).compareTo(ny) < 0)
				pos++;
			add(pos, ny);
		}
	}

	class HideCategory implements ActionListener { // D�ljer allt av en kategori
		public void actionPerformed(ActionEvent hc) {
		}
	}

	class DeleteCategory implements ActionListener { // Tar bort allt av en
														// kategori
		public void actionPerformed(ActionEvent dc) {
		}
	}

	public static void main(String[] args) {
		new Main();
	}

}
