package vue;

import java.awt.CardLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controleur.BatailleController;
import listener.ModeListener;

public class BatailleFenetre extends JFrame implements ModeListener{
	private BatailleController controller;
	private JPanel menuPanel;

	public BatailleFenetre(BatailleController controller) {
		super();

		this.controller=controller;
		build();
	}

	private void build() {
		setTitle("Bataille Navale");
		setSize(400, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(menuPanel=buildMenuPane());

	}

	public void display(){
		setVisible(true);
	}

	private JPanel buildMenuPane() {
		JPanel menuPanel = new JPanel(new CardLayout());

		menuPanel.add(buildModePane(), "ModePanel");
		menuPanel.add(buildBattlePane(), "BattlePanel");

		return menuPanel;
	}

	private JPanel buildBattlePane() {
		JPanel battlePanel = new JPanel(new FlowLayout());
		
		JLabel label = new JLabel("Choisissez un type de bataille:");
		battlePanel.add(label);

		JButton buttonClassique = new JButton(new BattleAction(controller, "Bataille Navale"));
		battlePanel.add(buttonClassique);

		JButton buttonRadar = new JButton(new BattleAction(controller, "Mission Radar"));
		battlePanel.add(buttonRadar);

		JButton buttonArtillerie = new JButton(new BattleAction(controller, "Opération Artillerie"));
		battlePanel.add(buttonArtillerie);

		JButton buttonAlerte = new JButton(new BattleAction(controller, "Alerte Rouge"));
		battlePanel.add(buttonAlerte);

		return battlePanel;
	}



	private JPanel buildModePane() {
		JPanel menuPanel=new JPanel(new FlowLayout());

		JLabel label = new JLabel("Choisissez un mode:");
		menuPanel.add(label);

		JButton buttonDemo = new JButton(new ModeAction(controller, "Demo"));

		menuPanel.add(buttonDemo);

		JButton buttonSolo = new JButton(new ModeAction(controller, "Solo"));
		menuPanel.add(buttonSolo);

		JButton buttonMulti = new JButton(new ModeAction(controller, "Multi"));
		menuPanel.add(buttonMulti);

		return menuPanel;
	}

	@Override
	public void modeChoisi(String mode) {
		if(!mode.equalsIgnoreCase("Demo"))
			((CardLayout) menuPanel.getLayout()).next(menuPanel);

	}




}
