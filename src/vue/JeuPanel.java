package vue;

import java.awt.LayoutManager;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

import controleur.BatailleController;
import enumeration.TypeBateau;
import enumeration.TypeBattle;

public class JeuPanel extends JPanel {

	private String j1Nom, j2Nom;
	private TypeBattle battle;
	private List<TypeBateau> j1Flotte, j2Flotte;
	private BatailleController controller;

	public JeuPanel(BatailleController controller, LayoutManager layout, TypeBattle battle, String j1Name, String j2Name, List<TypeBateau> j1Flotte, List<TypeBateau> j2Flotte) {
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
		add(new JLabel(j1Nom));
		add(new GrillePanel(controller));
		add(new GrillePanel(controller));
		add(new JLabel(j2Nom));
		
	}
	

}
