package vue;

import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.LayoutManager;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import controleur.BatailleController;
import enumeration.TypeBattle;
import enumeration.TypeMode;
import listener.BattleListener;
import listener.FlotteListener;
import listener.ModeListener;

public class MenuPanel extends JPanel implements ModeListener,BattleListener, FlotteListener {
	
	private BatailleController controller;

	public MenuPanel (BatailleController controller, LayoutManager layout){
		super(layout);
		this.controller= controller;
		add(buildModePane(), "ModePanel");
		add(buildBattlePane(), "BattlePanel");
		add(new FlottePanel(controller, new FlowLayout()), "FlottePanel1");
	}

	

	private JPanel buildBattlePane() {
		JPanel battlePanel = new JPanel(new FlowLayout());

		JLabel label = new JLabel("Choisissez un type de bataille:");
		battlePanel.add(label);

		for (TypeBattle typeBattle : TypeBattle.values()) {
			battlePanel.add(
					new JButton(new BattleAction(controller, typeBattle))
					);
		}
		
		return battlePanel;
	}



	private JPanel buildModePane() {
		JPanel menuPanel=new JPanel(new FlowLayout());

		JLabel label = new JLabel("Choisissez un mode:");
		menuPanel.add(label);

		for (TypeMode typeMode : TypeMode.values()) {
			menuPanel.add(
					new JButton(new ModeAction(controller, typeMode))
					);
		}

		return menuPanel;
	}

	@Override
	public void battleChoisie() {
		((CardLayout) getLayout()).next(this);	
	}
	
	@Override
	public void modeChoisi() {
			((CardLayout) getLayout()).next(this);

	}

	@Override
	public void flotteChoisie() {
		add(new FlottePanel(controller, new FlowLayout()), "FlottePanel2");
		((CardLayout) getLayout()).next(this);
		
	}

	
}
