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
	
	public Case(Case c) {
		this(c.getX(),c.getY());
	}

	@Override
	public String toString() {
		return "Case [x=" + x + ", y=" + y + ", visitee=" + visitee + ", occupee=" + occupee + "]";
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
	
	public void arti(){
		fireCaseArtillerie();
	}

	public int getDistance(Case proche) {
		return (int) Math.sqrt(Math.pow(x-proche.getX(), 2) + Math.pow(y-proche.getY(), 2));
	}

	public void cache() {
		fireCaseCachee();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Case))
			return false;
		Case other = (Case) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}
	
	

}
