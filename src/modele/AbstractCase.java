package modele;

import listener.CaseListener;

public abstract class AbstractCase extends AbstractModel {

	public AbstractCase() {
		super();
	}
	
	public void addCaseListener(CaseListener listener){
		listeners.add(CaseListener.class, listener);
	}
	
	public void removeCaseListener(CaseListener listener){
		listeners.remove(CaseListener.class, listener);
	}
	
	protected void fireCaseOccupee(){
		for (CaseListener listener : listeners.getListeners(CaseListener.class)) {
			listener.caseOccupee();
		}
	}
	
	protected void fireCaseVisitee(boolean touche){
		for (CaseListener listener : listeners.getListeners(CaseListener.class)) {
			listener.caseVisitee(touche);
		}
	}
}
