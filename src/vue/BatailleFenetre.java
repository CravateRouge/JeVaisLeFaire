package vue;

import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.util.List;


import javax.swing.JFrame;
import javax.swing.JPanel;
import controleur.BatailleController;
import enumeration.TypeBateau;
import enumeration.TypeBattle;
import listener.InitListener;

public class BatailleFenetre extends JFrame implements InitListener{
	private BatailleController controller;
	private JPanel menuPanel,jeuPanel;

	public BatailleFenetre(BatailleController controller) {
		super();

		this.controller=controller;
		build();
	}

	private void build() {
		setTitle("Bataille Navale");
		setSize(1200, 700);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(menuPanel=new MenuPanel(controller, new CardLayout()));
		jeuPanel=new JeuPanel(controller);

	}

	public void display(){
		setVisible(true);
	}

	public JPanel getMenuPanel() {
		return menuPanel;
	}
	
	public JPanel getJeuPanel(){
		return jeuPanel;
	}

	@Override
	public void initGame(TypeBattle battle, String j1Nom, String j2Nom, int taille, TypeBateau currentBoat) {
		((JeuPanel) jeuPanel).buildJeu(j1Nom, j2Nom, taille, currentBoat);
		setContentPane(jeuPanel);
		invalidate();
		validate();
		
	}

}
