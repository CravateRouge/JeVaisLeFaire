package modele;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import enumeration.TypeBateau;

public class Joueur {

	private String nom;
	private Case[][] grille;
	private Set<TypeBateau> flotte=EnumSet.allOf(TypeBateau.class);
	private Map<Case, TypeBateau> warShips=new HashMap<Case, TypeBateau>();

	/**
	 * Le joueur va etre cree grace a un nom et sa flotte sera constituer par la suite.
	 * @param n
	 * Le nom du joueur 
	 */
	public Joueur(String n, int taille){
		nom = n;
		grille=new Case[taille][taille];
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
	/**
	 * Permet de constituer la flotte du joueur en remplissant la Map.
	 * @param c
	 * La case dans laquelle va se trouver le bateau
	 * @param bateau
	 * Le nom du bateau
	 */

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
			if(horizontal)
				if(grille[x+i][y].isOccupee())
					return null;
			else
				if(grille[x+i][y].isOccupee())
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

	public boolean tir(int x, int y){
		boolean vide=false;

			grille[x][y].setVisitee();
		if(warShips.remove(grille[x][y])!=null)
			vide=warShips.isEmpty();

		return vide;
	}

	public int indication(Case origine) {
		Case proche = null;
		for (Case voisine : warShips.keySet()) {
			if(proche == null || (proche != null && origine.getDistance(voisine) < origine.getDistance(proche)))
				proche = voisine;
		}
		
		return origine.getDistance(proche);	
	}

	public Set<TypeBateau> getFlotte() {
		return flotte;
	}

	/**
	 * Inflige le degat au bateau.
	 * @param c
	 * La case qui a ete touchee
	 * @return
	 * True si le bateau est touche ou false s'il est coule
	 */


}
