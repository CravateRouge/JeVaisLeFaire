package ressources;

public class Case {

	private int x;
	private int y;
	private boolean testee = false;
	
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
	public boolean getEtat(){
		return this.testee;
	}
}
