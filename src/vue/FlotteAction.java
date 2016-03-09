package vue;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JTextField;

import controleur.BatailleController;

public class FlotteAction extends AbstractAction {

	private BatailleController controller;
	private FlottePanel fenetre;

	public FlotteAction(FlottePanel flottePanel, BatailleController controller, String name) {
		super(name);
		this.fenetre=flottePanel;
		this.controller=controller;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		controller.notifyFlotteChoisie(fenetre.getTextField().getText(), fenetre.getFlotte());

	}

}
