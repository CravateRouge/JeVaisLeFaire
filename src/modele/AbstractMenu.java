package modele;

import enumeration.TypeMode;
import listener.BattleListener;
import listener.InitListener;
import listener.J1NameListener;
import listener.ModeListener;

public class AbstractMenu extends AbstractModel {

	public AbstractMenu() {
		super();
	}
	
	public void addModeListener(ModeListener listener){
		listeners.add(ModeListener.class, listener);
	}
	
	public void removeModeListener(ModeListener listener){
		listeners.remove(ModeListener.class, listener);
	}

	protected void fireModeChanged(TypeMode mode){
		for(ModeListener listener : listeners.getListeners(ModeListener.class))
			listener.modeChoisi(mode);
	}

	public void addBattleListener(BattleListener listener){
		listeners.add(BattleListener.class, listener);
	}
	
	public void removeBattleListener(BattleListener listener){
		listeners.remove(BattleListener.class, listener);
	}
	
	protected void fireBattleChanged() {
		for(BattleListener listener : listeners.getListeners(BattleListener.class))
			listener.battleChoisie();
	}
	
	public void addJ1NameListener(J1NameListener listener){
		listeners.add(J1NameListener.class, listener);
	}
	
	public void removeJ1NameListener(J1NameListener listener){
		listeners.remove(J1NameListener.class, listener);
	}
	
	protected void fireJ1NameChanged() {
		for(J1NameListener listener : listeners.getListeners(J1NameListener.class))
			listener.J1NameChoisi();
	}
	
	
	public void addInitListener(InitListener listener){
		listeners.add(InitListener.class, listener);
	}

	public void removeInitListener(InitListener listener){
		listeners.remove(InitListener.class, listener);
	}

	protected void fireStartGame(String j1Nom, String j2Nom) {
		for(InitListener listener : listeners.getListeners(InitListener.class))
			listener.initGame(j1Nom,j2Nom);
	}

}
