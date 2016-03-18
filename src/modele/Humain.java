package modele;

import java.util.List;

import enumeration.TypeBateau;

public class Humain extends Joueur {

	public Humain(String n, List<TypeBateau> tempFlotte) {
		super(n, tempFlotte);
	}
	
	public Humain(Joueur j){
		super(j);
	}

}
