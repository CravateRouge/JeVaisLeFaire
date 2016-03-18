package modele;

import enumeration.TypeBateau;
import enumeration.TypeBattle;
import listener.BattleListener;
import listener.FlotteListener;
import listener.InitListener;
import listener.ModeListener;

public class AbstractMenu extends AbstractModel {

	public AbstractMenu() {
		super();
	}

	public void addBattleListener(BattleListener listener){
		listeners.add(BattleListener.class, listener);
	}
	
	public void removeBattleListener(BattleListener listener){
		listeners.remove(BattleListener.class, listener);
	}
	
	protected void fireBattleChoisie() {
		for(BattleListener listener : listeners.getListeners(BattleListener.class))
			listener.battleChoisie();
	}

	public void addModeListener(ModeListener listener){
		listeners.add(ModeListener.class, listener);
	}
	
	public void removeModeListener(ModeListener listener){
		listeners.remove(ModeListener.class, listener);
	}

	protected void fireModeChoisi(){
		for(ModeListener listener : listeners.getListeners(ModeListener.class))
			listener.modeChoisi();
	}
	
	public void addInitListener(InitListener listener){
		listeners.add(InitListener.class, listener);
	}

	public void removeInitListener(InitListener listener){
		listeners.remove(InitListener.class, listener);
	}

	protected void fireInitGame(TypeBattle battle, String j1Name, String j2Name, int taille, TypeBateau currentBoat) {
		for(InitListener listener : listeners.getListeners(InitListener.class))
			listener.initGame(battle, j1Name, j2Name, taille, currentBoat);
	}
	
	public void addFlotteListener(FlotteListener listener){
		listeners.add(FlotteListener.class, listener);
	}

	public void removeFlotteListener(FlotteListener listener){
		listeners.remove(FlotteListener.class, listener);
	}
	
	protected void fireFlotteChoisie() {
		for(FlotteListener listener : listeners.getListeners(FlotteListener.class))
			listener.flotteChoisie();
	}
}
