package modele;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import enumeration.EtatFlotte;
import enumeration.TypeBateau;

public class Joueur {

	private String nom;
	private Case[][] grille;
	private Set<TypeBateau> flotte=EnumSet.allOf(TypeBateau.class);
	private Map<Case, TypeBateau> warShips=new HashMap<Case, TypeBateau>();
	private ArrayList<Case> tirs;
	private boolean direction;
	private boolean sens;//true si horizontal et false si vertical
	private Case memoire = null;
	private Case derniere = null;

	/**
	 * Le joueur va etre cree grace a un nom et sa flotte sera constituer par la suite.
	 * @param n
	 * Le nom du joueur 
	 */
	public Joueur(String n, int taille){
		nom = n;
		grille=new Case[taille][taille];
		tirs = new ArrayList<Case>();
	}
	
	public Joueur(Joueur j){
		this.nom = j.getNom();
		this.grille = j.getGrille();
		this.flotte = j.getFlotte();
		this.warShips = j.getWarShips();
		tirs = new ArrayList<Case>();
	}

	public Case[][] getGrille() {
		return grille;
	}

	public Map<Case, TypeBateau> getWarShips() {
		return warShips;
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

	public Set<TypeBateau> getFlotte() {
		return flotte;
	}
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
			if(horizontal){
				if(grille[x+i][y].isOccupee())
					return null;
			}
			else
				if(grille[x][y+i].isOccupee())
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

	public EtatFlotte tir(int x, int y){
		if(!getCase(x, y).isVisitee()){
			grille[x][y].setVisitee();
			if(warShips.remove(grille[x][y])!=null){
				if(warShips.isEmpty()){
					return EtatFlotte.FCOULE;
				}
				return EtatFlotte.TOUCHE;
			}
			return EtatFlotte.SAUVE;
		}
		return EtatFlotte.DVISITEE;
	}

	public int indication(Case origine) {
		Case proche = null;
		for (Case voisine : warShips.keySet()) {
			if(proche == null || (proche != null && origine.getDistance(voisine) < origine.getDistance(proche)))
				proche = voisine;
		}

		return origine.getDistance(proche);	
	}

	public void cacheBateaux() {
		for (Case c : warShips.keySet()) {
			c.cache();
		}
		
	}
	public EtatFlotte tirIA(){
		if(memoire == null){
			int x = (int)(Math.random()*10);
			int y = (int)(Math.random()*10);
			Case test = new Case(x,y);
			if(!getCase(x, y).isVisitee()){
				tirs.add(test);
				grille[x][y].setVisitee();
				if(warShips.remove(grille[x][y])!=null){
					memoire = new Case(test);
					if(warShips.isEmpty()){
						memoire = null;
						return EtatFlotte.FCOULE;
					}
					return EtatFlotte.TOUCHE;
				}
				return EtatFlotte.SAUVE;
			}
			return EtatFlotte.DVISITEE;
		}
		else{
			if(derniere == null){
				int a = 0,o = 0,x,y;
				Case testsens;
				do{
				if((int)(Math.random()+0.1)==0){
					if((int)(Math.random()+0.1)==0){a = -1;}
					else{a = 1;}
				}
				else{
					if((int)(Math.random()+0.1)==0){o = -1;}
					else{o = 1;}
				}
				x = memoire.getX()+a;
				y = memoire.getY()+o;
				 testsens = new Case(x,y);
				}while(!(x>=0 && x<10 && y>=0 && y<10));
				if(!getCase(x, y).isVisitee()){
					tirs.add(testsens);
					grille[x][y].setVisitee();
					TypeBateau b = warShips.remove(grille[x][y]);
					if(b!=null){
						derniere = new Case(testsens);
						sens = (o==1);
						direction = (a==1);
						if(!warShips.containsValue(b)){
							memoire = null;
							derniere = null;
							if(warShips.isEmpty()){
								return EtatFlotte.FCOULE;
							}
							return EtatFlotte.BCOULE;
						}
						return EtatFlotte.TOUCHE;
					}
					return EtatFlotte.SAUVE;
				}
				return EtatFlotte.DVISITEE;
			}
			else{
				System.out.println("boucle3");
				if(!sens){
					if(direction){
						int x = derniere.getX()+1;
						int y = derniere.getY();
						derniere = new Case(x,y);
						if(x>=0 && x<10 && y>=0 && y<10){
							if(!getCase(x, y).isVisitee()){
								tirs.add(memoire);
								grille[x][y].setVisitee();
								TypeBateau b = warShips.remove(grille[x][y]);
								if(b!=null){
									if(!warShips.containsValue(b)){
										memoire = null;
										derniere = null;
										if(warShips.isEmpty()){
											return EtatFlotte.FCOULE;
										}
										return EtatFlotte.BCOULE;
									}
									return EtatFlotte.TOUCHE;
								}
								derniere = new Case(memoire);
								direction = !direction;
								return EtatFlotte.SAUVE;
							}
							return EtatFlotte.DVISITEE;
						}
						derniere = new Case(memoire);
						direction = !direction;
						return EtatFlotte.DVISITEE;
					}
				else{
					int x = derniere.getX()-1;
					int y = derniere.getY();
					derniere = new Case(x,y);
					if(x>=0 && x<10 && y>=0 && y<10){
						if(!getCase(x, y).isVisitee()){
							tirs.add(memoire);
							grille[x][y].setVisitee();
							TypeBateau b = warShips.remove(grille[x][y]);
							if(b!=null){
								if(!warShips.containsValue(b)){
									memoire = null;
									derniere = null;
									if(warShips.isEmpty()){
										return EtatFlotte.FCOULE;
									}
									return EtatFlotte.BCOULE;
								}
								return EtatFlotte.TOUCHE;
							}
							derniere = new Case(memoire);
							direction = !direction;
							return EtatFlotte.SAUVE;
						}
						return EtatFlotte.DVISITEE;
					}
					derniere = new Case(memoire);
					direction = !direction;
					return EtatFlotte.DVISITEE;
				}
				}
				else{
					if(direction){
						int x = derniere.getX();
						int y = derniere.getY()-1;
						derniere = new Case(x,y);
						System.out.println(derniere);
						if(x>=0 && x<10 && y>=0 && y<10){
							if(!getCase(x, y).isVisitee()){
								tirs.add(memoire);
								grille[x][y].setVisitee();
								TypeBateau b = warShips.remove(grille[x][y]);
								if(b!=null){
									if(!warShips.containsValue(b)){
										System.out.println("Pas normal");
										memoire = null;
										derniere = null;
										if(warShips.isEmpty()){
											return EtatFlotte.FCOULE;
										}
										return EtatFlotte.BCOULE;
									}
									return EtatFlotte.TOUCHE;
								}
								derniere = new Case(memoire);
								direction = !direction;
								return EtatFlotte.SAUVE;
							}
							return EtatFlotte.DVISITEE;
						}
						derniere = new Case(memoire);
						direction = !direction;
						return EtatFlotte.DVISITEE;
					}
					else{
						int x = derniere.getX();
						int y = derniere.getY()+1;
						derniere = new Case(x,y);
						System.out.println(derniere);
						if(x>=0 && x<10 && y>=0 && y<10){
							if(!getCase(x, y).isVisitee()){
								tirs.add(memoire);
								grille[x][y].setVisitee();
								TypeBateau b = warShips.remove(grille[x][y]);
								if(b!=null){
									if(!warShips.containsValue(b)){
										System.out.println("Pas normal");
										memoire = null;
										derniere = null;
										if(warShips.isEmpty()){
											return EtatFlotte.FCOULE;
										}
										return EtatFlotte.BCOULE;
									}
									return EtatFlotte.TOUCHE;
								}
								derniere = new Case(memoire);
								direction = !direction;
								return EtatFlotte.SAUVE;
							}
							return EtatFlotte.DVISITEE;
						}
					}
					derniere = new Case(memoire);
					direction = !direction;
					return EtatFlotte.DVISITEE;
				}

			}
		}
	}

}
