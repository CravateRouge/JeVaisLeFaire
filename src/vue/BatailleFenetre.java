package vue;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;

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
		setSize(700, 600);
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
		private JPanel buildGridPane(){
		JPanel gridPanel = new JPanel(new GridLayout(11,11));
		Border blackline = BorderFactory.createLineBorder(Color.black,1);
		Border blueline = BorderFactory.createLineBorder(Color.blue, 1);
		for(int o = 0; o<11;o++){
			for(int a = 0; a<11; a++){
				if((a == o) && a == 0){
					JButton ptest0 = new JButton();
					ptest0.setBackground(new Color(150,150,150));
					ptest0.setBorder(blackline);
					gridPanel.add(ptest0);
				}
				else if(a == 0){
					JButton ptest = new JButton(o+"");
					ptest.setBackground(new Color(100,100,100));
					ptest.setBorder(blackline);
					gridPanel.add(ptest);
				}
				else if(o == 0){
					JButton ptest = new JButton((char)(64+a)+"");
					ptest.setBackground(new Color(100,100,100));
					ptest.setBorder(blackline);
					gridPanel.add(ptest);
				}

				else{
					JButton ptest2 = new JButton();
					ptest2.setBackground(new Color(200));
					ptest2.setBorder(blueline);
					gridPanel.add(ptest2);

				}
			}
		}
		gridPanel.setBorder(blackline);
		
		return gridPanel;
	}
}
