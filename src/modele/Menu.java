package modele;

import java.util.List;

import enumeration.TypeBateau;
import enumeration.TypeBattle;
import enumeration.TypeMode;

public class Menu extends AbstractMenu{

	private TypeMode mode;
	private TypeBattle battle;
	private String j1Name, j2Name;
	private int taille;
	private List<TypeBateau> j1Flotte, j2Flotte;
	private Plateau plateau;

	public Menu(){
		super();
		
		this.j1Name="[Hitman Le Cobra]";
		this.j2Name="Philippe";
		taille=10;
		plateau=new Plateau(j1Name,j2Name,taille);
	
	}
	
	private void initGame() {
		TypeBateau currentBoat=null;
		if(mode!=TypeMode.DEMO)
			for (int i = 0; i < j1Flotte.size() && currentBoat==null; i++) 
				currentBoat=j1Flotte.get(i);

		plateau.setGame(mode,battle, j1Name, j2Name, j1Flotte, j2Flotte);	
		fireInitGame(battle, j1Name, j2Name, taille, currentBoat);
	}

	public void setMode(TypeMode mode) {
		this.mode=mode;

		if(mode==TypeMode.DEMO)
			initGame();
		else
			fireModeChoisi();
	}


	public void setBattle(TypeBattle battle) {
		this.battle=battle;
		fireBattleChoisie();

	}
	
	public void setFlotte(String jnom, List<TypeBateau> flotte) {
		
		if(j1Flotte == null){
			j1Name=jnom;
			j1Flotte = flotte;
		}
		else{		
			j2Name=jnom;
			j2Flotte = flotte;
		}

		if(mode == TypeMode.MULTI && j2Flotte == null)		
			fireFlotteChoisie();			
		else
			initGame();		
	}

	public Plateau getPlateau() {
		// TODO Auto-generated method stub
		return plateau;
	}
}
