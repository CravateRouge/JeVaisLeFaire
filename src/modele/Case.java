package modele;

public class Case extends AbstractCase {

	private int x;
	private int y;
	private boolean visitee = false;
	private boolean occupee = false;
	
	/**Constructeur avec les coordonnees */
	public Case(int x, int y){
		super();
		
		this.x = x;
		this.y = y;
	}
	
	/*@Débattre Constructeur inutile*/
	/** Constructeur par copie */
	public Case(Case c){
		this.x = c.getX();
		this.y = c.getY();
	}
	
	public int getX(){
		return this.x;
	}
	
	public int getY(){
		return this.y;
	}
	/** Permet de savoir si la case a ete testee */
	public boolean isVisitee(){
		return this.visitee;
	}
	
	public boolean isOccupee(){
		return this.occupee;
	}
	
	public void setOccupee() {
		this.occupee = !occupee;
		fireCaseOccupee(x,y);
	}
	public void setVisitee(){
		this.visitee = !visitee;
		fireCaseVisitee(x,y);
	}

}
