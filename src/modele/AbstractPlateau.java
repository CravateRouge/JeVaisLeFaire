package modele;

import enumeration.TypeBateau;
import enumeration.TypeMode;
import listener.EvenementListener;
import listener.MenuListener;

public class AbstractPlateau extends AbstractModel {

	public AbstractPlateau() {
		super();
	}
	
	public void addMenuListener(MenuListener listener){
		listeners.add(MenuListener.class, listener);
	}
	
	public void removeMenuListener(MenuListener listener){
		listeners.remove(MenuListener.class, listener);
	}

	protected void fireModeChanged(TypeMode mode){
		for(MenuListener listener : listeners.getListeners(MenuListener.class))
			listener.modeChoisi(mode);
	}
	
	protected void fireBattleChanged() {
		for(MenuListener listener : listeners.getListeners(MenuListener.class))
			listener.battleChoisie();
	}
	
	protected void fireJ1NameChanged() {
		for(MenuListener listener : listeners.getListeners(MenuListener.class))
			listener.j1NameChoisi();
	}

	protected void fireStartGame(String j1Nom, String j2Nom, TypeBateau boat) {
		for(MenuListener listener : listeners.getListeners(MenuListener.class))
			listener.initGame(j1Nom,j2Nom,boat);
	}
	
	public void addEvenementListener(EvenementListener listener){
		listeners.add(EvenementListener.class, listener);
	}

	public void removeEvenementListener(EvenementListener listener){
		listeners.remove(EvenementListener.class, listener);
	}

	protected void fireErrPos() {
		for(EvenementListener listener : listeners.getListeners(EvenementListener.class))
			listener.erreurPosition();
	}
	
	protected void fireNextBoat(TypeBateau boat) {
		for(EvenementListener listener : listeners.getListeners(EvenementListener.class))
			listener.nextBoat(boat);
	}
	
	protected void fireTourChange(String nom) {
		for(EvenementListener listener : listeners.getListeners(EvenementListener.class))
			listener.tourChange(nom);
	}
	
	protected void fireFinPose() {
		for(EvenementListener listener : listeners.getListeners(EvenementListener.class))
			listener.finPose();
	}
	
	protected void fireEnd(String jWin) {
		for(EvenementListener listener : listeners.getListeners(EvenementListener.class))
			listener.end(jWin);
	}
	
	protected void fireDirChanged(boolean horizontal) {
		for(EvenementListener listener : listeners.getListeners(EvenementListener.class))
			listener.dirChanged(horizontal);
	}
	
	
}
