package modele;

import listener.OccupeeListener;
import listener.VisiteeListener;

public abstract class AbstractCase extends AbstractModel {

	public AbstractCase() {
		super();
	}
	
	public void addOccupeeListener(OccupeeListener listener){
		listeners.add(OccupeeListener.class, listener);
	}
	
	public void removeOccupeeListener(OccupeeListener listener){
		listeners.remove(OccupeeListener.class, listener);
	}
	
	protected void fireCaseOccupee(){
		for (OccupeeListener listener : listeners.getListeners(OccupeeListener.class)) {
			listener.caseOccupee();
		}
	}

	public void addVisiteeListener(VisiteeListener listener){
		listeners.add(VisiteeListener.class, listener);
	}
	
	public void removeVisiteeListener(VisiteeListener listener){
		listeners.remove(VisiteeListener.class, listener);
	}
	
	protected void fireCaseVisitee(boolean touche){
		for (VisiteeListener listener : listeners.getListeners(VisiteeListener.class)) {
			listener.caseVisitee(touche);
		}
	}
}
