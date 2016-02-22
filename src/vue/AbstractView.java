package vue;

import controleur.AbstractController;

public abstract class AbstractView implements Listener {
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
