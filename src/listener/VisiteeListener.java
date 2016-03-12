package listener;

import java.util.EventListener;

public interface VisiteeListener extends EventListener {

	public void caseVisitee(int x, int y);
}
