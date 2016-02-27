package vue;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import controleur.BatailleController;

public class BattleAction extends AbstractAction {
	private BatailleController controller;
	private String name;

	public BattleAction(BatailleController controller,String name) {
		super(name);
		this.name=name;
		this.controller=controller;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		controller.notifyBattleChoisie(name);
	}

}
