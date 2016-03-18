package modele;

import java.util.List;

import enumeration.EtatFlotte;
import enumeration.TypeBateau;
import enumeration.TypeBattle;
import enumeration.TypeMode;

public class Plateau extends AbstractPlateau{

	private Case[][] grille1;
	private Case[][] grille2;
	private Joueur joueur1;
	private Joueur joueur2;
	private boolean j1turn = true;
	private TypeBattle battle;

	/** creer le plateau avec 2 IA pour le mode demo
	 * @param taille 
	 * @param battle */
	public Plateau(String j1Name, String j2Name, int taille){
		super();
		battle=TypeBattle.CLASSIQUE;
		joueur1 = new IA(j1Name);
		joueur2 = new IA(j2Name);
		initGrilles(taille);
	}

	public void setGame(TypeMode mode, TypeBattle battle, String j1Name, String j2Name,
			List<TypeBateau> j1Flotte, List<TypeBateau> j2Flotte) {
		switch (mode) {
		case SOLO:
			joueur1 = new Humain(j1Name,j1Flotte);
			joueur2 = new IA(j2Name);
			break;
			
		case MULTI:
			joueur1 = new Humain(j1Name, j1Flotte);
			joueur2 = new Humain(j2Name, j2Flotte);
			break;
			
		default:
			break;
		}
		this.battle=battle;
	}
	
	/** Crée 2 grilles 10*10 
	 * @param taille2 */
	private void initGrilles(int taille){
		grille1=new Case[taille][taille];
		grille2=new Case[taille][taille];
		
		for (int x = 0; x < taille; x++) {
			for (int y = 0; y < taille; y++) {
				grille1[x][y] = new Case(x, y);
				grille2[x][y] = new Case(x, y);
			}
		}
		
	}

	/** Passe au tour du joueur suivant */
	public void setTurn(){
		this.j1turn = !j1turn;
	}

	public boolean prePoseBateau(Case c, boolean horizontal, TypeBateau bateau){
		boolean rep=false;
		Case[][] grille= aQuiLeTourG();
		Joueur joueur= aQuiLeTourJ();
		/*Revoir le système de tour par tour (les cliques sur une grille inactive ne doivent
		pas être pris en compte)*/



		if(horizontal)
			for(int i = 0; i<bateau.getTaille(); i++){
				grille[c.getX()+i][c.getY()].setOccupee();
				rep=rep && joueur.poseBateau(grille[c.getX()+i][c.getY()], bateau);
			}

		else
			for(int i = 0; i<bateau.getTaille(); i++){
				grille[c.getX()][c.getY()+i].setOccupee();
				rep=rep && joueur.poseBateau(grille[c.getX()][c.getY()+i], bateau);
			}

		if(rep){
			joueur.setTempFlotte();
			bateau=joueur.getTempFlotte();
		}
		
		firePoseBateau(bateau);
		return rep;
	}
	
	public void tir(int x, int y){
			switch (battle) {
			case RADAR:
				if(tireLeCanon(x, y) == EtatFlotte.SAUVE)
					plusProcheVoisin(new Case(x,y));
				break;

			case ARTILLERIE:
				//demander au prof des détails sur le déroulement de ce type de bataille
				break;

			case ALERTE:
				//demander au prof des détails sur le déroulement de ce type de bataille
				break;

			default:
				break;
			}
	}
	
	private int plusProcheVoisin(Case origine) {
		Joueur j=aQuiLeTourJ();
		Case proche = null;
		for (Case voisine : j.getFlotte().keySet()) {
			if(proche == null || (proche != null && origine.getDistance(voisine) < origine.getDistance(proche)))
				proche = voisine;
		}
		
		return origine.getDistance(proche);		
	}
	public EtatFlotte tirIA(){
			Case test = new Case(((IA) joueur2).tirIA());
			EtatFlotte retour = tireLeCanon(test.getX(),test.getY());
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
	public EtatFlotte tireLeCanon(int x, int y){
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
