package vue;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.JButton;

import controleur.BatailleControleur;
import listener.CaseListener;

public class CaseButton extends JButton implements ActionListener, CaseListener {
private BatailleControleur c;
private int x,y;

	public CaseButton(BatailleControleur c, int x, int y) {
		super();
		this.c=c;
		this.x=x-1;
		this.y=y-1;
		
		addActionListener(this);
		c.notifyCaseCree(this,this.x,this.y);
	}

	@Override
	public void actionPerformed(ActionEvent a) {
			c.notifyCasePressee(x,y);
	}

	@Override
	public void caseVisitee(boolean touche) {
		if(touche)
			setBackground(Color.RED);
		else
			setBackground(Color.DARK_GRAY);
	}

	@Override
	public void caseOccupee() {
		setBackground(Color.GREEN);
	}

	@Override
	public void caseCachee() {
		setBackground(new Color(200));	
	}

}
