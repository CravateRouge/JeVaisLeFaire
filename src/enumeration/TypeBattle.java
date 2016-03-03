package enumeration;

public enum TypeBattle {
CLASSIQUE ("Bataille classique"),
RADAR ("Mission radar"),
ARTILLERIE ("Opération artillerie"),
ALERTE ("Alerte rouge");
	
	private final String name;

	private TypeBattle(final String name){
		this.name=name;
	}
	
	@Override
	public String toString(){
		return name;
	}
}
