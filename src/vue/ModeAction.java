package vue;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import controleur.BatailleControleur;
import enumeration.TypeMode;

public class ModeAction extends AbstractAction {
	private TypeMode mode;
	private BatailleControleur c;

	public ModeAction(BatailleControleur c, TypeMode mode) {
		super(mode.toString());
		this.mode=mode;
		this.c=c;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		c.notifyModeChoisi(mode);
	}

}
