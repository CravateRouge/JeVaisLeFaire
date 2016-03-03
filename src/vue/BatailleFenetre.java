package vue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import controleur.BatailleController;
import listener.InitListener;

public class BatailleFenetre extends JFrame implements InitListener{
	private BatailleController controller;
	private JPanel menuPanel;

	public BatailleFenetre(BatailleController controller) {
		super();

		this.controller=controller;
		build();
	}

	private void build() {
		setTitle("Bataille Navale");
		setSize(400, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(menuPanel=new MenuPanel(controller));

	}

	public void display(){
		setVisible(true);
	}

	public JPanel getMenuPanel() {
		return menuPanel;
	}

	@Override
	public void initGame() {
		System.out.println("Super");
		
	}
}
