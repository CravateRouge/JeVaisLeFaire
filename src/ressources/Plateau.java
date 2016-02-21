package ressources;

public class Plateau {

	private Case[][] grille1;
	private Case[][] grille2;
	private Joueur joueur1;
	private Joueur joueur2;
	private boolean j1turn = true;
	
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
	
	/** creer le plateau avec 2 IA pour le mode demo*/
	public Plateau(IA i1, IA i2){
		this();
		joueur1 = new IA(i1);
		joueur2 = new IA(i2);
	}
	
	/**creer le plateau avec 1 humain et 1 IA pour le mode 1 joueur*/
	public Plateau(Humain h, IA i){
		this();
		joueur1 = new Humain(h);
		joueur2 = new IA(i);		
	}
	
	/** creer le plateau avec 2 humains pour le mode 2 joueurs*/
	public Plateau(Humain h1, Humain h2){
		this();
		joueur1 = new Humain(h1);
		joueur2 = new Humain(h2);		
	}
	
	/** Passe au tour du joueur suivant */
	public void setTurn(){
		this.j1turn = !j1turn;
	}
	
	public boolean prePoseBateau(Case c, boolean horizontal, String bateau){
		int taille = 0;
		switch(bateau){
			case "PorteAvion":
				taille = 5;
			case "Cuirasse":
				taille = 4;
			case "SousMarin":
				taille = 3;
			case "Zodiac":
				taille = 2;
		}
		if(this.j1turn){
			if(horizontal){
				grille1[c.getX()][c.getY()].setOccupee();
				this.joueur1.poseBateau(grille1[c.getX()][c.getY()], bateau);
				for(int i = 1; i<taille; i++){
					grille1[c.getX()+i][c.getY()].setOccupee();
					this.joueur1.poseBateau(grille1[c.getX()+i][c.getY()], bateau);
				}
			}
			else{
				grille1[c.getX()][c.getY()].setOccupee();
				this.joueur1.poseBateau(grille1[c.getX()][c.getY()], bateau);
				for(int i = 1; i<taille; i++){
					grille1[c.getX()][c.getY()+i].setOccupee();
					this.joueur1.poseBateau(grille1[c.getX()][c.getY()+i], bateau);
				}
			}
		}
		else{
			if(horizontal){
				grille2[c.getX()][c.getY()].setOccupee();
				this.joueur2.poseBateau(grille2[c.getX()][c.getY()], bateau);
				for(int i = 1; i<taille; i++){
					grille2[c.getX()+i][c.getY()].setOccupee();
					this.joueur2.poseBateau(grille2[c.getX()+i][c.getY()], bateau);
				}
			}
			else{
				grille2[c.getX()][c.getY()].setOccupee();
				this.joueur2.poseBateau(grille2[c.getX()][c.getY()], bateau);
				for(int i = 1; i<taille; i++){
					grille2[c.getX()][c.getY()+i].setOccupee();
					this.joueur2.poseBateau(grille2[c.getX()][c.getY()+i], bateau);
				}
			}
		}
		return true;
	}
	
	/** Tir sur une grille en fonction du tour du joueur
	 * @param x
	 * 		l'absisce de la case
	 * @param y
	 *  	l'ordonnee de la case
	 *  @return -1 si la case a deja ete visitee,
	 *   0 si elle n'a rien touchee,
	 *   1 si touche et 2 si coule
	 */
	public int tir(int x, int y){
		if(this.j1turn){
			this.setTurn();
			if(this.grille2[x][y].isVisite()){
				return -1; //la case a deja ete visitee
			}
			this.grille2[x][y].setVisite();
			if(this.grille2[x][y].isOccupee()){
				if(joueur2.degat(this.grille2[x][y])){
					return 1; //le bateau a ete touche
				}
				else{
					return 2; //le bateau a ete coule
				}
			}
			return 0; //tombe dans l'eau
		}
		else{
			this.setTurn();
			if(this.grille1[x][y].isVisite()){
				return -1;//la case a deja ete visitee
			}
			this.grille1[x][y].setVisite();
			if(this.grille1[x][y].isOccupee()){
				if(joueur1.degat(this.grille1[x][y])){
					return 1;//le bateau a ete touche
				}
				else{
					return 2; //le bateau a ete coule
				}
			}
			return 0; //tombe dans l'eau
		}
		
	}
	
}
