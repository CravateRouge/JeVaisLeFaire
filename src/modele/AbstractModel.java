package modele;

import javax.swing.event.EventListenerList;


public class AbstractModel{

	protected EventListenerList listeners;
	
	public AbstractModel(){
		super();
		listeners= new EventListenerList();
	}
}
