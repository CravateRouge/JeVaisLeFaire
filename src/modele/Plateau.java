package modele;

import enumeration.EtatFlotte;
import enumeration.TypeBateau;

public class Plateau {

	private Case[][] grille1;
	private Case[][] grille2;
	private Joueur joueur1;
	private Joueur joueur2;
	private boolean j1turn = true;

	/** Creer un plateau avec 2 grilles 10*10 */
	/*@Prof Peut-on se permettre un constructeur privé avec 
	 * des valeurs non initialisées?*/
	private Plateau(){
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

	public boolean prePoseBateau(Case c, boolean horizontal, TypeBateau bateau){
		int taille;
		boolean rep=false;
		Case[][] grille= aQuiLeTourG();
		Joueur joueur= aQuiLeTourJ();

		switch(bateau){
		case PORTEAVION:
			taille = 5;
			break;
		case CUIRASSE1 :
			taille = 4;
			break;
		case CUIRASSE2 :
			taille = 4;
			break;
		case SOUSMARIN:
			taille = 3;
			break;
		case ZODIAC:
			taille = 2;
			break;
		default:
			taille = 0;
		}


		if(horizontal)
			for(int i = 0; i<taille; i++){
				grille[c.getX()+i][c.getY()].setOccupee();
				rep=rep && joueur.poseBateau(grille[c.getX()+i][c.getY()], bateau);
			}

		else
			for(int i = 0; i<taille; i++){
				grille[c.getX()][c.getY()+i].setOccupee();
				rep=rep && joueur.poseBateau(grille[c.getX()][c.getY()+i], bateau);
			}

		return rep;
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
	public EtatFlotte tir(int x, int y){
		Case[][] grille=aQuiLeTourG();
		Joueur j=aQuiLeTourJ();
		/*@Débattre lorsqu'un joueur tire sur une case déjà visitée
		 * le laisse t-on rejouer ou pas?
		 */
		this.setTurn();
		if(grille[x][y].isVisitee())
			return EtatFlotte.DVISITEE;//la case a deja ete visitee

		grille[x][y].setVisitee();
		if(grille[x][y].isOccupee())
			return j.degat(grille[x][y]);

		return EtatFlotte.SAUVE; //tombe dans l'eau
	}

	private Case[][] aQuiLeTourG(){
		return j1turn? grille2 : grille1;
	}

	private Joueur aQuiLeTourJ(){
		return j1turn? joueur2 : joueur1;
	}

}
