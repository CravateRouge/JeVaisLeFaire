package enumeration;

public enum TypeMode {
DEMO ("Démo"),
SOLO ("Solo"),
MULTI ("Multi");
	
	private final String name;

	private TypeMode(final String name){
		this.name=name;
	}
	
	@Override
	public String toString(){
		return name;
	}
}
