package modele;

import java.util.LinkedList;
import java.util.List;

import enumeration.TypeBateau;
import enumeration.TypeBattle;
import enumeration.TypeMode;

public class Menu extends AbstractMenu{

	private TypeMode mode;
	private TypeBattle battle;
	private String j1Name, j2Name;
	private List<TypeBateau> j1Flotte, j2Flotte;

	public Menu(){
		super();
		
		this.j1Name="IA1";
		this.j2Name="IA2";
		
		this.j1Flotte=new LinkedList<TypeBateau>();
		this.j2Flotte=new LinkedList<TypeBateau>();
	}
	
	public void setMode(TypeMode mode) {
		this.mode=mode;
		fireModeChoisi(mode);
	}

	public void setBattle(TypeBattle battle) {
		this.battle=battle;
		fireBattleChoisie();

	}
	
	public void setFlotte(String jnom, List<TypeBateau> flotte) {
		boolean multi1 = mode==TypeMode.MULTI;
		boolean multi2 = multi1 && !j1Flotte.isEmpty();
		
		if(!multi2){
			j1Name=jnom;
			j1Flotte.addAll(flotte);
		}
		else{		
			j2Name=jnom;
			j2Flotte.addAll(flotte);
		}

		if(multi1 && j2Flotte.isEmpty())
			fireFlotteChoisie();
		else
			fireInitGame();		
	}
}
