package modele;

import enumeration.EtatFlotte;
import enumeration.TypeBateau;
import enumeration.TypeBattle;
import enumeration.TypeMode;

public class Plateau extends AbstractPlateau{

	private TypeBattle battle=TypeBattle.CLASSIQUE;
	private TypeMode mode=TypeMode.DEMO;
	private boolean secondeFlotte=false, end=false, positionnement=true, horizontal=true;
	private Joueur j1, j2;
	private int taille;
	private int arti;

	public Plateau(){
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
			setSecondeFlotte(true);
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
		setSecondeFlotte(false);
		fireStartGame(j1.getNom(),j2.getNom(),j1.nextBoat());
		if(mode == TypeMode.DEMO)
			demo();
		else if(mode == TypeMode.SOLO)
			solo();
	}

	private void demo(){
		j1 = new IA(j1);
		j2 = new IA(j2);
		new Thread(new Runnable() {
			@Override
			public void run() {
				while(!end){
					actionIA();
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}
		}).start();
	}
	
	private void solo(){
		j2 = new IA(j2);
	}

	private void actionIA() {
		if(positionnement){
			boolean tmp=secondeFlotte;
			while(positionnement && tmp == secondeFlotte){
				while(!placement(randomCardi(),randomCardi(),randomBool()));
			}
		}
		else{
			while(tir(randomCardi(),randomCardi()) == EtatFlotte.DVISITEE);
		}
	}

	private void indication(int x, int y) {
		Joueur j=aQuiLeTour();
		fireIndication(j.indication(new Case(x,y)));
	}

	private int randomCardi() {
		return (int)(Math.random()*10);
	}
	
	private boolean randomBool(){
		if((int)(Math.random()*2)==1){
			return true;
		}
		else{
			return false;
		}
	}

	private boolean placement(int x, int y, boolean sens){
		Joueur j=aQuiLeTour();
		TypeBateau boat=j.placement(x, y, sens);
		if(boat == null){
			fireErrPos();
			return false;
		}
		else{
			if(j.getFlotte().isEmpty())
			{
				if(mode == TypeMode.MULTI)
					j.cacheBateaux();
				else if(mode == TypeMode.SOLO && secondeFlotte)
					j.cacheBateaux();
					
				if(secondeFlotte)
					setPositionnement(false);

				if(positionnement)
					setSecondeFlotte(!secondeFlotte);

				else
					setSecondeFlotte(true);
			}
			else
				fireNextBoat(boat);
			return true;
		}

	}

	private EtatFlotte tir(int x, int y){
		Joueur j=aQuiLeTour();
		EtatFlotte tir;
		if((!(j instanceof IA) && mode == TypeMode.SOLO) || mode == TypeMode.DEMO){
			tir = j.tirIA();
		}
		else{
			tir = j.tir(x, y);
		}
		if(tir != EtatFlotte.DVISITEE){
			if(tir == EtatFlotte.FCOULE){
				setEnd(true);
			}
			if(!end)
				setSecondeFlotte(!secondeFlotte);

		}
		return tir;
	}


	public void casePressee(int x, int y) {

		if(end)
			System.out.println("the end");
		else{
			if(positionnement)
				placement(x,y,horizontal);
			else{
				if(battle==TypeBattle.ALERTE || battle==TypeBattle.ARTILLERIE){
					artillerie(x);
				}
				else
					tir(x,y);

				if(battle==TypeBattle.RADAR || battle==TypeBattle.ALERTE)
					indication(x,y);
			}
		}
	}
	
	private void artillerie(int x){
		Joueur j = aQuiLeTour();
		int y = 0;
		if(arti == 0){	
			arti = 1;
			while(arti == 1){
				j.getGrille()[x][y].arti();
				y++;
			}
		}
		else{
			arti = -1;
			j.tir(x, y);
		}
	}

	private Joueur aQuiLeTour(){
		return secondeFlotte?j2:j1;
	}

	public int getTaille() {
		return taille;
	}


	private void setSecondeFlotte(boolean secondeFlotte) {
		this.secondeFlotte = secondeFlotte;

		if(secondeFlotte){
			fireTourChange(j1.getNom());
			if(mode == TypeMode.SOLO && positionnement)
				actionIA();
		}

		else{
			fireTourChange(j2.getNom());
			if(mode == TypeMode.SOLO && !positionnement)
				actionIA();
		}
	}

	private void setPositionnement(boolean positionnement) {
		this.positionnement = positionnement;
		fireFinPose();
	}

	private void setEnd(boolean end) {
		if(this.end != end){
			this.end = end;
			if(secondeFlotte)
				fireEnd(j1.getNom());
			else
				fireEnd(j2.getNom());
		}
	}

	public void setHorizontal(boolean horizontal) {
		this.horizontal = horizontal;
		fireDirChanged(horizontal);
	}

	public boolean isHorizontal() {
		return horizontal;
	}
}
