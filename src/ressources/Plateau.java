package ressources;

public class Plateau {

	private Case[][] grille1;
	private Case[][] grille2;
	
	/** Creer un plateau avec 2 grilles 10*10 */
	public Plateau(){
		this.grille1 = new Case[10][10];
		this.grille2 = new Case[10][10];
		for(int i = 0; i<10; i++){
			for(int j = 0; j<10; j++){
				this.grille1[i][j] = new Case(i,j);
				this.grille2[i][j] = new Case(i,j);
			}
		}
	}
}
