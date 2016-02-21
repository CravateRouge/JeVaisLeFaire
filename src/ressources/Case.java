package ressources;

public class Case {

	private int x;
	private int y;
	private boolean visitee = false;
	private boolean occupee = false;
	
	/**Constructeur avec les coordonnees */
	public Case(int x, int y){
		this.x = x;
		this.y = y;
	}
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
	public boolean isVisite(){
		return this.visitee;
	}
	
	public boolean isOccupee(){
		return this.occupee;
	}
	
	public void setOccupee() {
		this.occupee = !occupee;
	}
	public void setVisite(){
		this.visitee = !visitee;
	}
}
