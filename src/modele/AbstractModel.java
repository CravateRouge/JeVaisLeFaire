package modele;

import java.util.EventObject;

import javax.swing.event.EventListenerList;

import vue.Listener;

public class AbstractModel{

	protected EventListenerList listeners;
	
	public AbstractModel(){
		super();
		listeners= new EventListenerList();
	}

	public void addListener(Listener l){
		listeners.add(Listener.class, l);
	}
	
	public void removeListener(Listener l){
		listeners.remove(Listener.class, l);
	}
	
	public void fireChange(Object obj){
		Listener[] listenersList= listeners.getListeners(Listener.class);
		
		for(Listener listener : listenersList){
			listener.change(new EventObject(this),obj);
		}
	}
}
