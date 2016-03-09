package modele;

import enumeration.TypeMode;
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

	protected void fireModeChoisi(TypeMode mode){
		for(ModeListener listener : listeners.getListeners(ModeListener.class))
			listener.modeChoisi(mode);
	}
	
	public void addInitListener(InitListener listener){
		listeners.add(InitListener.class, listener);
	}

	public void removeInitListener(InitListener listener){
		listeners.remove(InitListener.class, listener);
	}

	protected void fireInitGame() {
		for(InitListener listener : listeners.getListeners(InitListener.class))
			listener.initGame();
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
