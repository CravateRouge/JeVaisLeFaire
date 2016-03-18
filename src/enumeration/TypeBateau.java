package enumeration;

public enum TypeBateau {
PORTEAVION ("Porte-avion",5),
CUIRASSE1 ("Cuirassé 1",4),
CUIRASSE2 ("Cuirassé 2",4),
SOUSMARIN ("Sous-marin",3),
ZODIAC ("Zodiac",2);
	
	private final String name;
	private final int taille;

	private TypeBateau(final String name, final int taille){
		this.name=name;
		this.taille=taille;
	}
	
	@Override
	public String toString(){
		return name;
	}

	public int getTaille() {
		return taille;
	}

}
