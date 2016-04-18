package listener;

import java.util.EventListener;

public interface CaseListener extends EventListener {

	public void caseOccupee();

	public void caseVisitee(boolean touche);

	public void caseCachee();

	public void caseArti();
}
