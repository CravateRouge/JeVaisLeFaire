package vue;

import javax.swing.JTextArea;

import enumeration.TypeBateau;
import listener.EvenementListener;

public class Console extends JTextArea implements EvenementListener{

	public Console(int rows, int columns) {
		super(rows,columns);
		setEditable(false);
		
		append("Pour poser un bateau clique sur la case en haut à gauche afin de selectionner"
				+ " sa direction puis appuye sur la case désirée.\n\n");
	}
	
	@Override
	public void nextBoat(TypeBateau boat) {
		append("\t- "+boat+" -> "+boat.getTaille()+"\n");
	}

	@Override
	public void erreurPosition() {
		append("Erreur de positionnement!!\n");
	}

	@Override
	public void tourChange(String nom) {
		append("C'est à "+nom+" de jouer\n");
		
	}

	@Override
	public void finPose() {
		append("Que la bataille commence, tirez moussaillons!!\n\n");
	}

	@Override
	public void end(String jWin) {
		append("Fin du jeu "+jWin+" a gagné! Félicitations");
	}

	@Override
	public void dirChanged(boolean horizontal) {
		append("Direction ");
		if(horizontal)
			append("horizontale\n");
		else
			append("verticale\n");
		
	}

}
