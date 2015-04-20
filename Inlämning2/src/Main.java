import java.awt.*;
import java.io.*;
import java.util.*;
import java.awt.event.*;

import javax.swing.*;

class Main extends JFrame {
	
	MapPanel mp = null; // skapar en variabel f�r kartan vi kan anv�nda senare
	NewPlace newPlace = new NewPlace();
	// WhatHere whatHere = new WhatHere();

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

	HashMap<String, Position> placeMap = new HashMap<>(); // hashmap d�r platser
															// sparas med sin
															// position f�r
															// s�kning
	ArrayList<Place> placeList = new ArrayList<>(); // arraylist f�r platser..

	Main() {
		super("Main");

		setLayout(new BorderLayout());

		// Arkiv Menyn
		JMenuBar mb = new JMenuBar(); // skapar menyraden
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
		box.addActionListener(new NewPlaceActivator());
		north.add(searchField);
		searchField.setText("Search");
		searchField.addMouseListener(new SearchText());
		JButton search = new JButton("Search");
		north.add(search);
		search.addActionListener(new Search());
		JButton hide = new JButton("Hide places");
		north.add(hide);
		hide.addActionListener(new HidePlace());
		JButton delete = new JButton("Delete places");
		north.add(delete);
		delete.addActionListener(new DeletePlace());
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
		categoryList
				.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION); // en
																					// eller
																					// flera?
																					// vad
																					// vill
																					// man
																					// ha?
		categoryList.setLayoutOrientation(JList.VERTICAL); // listan �r
															// vertikal.. kanske
															// inte beh�vs..
		categoryList.setFixedCellWidth(150); // g�r den h�r ens n�t nu? nej?
		JButton hideC = new JButton("Hide category");
		east.add(hideC);
		JButton newC = new JButton("New category");
		east.add(newC);
		newC.addActionListener(new NewCategory());
		JButton delC = new JButton("Delete category");
		east.add(delC);
		east.add(Box.createVerticalGlue()); // Limmar knapparna till botten av
		// panelen.. dont want this? tar man bort den blir kategorilistan
		// stooor!

		// Stuff
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack(); // s�tter allt till minium size s� alla komponenter f�r plats
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

	// Searchklasser:
	class SearchText extends MouseAdapter { // Tar bort texten fr�n s�krutan n�r
											// man klickar d�r
		public void mouseClicked(MouseEvent click) {
			searchField.setText("");
		}
	}

	class Search implements ActionListener { // G�r alla platser med ett visst
												// namn visible
		public void actionPerformed(ActionEvent ave) {
			String p = searchField.getText();
			for (Place pl : placeList)
				if (pl.getName().equals(p)) {
					pl.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(Main.this, "The place " + p
							+ " does not exist!");
				}
		}
	}

	class HidePlace implements ActionListener { // D�ljer alla platser med ett
												// visst namn
		public void actionPerformed(ActionEvent ave) {
			String p = searchField.getText();
			for (Place pl : placeList)
				if (pl.getName().equals(p)) {
					pl.setVisible(false);
				}
		}
	}

	class DeletePlace implements ActionListener { // Tar bort alla platser med
													// ett visst namn
		public void actionPerformed(ActionEvent ave) {
			String p = searchField.getText();
			for (Place pl : placeList)
				if (pl.getName().equals(p)) {
					System.out.println(pl.getName());
					placeList.remove(pl);
				}
		}
	}

	class WhatLyss implements ActionListener { // Kollar om det finns n�got d�r
												// man klickar
		public void actionPerformed(ActionEvent ave) {
		}
	}

	class WhatHere extends MouseAdapter { // Kollar om det finns n�got d�r man
											// klickar
		public void actionPerformed(MouseEvent ave) {
		}
	}

