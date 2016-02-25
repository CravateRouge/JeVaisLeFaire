package listener;

import java.util.EventListener;

public interface OccupeeListener extends EventListener {

	public void occupeeChanged(OccupeeChangedEvent event);
}
