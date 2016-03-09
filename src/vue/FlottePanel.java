package vue;

import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

import controleur.BatailleController;
import enumeration.TypeBateau;

public class FlottePanel extends JPanel implements ItemListener {

	private JTextField textField;
	private BatailleController controller;
	private List<TypeBateau> flotte;

	public FlottePanel(BatailleController controller, LayoutManager layout) {
		super(layout);
		this.controller=controller;
		
		initPane();
	}
	
	private void initPane(){
		JLabel label1 = new JLabel("Saisissez votre nom:");
		add(label1);

		textField = new JTextField();
		textField.setColumns(10); //On lui donne un nombre de colonnes à afficher

		add(textField);

		JLabel label2 = new JLabel("Choisissez votre flotte:");
		add(label2);

		flotte=new LinkedList<TypeBateau>();
		CheckBoatBox checkButton;
		
		for (TypeBateau typeBateau : TypeBateau.values()) {
			checkButton= new CheckBoatBox(typeBateau, true);
			add(checkButton);
			checkButton.addItemListener(this);
			flotte.add(typeBateau);
		}

		JButton button = new JButton(new FlotteAction(this, controller, "Valider"));
		add(button);

	}

	public JTextComponent getTextField() {	
		return textField;
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		int index=0;
        TypeBateau typeBateau = ((CheckBoatBox) e.getItemSelectable()).getTypeBateau();
        TypeBateau[] typeBoats= TypeBateau.values();
        
        while ( index < typeBoats.length && typeBoats[index]==typeBateau)
        	index++;
 
        if (e.getStateChange() == ItemEvent.DESELECTED)
            typeBateau = null;
 
        flotte.set(index, typeBateau);	
	}

	public List<TypeBateau> getFlotte() {

		return flotte;
	}
	
	private class CheckBoatBox extends JCheckBox{
		private TypeBateau typeBateau;
		public CheckBoatBox (TypeBateau typeBateau, boolean check){
			super(typeBateau.toString(),check);
			this.typeBateau=typeBateau;
		}
		public TypeBateau getTypeBateau() {
			return typeBateau;
		}
	}
	
}
