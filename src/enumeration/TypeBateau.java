package enumeration;

public enum TypeBateau {
PORTEAVION ("Porte-avion"),
CUIRASSE1 ("Cuirassé 1"),
CUIRASSE2 ("Cuirassé 2"),
SOUSMARIN ("Sous-marin"),
ZODIAC ("Zodiac");
	
	private final String name;

	private TypeBateau(final String name){
		this.name=name;
	}
	
	@Override
	public String toString(){
		return name;
	}

}
