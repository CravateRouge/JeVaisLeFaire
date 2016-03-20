package controleur;

import enumeration.TypeBateau;
import enumeration.TypeBattle;
import enumeration.TypeMode;
import modele.Case;
import modele.Menu;
import vue.BatailleFenetre;
import vue.CaseButton;

public class BatailleControleur {

	private Menu modele;
	protected BatailleFenetre f;




	public BatailleControleur(Menu modele){
		this.modele=modele;
		f=new BatailleFenetre(this,modele.getTaille());				
		f.display();
		
		modele.addModeListener(f);
		modele.addBattleListener(f);
		modele.addJ1NameListener(f);
		modele.addInitListener(f);

	}
	
	


	public void notifyModeChoisi(TypeMode mode) {
		modele.setMode(mode);	
	}




	public void notifyBattleChoisie(TypeBattle battle) {
		modele.setBattle(battle);	
	}




	public boolean notifyShipRemoved(TypeBateau boat) {
		return modele.removeShip(boat);
	}




	public void notifyShipAdded(TypeBateau boat) {
		modele.addShip(boat);	
	}




	public void notifyNameChanged(String j1Name) {
		modele.setName(j1Name);		
	}




	public void notifyCaseCree(CaseButton b, int x, int y) {
		Case c=modele.creerCase(x,y);
		c.addOccupeeListener(b);
		c.addVisiteeListener(b);
		
	}




	public void notifyCasePressee(int x, int y) {
		modele.casePressee(x,y);
		
	}
	
}
