package listener;

import java.util.EventObject;

@SuppressWarnings("serial")
public class VisiteeChangedEvent extends EventObject{
private boolean newVisitee;

	public VisiteeChangedEvent(Object source, boolean newVisitee) {
		super(source);

		this.newVisitee=newVisitee;
	}

	public boolean isNewVisitee() {
		return newVisitee;
	}
	
	

}
