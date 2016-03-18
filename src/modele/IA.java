package modele;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import enumeration.TypeBateau;

public class IA extends Joueur {
	private List<Case> tirs;
	private Case derniere;
	private Case memoire;
	private char sens;
	private boolean direction;

	public IA(String n) {
		super(n, new ArrayList<TypeBateau>(Arrays.asList(TypeBateau.values())));
		
		this.tirs = new ArrayList<Case>();
		this.sens = 'n';
		this.derniere = new Case(0,0);
	}
	
	public IA(Joueur j){
		super(j);
	}
	
	public Case tirIA(){
		int size = tirs.size();
		if(this.memoire == null){
			boolean pasfini = true;
			while(pasfini){
				int x = (int) (Math.random() * (11));
				int y = (int) (Math.random() * (11));
				for(int i = 0; i<size;i++){
					if(x !=tirs.get(i).getX() && y != tirs.get(i).getY()){
							this.tirs.add(new Case(x,y));
							return this.memoire = new Case(x,y);
					}
				}
			}
		}
		else{
			if(this.sens == 'n'){
				int a = (int) (Math.random() * ((1)+1));
				int o = (int) (Math.random() * ((1)+1));
				if(a == 0){
					this.sens = 'v';
					if(o == 0){
						this.direction = true;
						this.tirs.add(new Case(this.memoire.getX(),this.memoire.getY()-1));
						return new Case(this.memoire.getX(),this.memoire.getY()-1);
					}
					else{
						this.direction = false;
						this.tirs.add(new Case(this.memoire.getX(),this.memoire.getY()+1));
						return new Case(this.memoire.getX(),this.memoire.getY()+1);
					}
				}
				else{
					this.sens = 'h';
					if(o == 0){
						this.direction = true;
						this.tirs.add(new Case(this.memoire.getX()-1,this.memoire.getY()));
						return new Case(this.memoire.getX()-1,this.memoire.getY());
					}
					else{
						this.direction = false;
						this.tirs.add(new Case(this.derniere.getX()+1,this.derniere.getY()));
						return new Case(this.derniere.getX()+1,this.derniere.getY());
					}
				}	
			}
			if(this.sens == 'h'){
				if(this.direction){
					this.derniere = new Case(this.derniere.getX()-1,this.derniere.getY());
					this.tirs.add(this.derniere);
					return new Case(this.derniere.getX()-1,this.derniere.getY());
				}
				else{
					this.derniere = new Case(this.derniere.getX()+1,this.derniere.getY());
					this.tirs.add(this.derniere);
					return new Case(this.derniere);
				}
			}
			else{
				if(this.direction){
					this.derniere = new Case(this.derniere.getX(),this.derniere.getY()-1);
					this.tirs.add(this.derniere);
					return new Case(this.derniere);
				}
				else{
					this.derniere = new Case(this.derniere.getX(),this.derniere.getY()+1);
					this.tirs.add(this.derniere);
					return new Case(this.derniere);
				}
			}
		}
		return this.derniere;
	}
	
	public Case getMem(){
		return this.memoire;
	}
	
	public void resetMem(){
		this.memoire = null;
		this.derniere = null;
	}
	
	public char getSens(){
		return this.sens;
	}
	
	public void resetSens(){
		this.sens = 'n';
	}
	
	public void changeDirection(){
		this.direction =! this.direction;
	}
	
	public void retourMem(){
		this.derniere = new Case(this.memoire);
	}
	
	public void resetDerniere(){
		this.derniere = null;
	}
	
	public Case getDerniere(){
		return this.derniere;
	}
	
	public void setDerniere(Case c){
		this.derniere = new Case(c);
	}
}
