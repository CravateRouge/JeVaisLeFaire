package ressources;


public abstract class Bateau {
	
	private Case[] compartiments;
	private boolean coule = false;
	
	/** Constructeur avec la taille du bateau (utilisé pour les sous classes de bateau) */
	public Bateau(int n){
		this.compartiments  = new Case[n];
	}
	
	/** Constructeur par copie */
	public Bateau(Bateau b){
		this.compartiments = b.getComp();
	}
	
	public boolean getCoule(){
		return coule;
	}
	
	public Case[] getComp(){
		return this.compartiments;
	}

	
	/** Calcul l'etat de "coulé" du bateau */
	public boolean isCoule(){
		boolean b = true;
		for(int i = 0; i<this.compartiments.length; i++){
			b = b && this.compartiments[i].getEtat();
		}
		return this.coule = b;
	}
	
	
}
