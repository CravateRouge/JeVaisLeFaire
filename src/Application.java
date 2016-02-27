import controleur.BatailleController;
import modele.Menu;

public class Application {

	public static void main(String[] args) {
		Menu model=new Menu();
		BatailleController controller=new BatailleController(model);

	}

}
