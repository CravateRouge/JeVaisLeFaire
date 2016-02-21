package ressources;

import java.util.HashMap;
import java.util.Map;

public abstract class Joueur {
	
	private String nom;
	private Map<Case,String> flotte;
	
	/**
	 * Le joueur va etre cree grace a un nom et sa flotte sera constituer par la suite.
	 * @param n
	 * Le nom du joueur 
	 */
	public Joueur(String n){
		this.nom = n;
		this.flotte = new HashMap<Case,String>();
	}
	
	public Joueur(Joueur j){
		this.nom = j.getNom();
		this.flotte = j.getFlotte();
	}
	
	public String getNom(){
		return this.nom;
	}
	public Map<Case,String> getFlotte(){
		return this.flotte;
	}
	
	/**
	 * Permet de constituer la flotte du joueur en remplissant la Map.
	 * @param c
	 * La case dans laquelle va se trouver le bateau
	 * @param bateau
	 * Le nom du bateau
	 */
	public void poseBateau(Case c, String bateau){
		flotte.put(c, bateau);
	}

	/**
	 * Inflige le degat au bateau.
	 * @param c
	 * La case qui a ete touchee
	 * @return
	 * True si le bateau est touche ou false s'il est coule
	 */
	public boolean degat(Case c) {
		return flotte.containsValue(flotte.remove(c));
	}

}
