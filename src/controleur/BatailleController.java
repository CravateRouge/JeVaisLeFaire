package controleur;

import modele.Menu;
import vue.BatailleFenetre;

public class BatailleController {

	private BatailleFenetre fenetre;
	private Menu model;

	public BatailleController(Menu model){
		this.model=model;
		
		fenetre=new BatailleFenetre(this);
		model.addModeListener(fenetre);
		displayView();
	}
	
	private void displayView() {
		fenetre.display();
		
	}

	public void notifyModeChoisi(String mode){
		model.setMode(mode);
	}
	
	public void notifyBattleChoisie(String battle){
		model.setBattle(battle);
	}
}
