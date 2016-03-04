package modele;

import java.util.ArrayList;
import java.util.List;

public class IA extends Joueur {
	private List<Case> tirs;
	private Case derniere;
	private Case memoire;
	private char sens;
	private boolean direction;

	public IA(String n) {
		super(n);
		
		this.tirs = new ArrayList<Case>();
		this.sens = 'n';
		this.derniere = new Case(0,0);
	}
	
	public IA(Joueur j){
		super(j);
	}
	
	public Case tirIA(){
		boolean fini = false;
		int size = tirs.size();
		do{
			int a = (int) (Math.random() * ((11 - 1) + 1));
			int b = (int) (Math.random() * ((11 - 1) + 1));
			for(int i = 0; i<size;i++){
				if(a !=tirs.get(i).getX() && b != tirs.get(i).getY()){
					if(this.memoire == null){
						this.derniere = new Case(a,b);
						this.tirs.add(new Case(a,b));
						return this.memoire = new Case(a,b);
					}
					if(this.derniere == null){
						if(this.sens == 'n'){
							
						}
						else{
							if(this.direction=!this.direction){
								this.derniere = new Case(new Case(this.derniere.getX()-1,this.derniere.getY()));
								this.tirs.add(this.derniere);
								return new Case(this.derniere);
							}
							else{
								this.derniere = new Case(new Case(this.derniere.getX()+1,this.derniere.getY()));
								this.tirs.add(this.derniere);
								return new Case(this.derniere);
							}
						}
					}
					else{
						if(this.sens == 'n'){
							
						}
					}
				}
			}
		}while(fini);
	return new Case(0,0);
	}
	
	public Case getMem(){
		return this.memoire;
	}
	
	public void resetMem(){
		this.memoire = null;
		this.derniere = null;
	}
	

}
