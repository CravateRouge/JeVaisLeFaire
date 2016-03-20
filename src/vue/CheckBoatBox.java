package vue;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.AbstractButton;
import javax.swing.JCheckBox;

import controleur.BatailleControleur;
import enumeration.TypeBateau;

public class CheckBoatBox extends JCheckBox implements ItemListener{

	private BatailleControleur c;
	private TypeBateau boat;

	public CheckBoatBox(BatailleControleur c, TypeBateau boat) {
		super(boat.toString(),true);
		this.c=c;
		this.boat=boat;
		addItemListener(this);
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if(e.getStateChange() == ItemEvent.DESELECTED){
			if(c.notifyShipRemoved(boat))
				this.setSelected(true);
		}
		
		else
			c.notifyShipAdded(boat);	
	}


}
