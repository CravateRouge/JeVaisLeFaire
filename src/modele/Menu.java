package modele;

import enumeration.TypeBateau;
import enumeration.TypeBattle;
import enumeration.TypeMode;

public class Menu extends AbstractMenu{

	private TypeBattle battle=TypeBattle.CLASSIQUE;
	private TypeMode mode=TypeMode.DEMO;
	private boolean secondeFlotte=false, end=false, positionnement=true;
	private Joueur j1, j2;
	private int taille;



	public Menu(){
		this.taille=10;
		j1=new Joueur("Philippe",taille);
		j2=new Joueur("Mike",taille);
	}

	public Case creerCase(int x, int y) {
		return j1.getCase(x,y)==null? j1.setGrille(x,y): j2.setGrille(x,y);
	}

	public void setMode(TypeMode mode) {
		this.mode=mode;
		fireModeChanged(mode);

		if(mode==TypeMode.DEMO)
			finMenu();
	}

	public void setBattle(TypeBattle battle){
		this.battle=battle;
		fireBattleChanged();

	}

	public boolean removeShip(TypeBateau boat) {
		boolean rep=false;

		if(!secondeFlotte)
			rep=j1.removeShip(boat);
		else
			rep=j2.removeShip(boat);
		return rep;
	}

	public void addShip(TypeBateau boat) {
		if(!secondeFlotte)
			j1.addShip(boat);
		else
			j2.addShip(boat);
	}

	public void setName(String jName) {
		String tmpNom=jName;

		if(mode == TypeMode.MULTI && !secondeFlotte){
			secondeFlotte=true;
			j1.setNom(tmpNom);
			fireJ1NameChanged();
		}
		else{
			if(secondeFlotte)
				j2.setNom(tmpNom);
			else
				j1.setNom(tmpNom);

			finMenu();
		}

	}

	public void finMenu(){
		secondeFlotte=false;
		fireStartGame(j1.getNom(),j2.getNom());
	}


	private void indication(int x, int y) {
		Joueur j=aQuiLeTour();
		//fireIndication(j.indication(new Case(x,y)));
	}

	private int randomCardi() {
		return (int)(Math.random()*10);
	}

	private void placement(int x, int y){
		Joueur j=aQuiLeTour();
		if(j.placement(x, y))
		{

			if(secondeFlotte)
				positionnement=false;

			secondeFlotte=!secondeFlotte;
			if(!positionnement)
				secondeFlotte=true;
		}
	}

	private void tir(int x, int y){
		Joueur j=aQuiLeTour();
		if(!j.getCase(x, y).isVisitee()){
			System.out.println("nooon");
			end=j.tir(x, y);
			secondeFlotte=!secondeFlotte;
		}

	}


	public void casePressee(int x, int y) {
		if(end)
			System.out.println("the end");
		else{
			if(positionnement)
				placement(x,y);
			else{
				if(battle==TypeBattle.ALERTE || battle==TypeBattle.ARTILLERIE)
					tir(randomCardi(),randomCardi());
				else
					tir(x,y);

				if(battle==TypeBattle.RADAR || battle==TypeBattle.ALERTE)
					indication(x,y);
			}
		}

	}

	private Joueur aQuiLeTour(){
		return secondeFlotte?j2:j1;
	}

	public int getTaille() {
		return taille;
	}
}
