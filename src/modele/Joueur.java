package modele;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import enumeration.EtatFlotte;
import enumeration.TypeBateau;

public abstract class Joueur {
	
	private String nom;
	private Map<Case,TypeBateau> flotte;
	private List<TypeBateau> tempFlotte;
	
	/**
	 * Le joueur va etre cree grace a un nom et sa flotte sera constituer par la suite.
	 * @param n
	 * Le nom du joueur 
	 */
	public Joueur(String n, List<TypeBateau> tempFlotte){
		this.nom = n;
		this.flotte = new HashMap<Case,TypeBateau>();
		this.tempFlotte=new ArrayList<TypeBateau>(tempFlotte);
	}
	
	/*@Débattre Constructeur inutile?*/
	public Joueur(Joueur j){
		this.nom = j.getNom();
		this.flotte = j.getFlotte();
	}
	
	public String getNom(){
		return this.nom;
	}
	
	/*@Débattre Getter inutile?*/
	public Map<Case,TypeBateau> getFlotte(){
		return this.flotte;
	}
	
	/**
	 * Permet de constituer la flotte du joueur en remplissant la Map.
	 * @param c
	 * La case dans laquelle va se trouver le bateau
	 * @param bateau
	 * Le nom du bateau
	 */
	public boolean poseBateau(Case c, TypeBateau bateau){
		return flotte.put(c, bateau) == null;
	}

	/**
	 * Inflige le degat au bateau.
	 * @param c
	 * La case qui a ete touchee
	 * @return
	 * True si le bateau est touche ou false s'il est coule
	 */
	public EtatFlotte degat(Case c) {
		return flotte.containsValue(flotte.remove(c))? EtatFlotte.TOUCHE : flotte.isEmpty()? EtatFlotte.COULE : EtatFlotte.BCOULE;
	}

	public TypeBateau getTempFlotte() {
		return tempFlotte.get(0);
	}

	public void setTempFlotte() {
		this.tempFlotte.remove(0);
	}

}
