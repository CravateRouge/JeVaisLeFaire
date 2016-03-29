package modele;

import java.util.ArrayList;

import enumeration.EtatFlotte;

public class IA extends Joueur {
	
	private ArrayList<Case> tirs;
	private boolean direction;
	private char sens = 'n';
	private Case memoire = null;
	private Case derniere = null;
	
	public IA(Joueur j){
		super(j);
		tirs = new ArrayList<>();
	}
	
	public boolean tir(){
		if(memoire == null){
			int x = (int)(Math.random()*10);
			int y = (int)(Math.random()*10);
			Case test = new Case(x,y);
			if(!tirs.contains(test)){
				if(!getCase(x, y).isVisitee()){
					grille[x][y].setVisitee();
					if(warShips.remove(grille[x][y])!=null){
						if(warShips.isEmpty()){
							return EtatFlotte.FCOULE;
						}
						return EtatFlotte.TOUCHE;
					}
					return EtatFlotte.SAUVE;
				}
				return EtatFlotte.DVISITEE;g
			}
		}

		
		return true;
	}
}
