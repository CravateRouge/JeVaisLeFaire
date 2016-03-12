package modele;

import listener.GrilleListener;

public abstract class AbstractPlateau extends AbstractModel {

	public AbstractPlateau() {
		super();
	}
	
	public void addGrilleListener(GrilleListener listener){
		listeners.add(GrilleListener.class, listener);
	}
	
	public void removeGrilleListener(GrilleListener listener){
		listeners.remove(GrilleListener.class, listener);
	}
	
	protected void fireInitGrilles(int taille){
		for (GrilleListener listener : listeners.getListeners(GrilleListener.class)) {
			listener.initGrilles(taille);
		}
	}

}
