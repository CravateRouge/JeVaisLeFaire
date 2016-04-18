package listener;

import java.util.EventListener;

import enumeration.TypeBateau;

public interface EvenementListener extends EventListener {

	public void nextBoat(TypeBateau boat);

	public void erreurPosition();

	public void finPose();

	public void dirChanged(boolean horizontal);

	public void end(String jWin);

	public void tourChange(String nom);

	public void indication(int indic);
}
