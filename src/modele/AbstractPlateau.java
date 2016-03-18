package modele;

import enumeration.TypeBateau;
import listener.GrilleListener;
import listener.PoseBateauListener;

public abstract class AbstractPlateau extends AbstractModel {

	public AbstractPlateau() {
		super();
	}
	
	public void addPoseBateauListener(PoseBateauListener listener){
		listeners.add(PoseBateauListener.class, listener);
	}
	
	public void removePoseBateauListener(PoseBateauListener listener){
		listeners.remove(PoseBateauListener.class, listener);
	}
	
	protected void firePoseBateau(TypeBateau currentBoat){
		for (PoseBateauListener listener : listeners.getListeners(PoseBateauListener.class)) {
			listener.poseBateau(currentBoat);
		}
	}

}
