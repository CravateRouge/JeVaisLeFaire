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
	
	protected void fireCaseOccupee(int x, int y){
		for (OccupeeListener listener : listeners.getListeners(OccupeeListener.class)) {
			listener.caseOccupee(x,y);
		}
	}

	public void addVisiteeListener(VisiteeListener listener){
		listeners.add(VisiteeListener.class, listener);
	}
	
	public void removeVisiteeListener(VisiteeListener listener){
		listeners.remove(VisiteeListener.class, listener);
	}
	
	protected void fireCaseVisitee(int x, int y){
		for (VisiteeListener listener : listeners.getListeners(VisiteeListener.class)) {
			listener.caseVisitee(x,y);
		}
	}
}
