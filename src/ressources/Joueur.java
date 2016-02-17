package ressources;

import java.util.HashMap;
import java.util.Map;

public abstract class Joueur {
	
	private String nom;
	private Map<Integer,Bateau> flotte;
	
	public Joueur(String n){
		this.nom = n;
		flotte = new HashMap<Integer,Bateau>();
	}
	
	public String getNom(){
		return this.nom;
	}
	

}
