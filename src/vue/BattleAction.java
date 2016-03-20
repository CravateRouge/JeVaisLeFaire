package vue;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import controleur.BatailleControleur;
import enumeration.TypeBattle;

public class BattleAction extends AbstractAction {
	private TypeBattle battle;
	private BatailleControleur c;

	public BattleAction(BatailleControleur c, TypeBattle battle) {
		super(battle.toString());
		this.battle=battle;
		this.c=c;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		c.notifyBattleChoisie(battle);
	}

}
