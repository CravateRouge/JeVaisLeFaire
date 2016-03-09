package vue;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import controleur.BatailleController;
import enumeration.TypeBattle;

public class BattleAction extends AbstractAction {
	
	private BatailleController controller;
	private TypeBattle battle;

	public BattleAction(BatailleController controller,TypeBattle battle) {
		super(battle.toString());
		this.battle=battle;
		this.controller=controller;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		controller.notifyBattleChoisie(battle);
	}

}
