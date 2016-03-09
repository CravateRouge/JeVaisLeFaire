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
	private JPanel menuPanel;

	public BatailleFenetre(BatailleController controller) {
		super();

		this.controller=controller;
		build();
	}

	private void build() {
		setTitle("Bataille Navale");
		setSize(700, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(menuPanel=new MenuPanel(controller, new CardLayout()));

	}

	public void display(){
		setVisible(true);
	}

	public JPanel getMenuPanel() {
		return menuPanel;
	}

	@Override
	public void initGame(TypeBattle battle, String j1Name, String j2Name, List<TypeBateau> j1Flotte, List<TypeBateau> j2Flotte) {

		setContentPane(new JeuPanel(controller, new FlowLayout(), battle, j1Name, j2Name, j1Flotte, j2Flotte));
		invalidate();
		validate();
		
	}
}
