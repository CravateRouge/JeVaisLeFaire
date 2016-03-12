package vue;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import controleur.BatailleController;
import enumeration.TypeBateau;
import enumeration.TypeBattle;
/*
 * Pour console de liste utiliser JScrollPane/JTextArea
 *
 * Pour JeuPanel utiliser BorderLayout
 */
public class JeuPanel extends JPanel {

	private String j1Nom, j2Nom;
	private TypeBattle battle;
	private List<TypeBateau> j1Flotte, j2Flotte;
	private BatailleController controller;

	public JeuPanel(BatailleController controller, LayoutManager layout, TypeBattle battle, String j1Name, String j2Name, List<TypeBateau> j1Flotte, List<TypeBateau> j2Flotte) {
		super(layout);
		layout.preferredLayoutSize(this);
		this.controller=controller;
		this.battle=battle;
		this.j1Nom=j1Name;
		this.j2Nom=j2Name;
		this.j1Flotte=j1Flotte;
		this.j2Flotte=j2Flotte;
				
		buildJeu();
	}

	private void buildJeu() {
		add(new JLabel(j1Nom));
		add(buildGridPane());
		add(buildGridPane());
		add(new JLabel(j2Nom));
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
