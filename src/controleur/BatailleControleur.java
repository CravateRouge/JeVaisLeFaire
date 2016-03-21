package controleur;

import enumeration.TypeBateau;
import enumeration.TypeBattle;
import enumeration.TypeMode;
import modele.Case;
import modele.Plateau;
import vue.BatailleFenetre;
import vue.CaseButton;

public class BatailleControleur {

	private Plateau modele;
	protected BatailleFenetre f;




	public BatailleControleur(Plateau modele){
		this.modele=modele;
		f=new BatailleFenetre(this,modele.getTaille());				
		f.display();
		
		modele.addMenuListener(f);
		modele.addEvenementListener(f.getConsole());
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
		c.addCaseListener(b);
		
	}




	public void notifyCasePressee(int x, int y) {
		modele.casePressee(x,y);
		
	}




	public void notifyDirChanged() {
		modele.setHorizontal(!modele.isHorizontal());	
	}
	
}
