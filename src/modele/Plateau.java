package modele;

import enumeration.EtatFlotte;
import enumeration.TypeBateau;
import enumeration.TypeBattle;

public class Plateau extends AbstractPlateau{

	private Case[][] grille1;
	private Case[][] grille2;
	private Joueur joueur1;
	private Joueur joueur2;
	private boolean j1turn = true;
	private TypeBattle battle;

	/** creer le plateau avec 2 IA pour le mode demo
	 * @param battle */
	private Plateau(TypeBattle battle){
		super();
		this.battle=battle;
		initGrilles();
	}
	public Plateau(IA i1, IA i2, TypeBattle battle){
		this(battle);
		joueur1 = new IA(i1);
		joueur2 = new IA(i2);
	}

	/**creer le plateau avec 1 humain et 1 IA pour le mode 1 joueur*/
	public Plateau(Humain h, IA i, TypeBattle battle){
		this(battle);
		joueur1 = new Humain(h);
		joueur2 = new IA(i);		
	}

	/** creer le plateau avec 2 humains pour le mode 2 joueurs*/
	public Plateau(Humain h1, Humain h2, TypeBattle battle){
		this(battle);
		joueur1 = new Humain(h1);
		joueur2 = new Humain(h2);		
	}
	
	/** Crée 2 grilles 10*10 */
	private void initGrilles(){
		int taille = 10;
		grille1=new Case[taille][taille];
		grille2=new Case[taille][taille];
		
		for (int x = 0; x < taille; x++) {
			for (int y = 0; y < taille; y++) {
				grille1[x][y] = new Case(x, y);
				grille2[x][y] = new Case(x, y);
			}
		}
		
		fireInitGrilles(taille);
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
		/*Revoir le système de tour par tour (les cliques sur une grille inactive ne doivent
		pas être pris en compte)*/

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
	
	public EtatFlotte tirIA(){
			Case test = new Case(((IA) joueur2).tirIA());
			EtatFlotte retour = tir(test.getX(),test.getY());
		if( retour == EtatFlotte.COULE || (retour == EtatFlotte.SAUVE && ((IA)joueur2).getSens() == 'n') ){
			((IA) joueur2).resetMem();
			((IA) joueur2).resetSens();
		}
		else{
			if(retour == EtatFlotte.TOUCHE && ((IA)joueur2).getDerniere() == null){
				((IA)joueur2).setDerniere(test);
			}
			else if(retour == EtatFlotte.SAUVE && ((IA)joueur2).getSens() != 'n'){
				((IA)joueur2).changeDirection();
				((IA)joueur2).retourMem();
			}
		}
		
		return retour;
	}

	/** Tir sur une grille en fonction du tour du joueur
	 * @param x
	 * 		l'abscisse de la case
	 * @param y
	 *  	l'ordonnee de la case
	 *  @return DVISITEE si la case a deja ete visitee,
	 *   SAUVE si elle n'a rien touchee,
	 *   TOUCHE si touche et COULE si coule
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
