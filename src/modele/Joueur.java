package modele;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import enumeration.EtatFlotte;
import enumeration.TypeBateau;

public class Joueur {

	protected String nom;
	protected Case[][] grille;
	protected Set<TypeBateau> flotte=EnumSet.allOf(TypeBateau.class);
	protected Map<Case, TypeBateau> warShips=new HashMap<Case, TypeBateau>();

	/**
	 * Le joueur va etre cree grace a un nom et sa flotte sera constituer par la suite.
	 * @param n
	 * Le nom du joueur 
	 */
	public Joueur(String n, int taille){
		nom = n;
		grille=new Case[taille][taille];
	}
	
	public Joueur(Joueur j){
		this.nom = j.getNom();
		this.grille = j.getGrille();
		this.flotte = j.getFlotte();
		this.warShips = j.getWarShips();
	}

	public Case[][] getGrille() {
		return grille;
	}

	public Map<Case, TypeBateau> getWarShips() {
		return warShips;
	}

	public boolean removeShip(TypeBateau boat) {
		boolean rep=false;

		flotte.remove(boat);

		if(rep=flotte.isEmpty())
			flotte.add(boat);

		return rep;
	}

	public void addShip(TypeBateau boat) {
		flotte.add(boat);
	}

	public Set<TypeBateau> getFlotte() {
		return flotte;
	}
	public Case getCase(int x, int y) {
		return grille[x][y];
	}

	public Case setGrille(int x, int y) {
		return grille[x][y]=new Case(x, y);
	}

	public void setNom(String tmpNom) {
		if(!tmpNom.equals(""))
			nom=tmpNom;
	}

	public String getNom() {
		return nom;
	} 

	public TypeBateau placement(int x, int y, boolean horizontal){
		int tmp;
		TypeBateau currentBoat=nextBoat();

		if(horizontal)
			tmp=x;
		else
			tmp=y;

		if(tmp>grille.length-currentBoat.getTaille())
			return null;

		for (int i = 0; i < currentBoat.getTaille(); i++) {
			if(horizontal){
				if(grille[x+i][y].isOccupee())
					return null;
			}
			else
				if(grille[x][y+i].isOccupee())
					return null;
		}

		for (int i = 0; i < currentBoat.getTaille(); i++) {
			grille[x][y].setOccupee();
			warShips.put(grille[x][y], currentBoat);
			if(horizontal)
				x++;
			else
				y++;
		}

		flotte.remove(currentBoat);
		return nextBoat();

	}

	public TypeBateau nextBoat() {
		TypeBateau currentBoat= TypeBateau.ZODIAC;
		for (TypeBateau boat : flotte) {
			if(currentBoat.getTaille()<boat.getTaille())
				currentBoat=boat;
		}
		return currentBoat;
	}

	public EtatFlotte tir(int x, int y){
		
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
		return EtatFlotte.DVISITEE;
	}

	public int indication(Case origine) {
		Case proche = null;
		for (Case voisine : warShips.keySet()) {
			if(proche == null || (proche != null && origine.getDistance(voisine) < origine.getDistance(proche)))
				proche = voisine;
		}

		return origine.getDistance(proche);	
	}

	public void cacheBateaux() {
		for (Case c : warShips.keySet()) {
			c.cache();
		}
		
	}

	/**
	 * Inflige le degat au bateau.
	 * @param c
	 * La case qui a ete touchee
	 * @return
	 * True si le bateau est touche ou false s'il est coule
	 */


}
