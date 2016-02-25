package modele;

import listener.OccupeeChangedEvent;
import listener.OccupeeListener;
import listener.VisiteeChangedEvent;
import listener.VisiteeListener;

public class Case extends AbstractModel {

	private int x;
	private int y;
	private boolean visitee = false;
	private boolean occupee = false;
	
	/**Constructeur avec les coordonnees */
	public Case(int x, int y){
		super();
		this.x = x;
		this.y = y;
	}
	
	/*@Débattre Constructeur inutile*/
	/** Constructeur par copie */
	public Case(Case c){
		this.x = c.getX();
		this.y = c.getY();
	}
	
	public int getX(){
		return this.x;
	}
	
	public int getY(){
		return this.y;
	}
	/** Permet de savoir si la case a ete testee */
	public boolean isVisitee(){
		return this.visitee;
	}
	
	public boolean isOccupee(){
		return this.occupee;
	}
	
	public void setOccupee() {
		this.occupee = !occupee;
	}
	public void setVisitee(){
		this.visitee = !visitee;
	}
	
	public void addVisiteeListener(VisiteeListener l){
		listeners.add(VisiteeListener.class, l);
	}
	
	public void removeVisiteeListener(VisiteeListener l){
		listeners.remove(VisiteeListener.class, l);
	}
	
	public void fireVisiteeChanged(Object obj){
		VisiteeListener[] listenersList= listeners.getListeners(VisiteeListener.class);
		
		for(VisiteeListener visiteeListener : listenersList){
			visiteeListener.visiteeChanged(new VisiteeChangedEvent(getClass(), isVisitee()));
		}
	}
	
	public void addOccupeeListener(OccupeeListener l){
		listeners.add(OccupeeListener.class, l);
	}
	
	public void removeOccupeeListener(OccupeeListener l){
		listeners.remove(OccupeeListener.class, l);
	}
	
	public void fireOccupeeChanged(Object obj){
		OccupeeListener[] listenersList= listeners.getListeners(OccupeeListener.class);
		
		for(OccupeeListener occupeeListener : listenersList){
			occupeeListener.occupeeChanged(new OccupeeChangedEvent(getClass(), isOccupee()));
		}
	}
}
