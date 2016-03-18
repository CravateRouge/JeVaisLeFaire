package vue;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Icon;

import controleur.BatailleController;
import modele.Case;

public class CaseAction extends AbstractAction {
private int x,y;
private BatailleController controller;
private JeuPanel jeuPanel;

	public CaseAction(BatailleController controller,JeuPanel jeuPanel, int x, int y) {
		this.x=x;
		this.y=y;
		this.controller=controller;
		this.jeuPanel=jeuPanel;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(jeuPanel.isPositionnement())
			controller.notifyShipPlaced(new Case(x,y), jeuPanel.isHorizontal(), jeuPanel.getCurrentBoat());
		else
			controller.notifyCasePressee(x,y);
	}

}
