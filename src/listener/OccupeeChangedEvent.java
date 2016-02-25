package listener;

import java.util.EventObject;

@SuppressWarnings("serial")
public class OccupeeChangedEvent extends EventObject {
private boolean newOccupee;

	public OccupeeChangedEvent(Object source, boolean newOccupee) {
		super(source);
		
		this.newOccupee=newOccupee;
	}

	public boolean isNewOccupee() {
		return newOccupee;
	}
	
	

}
