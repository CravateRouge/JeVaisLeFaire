package vue;

import controleur.AbstractController;
import listener.VisiteeListener;

public abstract class AbstractView implements VisiteeListener {
	private AbstractController controller=null;

	public AbstractView(AbstractController controller){
		super();
		this.controller=controller;
	}

	public final AbstractController getController(){
		return controller;
	}

	public abstract void display();

	public abstract void close();
}
