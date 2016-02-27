package modele;

import listener.ModeListener;

public class Menu extends AbstractModel{

	private String mode;
	private String battle;

	public Menu(){
		super();
	}
	
	public void setMode(String mode) {
		this.mode=mode;
		fireModeChoisi(mode);
	}

	public void setBattle(String battle) {
		this.battle=battle;

	}
	
	public void addModeListener(ModeListener listener){
		listeners.add(ModeListener.class, listener);
	}
	
	public void removeModeListener(ModeListener listener){
		listeners.remove(ModeListener.class, listener);
	}

	public void fireModeChoisi(String mode){
		for(ModeListener listener : listeners.getListeners(ModeListener.class))
			listener.modeChoisi(mode);
	}
}
