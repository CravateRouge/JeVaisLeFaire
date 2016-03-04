package vue;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import controleur.BatailleController;
import listener.BattleListener;
import listener.ModeListener;

public class BatailleFenetre extends JFrame implements ModeListener, BattleListener{
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
		setContentPane(menuPanel=buildMenuPane());

	}

	public void display(){
		setVisible(true);
	}

	private JPanel buildMenuPane() {
		JPanel menuPanel = new JPanel(new CardLayout());

		menuPanel.add(buildModePane(), "ModePanel");
		menuPanel.add(buildBattlePane(), "BattlePanel");
		menuPanel.add(buildGridPane(), "gridPanel");

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
	
	private JPanel buildGridPane(){
		JPanel gridPanel = new JPanel(new GridLayout(11,11));
		Border blackline = BorderFactory.createLineBorder(Color.black,1);
		Border blueline = BorderFactory.createLineBorder(Color.blue, 1);
		for(int o = 0; o<11;o++){
			for(int a = 0; a<11; a++){
				if((a == o) && a == 0){
					JButton ptest0 = new JButton();
					ptest0.setBackground(new Color(150,150,150));
					ptest0.setBorder(blackline);
					gridPanel.add(ptest0);
				}
				else if(a == 0){
					JButton ptest = new JButton(o+"");
					ptest.setBackground(new Color(100,100,100));
					ptest.setBorder(blackline);
					gridPanel.add(ptest);
				}
				else if(o == 0){
					JButton ptest = new JButton((char)(64+a)+"");
					ptest.setBackground(new Color(100,100,100));
					ptest.setBorder(blackline);
					gridPanel.add(ptest);
				}

				else{
					JButton ptest2 = new JButton();
					ptest2.setBackground(new Color(200));
					ptest2.setBorder(blueline);
					gridPanel.add(ptest2);

				}
			}
		}
		gridPanel.setBorder(blackline);
		
		return gridPanel;
	}

	@Override
	public void modeChoisi(String mode) {
		if(!mode.equalsIgnoreCase("Demo"))
			((CardLayout) menuPanel.getLayout()).next(menuPanel);

	}

	@Override
	public void battleChoisi(String battle) {
		((CardLayout) menuPanel.getLayout()).next(menuPanel);
		
	}
}
