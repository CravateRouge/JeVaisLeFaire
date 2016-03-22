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
		this.occupee = true;
		fireCaseOccupee();
	}
	public void setVisitee(){
		this.visitee = true;
		fireCaseVisitee(visitee && occupee);
	}

	public int getDistance(Case proche) {
		return (int) Math.sqrt(Math.pow(x-proche.getX(), 2) + Math.pow(y-proche.getY(), 2));
	}

	public void cache() {
		fireCaseCachee();
		
	}

}
