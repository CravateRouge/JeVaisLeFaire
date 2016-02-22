package vue;

import java.util.EventListener;
import java.util.EventObject;

public interface Listener extends EventListener {
	
	public void change(EventObject eObj, Object obj);
}
