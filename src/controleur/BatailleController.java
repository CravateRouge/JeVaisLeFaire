package controleur;

import java.util.List;

import javax.swing.JTextField;

import enumeration.TypeBateau;
import enumeration.TypeBattle;
import enumeration.TypeMode;
import listener.BattleListener;
import listener.FlotteListener;
import listener.InitListener;
import listener.ModeListener;
import modele.Menu;
import vue.BatailleFenetre;

public class BatailleController {

	private BatailleFenetre fenetre;
	private Menu model;

	public BatailleController(Menu model){
		this.model=model;
		
		fenetre=new BatailleFenetre(this);
		model.addModeListener((ModeListener) fenetre.getMenuPanel());
		model.addBattleListener((BattleListener) fenetre.getMenuPanel());
		model.addFlotteListener((FlotteListener) fenetre.getMenuPanel());
		model.addInitListener((InitListener) fenetre);
		displayView();
	}
	
	private void displayView() {
		fenetre.display();
		
	}

	public void notifyModeChoisi(TypeMode mode){
		model.setMode(mode);
	}
	
	public void notifyBattleChoisie(TypeBattle battle){
		model.setBattle(battle);
	}

	public void notifyFlotteChoisie(String jname, List<TypeBateau> flotte) {
		model.setFlotte(jname, flotte);
		
	}
}
