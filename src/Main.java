import javax.swing.SwingUtilities;

import controleur.BatailleControleur;
import modele.Plateau;
import vue.BatailleFenetre;

public class Main {

	public static void main(String[] args) {
		new BatailleControleur(new Plateau());

	}

}
