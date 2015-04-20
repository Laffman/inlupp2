import java.awt.*;
import java.io.*;
import java.util.*;
import java.awt.event.*;

import javax.swing.*;

class Main extends JFrame {
	
	MapPanel mp = null; // skapar en variabel för kartan vi kan använda senare
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

	JTextField searchField = new JTextField(20); // searchfield skapas utanför
													// Main för att den ska
													// synas i andra klasser

	HashMap<String, Position> placeMap = new HashMap<>(); // hashmap där platser
															// sparas med sin
															// position för
															// sökning
	ArrayList<Place> placeList = new ArrayList<>(); // arraylist för platser..

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

		// Östra Delen
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
		categoryList.setLayoutOrientation(JList.VERTICAL); // listan är
															// vertikal.. kanske
															// inte behövs..
		categoryList.setFixedCellWidth(150); // gör den här ens nåt nu? nej?
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
		pack(); // sätter allt till minium size så alla komponenter får plats
		setLocation(450, 200);
		setVisible(true);
		setResizable(false);

	}

	// Menyklasser:
	class NewMap implements ActionListener { // Öppnar en ny karta
		public void actionPerformed(ActionEvent ave) {

			int svar = jfc.showOpenDialog(Main.this); // öppna fönstret, det
														// alternativ man väljer
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
			pack(); // sätter storleken till minimum alla objekt kräver
			validate(); // validera layout
			repaint(); // måla om allt

		}
	}

	class ExitProgram implements ActionListener { // Klass för att stänga
													// programmet.
		public void actionPerformed(ActionEvent ave) {
			System.exit(0);
		}
	}

	// Searchklasser:
	class SearchText extends MouseAdapter { // Tar bort texten från sökrutan när
											// man klickar där
		public void mouseClicked(MouseEvent click) {
			searchField.setText("");
		}
	}

	class Search implements ActionListener { // Gör alla platser med ett visst
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

	class HidePlace implements ActionListener { // Döljer alla platser med ett
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

	class WhatLyss implements ActionListener { // Kollar om det finns något där
												// man klickar
		public void actionPerformed(ActionEvent ave) {
		}
	}

	class WhatHere extends MouseAdapter { // Kollar om det finns något där man
											// klickar
		public void actionPerformed(MouseEvent ave) {
		}
	}

	// Platsklasser:
	class NewPlace extends MouseAdapter { // Skapar och placerar en plats på
											// kartan..
		public void mouseClicked(MouseEvent mev) {
			int x = mev.getX(); // hämta x koordinat på kartan
			int y = mev.getY(); // hämta y koordinat på kartan

			Position pos = new Position(x, y); // skapa ett positionsobjekt
			Category cat = categoryList.getSelectedValue();
			
			if (mev.getSource() == mp) { // Använder 'mp' som källa
				for (;;)
					switch (box.getSelectedIndex()) { // Kollar vilket
														// alternativ som är
														// valt

					case 0:
						DescribedPlaceForm dpForm = new DescribedPlaceForm();
						int an = JOptionPane.showConfirmDialog(Main.this,
								dpForm, "New described place",
								JOptionPane.OK_CANCEL_OPTION);
						if (an != JOptionPane.OK_OPTION) { // ser till så att om
															// man klickar på
															// cancel så
															// avslutas det
							mp.setCursor(Cursor.getDefaultCursor());
							mp.removeMouseListener(newPlace);
							return;
						}

						String dpName = dpForm.getName();
						String description = dpForm.getDescription();
						
						Place dp = new DescribedPlace(dpName, description, pos, cat);
						dp.addMouseListener(new MarkedPlace());
						placeMap.put(dpName, pos); // lägg till platsen i
													// hashmapen places
						placeList.add(dp); // lägg till platsen i arraylisten
											// placeList
						mp.add(dp); // lägg till platsen på kartan
						mp.validate();
						mp.repaint();
						mp.setCursor(Cursor.getDefaultCursor()); // sätt default
																	// cursor
						mp.removeMouseListener(newPlace); // förhindrar fler än
															// 1 pos
						
						return;

					case 1:
						NamedPlaceForm npForm = new NamedPlaceForm();
						int ans = JOptionPane.showConfirmDialog(Main.this,
								npForm, "New described place",
								JOptionPane.OK_CANCEL_OPTION);
						if (ans != JOptionPane.OK_OPTION) { // ser till så att
															// om man klickar på
															// cancel så
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
			if (mp != null) { // om ingen karta laddats så ska det inte gå att skapa en ny plats
			mp.removeMouseListener(newPlace); // tar bort listener innan man lägger till en ny (förhindrar att man skapar flera objekt samtidigt)
			mp.addMouseListener(newPlace); // gör så man kan sätta ut platser
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
			dataModel.addSorted(cat); // här vi lägga i kategorin i en lista
										// med objekt!

			JOptionPane.showMessageDialog(Main.this, "The category " + name
					+ " has been added!");
			return;
		}
	}

	class MarkedPlace extends MouseAdapter {
		//@Override //behövs inte verkar det som..
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

	class HideCategory implements ActionListener { // Döljer allt av en kategori
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