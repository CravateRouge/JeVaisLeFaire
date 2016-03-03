package vue;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import controleur.BatailleController;
import enumeration.TypeMode;

public class ModeAction extends AbstractAction {

	private BatailleController controller;
	private TypeMode mode;

	public ModeAction(BatailleController controller,TypeMode mode) {
		super(mode.toString());
		this.mode=mode;
		this.controller=controller;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		controller.notifyModeChoisi(mode);
	}

}
