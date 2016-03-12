package vue;



import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import controleur.BatailleController;
import enumeration.TypeBateau;
import enumeration.TypeBattle;

public class JeuPanel extends JPanel {

	private String j1Nom, j2Nom;
	private TypeBattle battle;
	private List<TypeBateau> j1Flotte, j2Flotte;
	private BatailleController controller;
	private static LayoutManager layout =new BorderLayout();


	public JeuPanel(BatailleController controller, TypeBattle battle, String j1Name, String j2Name, List<TypeBateau> j1Flotte, List<TypeBateau> j2Flotte) {
		super(layout);
		this.controller=controller;
		this.battle=battle;
		this.j1Nom=j1Name;
		this.j2Nom=j2Name;
		this.j1Flotte=j1Flotte;
		this.j2Flotte=j2Flotte;
		buildJeu();
		
	}

	private void buildJeu() {
		//add(new JLabel(j1Nom));
		
		add(buildNorth(), BorderLayout.NORTH);
		add(new GrillePanel(controller),BorderLayout.WEST);
		add(new GrillePanel(controller), BorderLayout.CENTER);
		add(buildSouth(), BorderLayout.SOUTH);
		
	}
	
	private JPanel buildNorth(){
		JPanel north = new JPanel(new GridLayout(1,2));
		north.add(new JLabel(j1Nom));
		north.add(new JLabel(j2Nom));
		return north;
	}
	private JPanel buildSouth(){
		JPanel south = new JPanel();
		Dimension dscroll = new Dimension(800,85);
		JTextArea console = new JTextArea(2,5);
		console.setEditable(false);
		JScrollPane scroll = new JScrollPane(console);
		scroll.setPreferredSize(dscroll);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		south.add(scroll);
		return south;
	}
	

}
