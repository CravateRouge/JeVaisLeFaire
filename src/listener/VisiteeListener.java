package listener;

import java.util.EventListener;

public interface VisiteeListener extends EventListener {
	
	public void visiteeChanged(VisiteeChangedEvent event);
}