	// Platsklasser:
	class NewPlace extends MouseAdapter { // Skapar och placerar en plats p�
											// kartan..
		public void mouseClicked(MouseEvent mev) {
			int x = mev.getX(); // h�mta x koordinat p� kartan
			int y = mev.getY(); // h�mta y koordinat p� kartan

			Position pos = new Position(x, y); // skapa ett positionsobjekt
			Category cat = categoryList.getSelectedValue();
			
			if (mev.getSource() == mp) { // Anv�nder 'mp' som k�lla
				for (;;)
					switch (box.getSelectedIndex()) { // Kollar vilket
														// alternativ som �r
														// valt

					case 0:
						DescribedPlaceForm dpForm = new DescribedPlaceForm();
						int an = JOptionPane.showConfirmDialog(Main.this,
								dpForm, "New described place",
								JOptionPane.OK_CANCEL_OPTION);
						if (an != JOptionPane.OK_OPTION) { // ser till s� att om
															// man klickar p�
															// cancel s�
															// avslutas det
							mp.setCursor(Cursor.getDefaultCursor());
							mp.removeMouseListener(newPlace);
							return;
						}

						String dpName = dpForm.getName();
						String description = dpForm.getDescription();
						
						Place dp = new DescribedPlace(dpName, description, pos, cat);
						dp.addMouseListener(new MarkedPlace());
						placeMap.put(dpName, pos); // l�gg till platsen i
													// hashmapen places
						placeList.add(dp); // l�gg till platsen i arraylisten
											// placeList
						mp.add(dp); // l�gg till platsen p� kartan
						mp.validate();
						mp.repaint();
						mp.setCursor(Cursor.getDefaultCursor()); // s�tt default
																	// cursor
						mp.removeMouseListener(newPlace); // f�rhindrar fler �n
															// 1 pos
						
						return;

					case 1:
						NamedPlaceForm npForm = new NamedPlaceForm();
						int ans = JOptionPane.showConfirmDialog(Main.this,
								npForm, "New described place",
								JOptionPane.OK_CANCEL_OPTION);
						if (ans != JOptionPane.OK_OPTION) { // ser till s� att
															// om man klickar p�
															// cancel s�
															// avslutas det
							mp.setCursor(Cursor.getDefaultCursor());
							mp.removeMouseListener(newPlace);
							return;
						}
						String npName = npForm.getName();

						Place np = new NamedPlace(npName, pos, cat);
						np.addMouseListener(new MarkedPlace());
						mp.add(np);
						placeMap.put(npName, pos);
						placeList.add(np);
						mp.validate();
						mp.repaint();
						mp.setCursor(Cursor.getDefaultCursor());
						mp.removeMouseListener(newPlace);				
						return;

					}

			}

		}
	}

	class NewPlaceActivator implements ActionListener { // Aktiverar NewPlace
		public void actionPerformed(ActionEvent nw) {
			if (mp != null) { // om ingen karta laddats s� ska det inte g� att skapa en ny plats
			mp.removeMouseListener(newPlace); // tar bort listener innan man l�gger till en ny (f�rhindrar att man skapar flera objekt samtidigt)
			mp.addMouseListener(newPlace); // g�r s� man kan s�tta ut platser
			mp.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
			}
		}
	}

	// Kategoriklasser:
	class NewCategory implements ActionListener { // Skapar en ny kategori
		public void actionPerformed(ActionEvent aev) {
			CategoryForm form = new CategoryForm();

			int ans = JOptionPane.showConfirmDialog(Main.this,
					form, "New category",
					JOptionPane.OK_CANCEL_OPTION);
			if (ans != JOptionPane.OK_OPTION) {
				return;
			}

			String name = form.getName();
			Color color = form.getColor();

			Category cat = new Category(name, color);
			dataModel.addSorted(cat); // h�r vi l�gga i kategorin i en lista
										// med objekt!

			JOptionPane.showMessageDialog(Main.this, "The category " + name
					+ " has been added!");
			return;
		}
	}

	class MarkedPlace extends MouseAdapter {
		//@Override //beh�vs inte verkar det som..
		public void mouseClicked(MouseEvent mev) {
			Place p = (Place) mev.getSource();
				if (!p.isMarked()) {
					p.setMarked(true);
				}else if (p.isMarked()) {
					p.setMarked(false);
				}
			
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