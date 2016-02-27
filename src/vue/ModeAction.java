package vue;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import controleur.BatailleController;

public class ModeAction extends AbstractAction {

	private BatailleController controller;
	private String name;

	public ModeAction(BatailleController controller,String name) {
		super(name);
		this.name=name;
		this.controller=controller;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		controller.notifyModeChoisi(name);
	}

}
