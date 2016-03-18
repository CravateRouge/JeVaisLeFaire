package vue;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.Border;

import controleur.BatailleController;
import enumeration.TypeBateau;
import enumeration.TypeBattle;
import listener.GrilleListener;
import listener.PoseBateauListener;

public class JeuPanel extends JPanel implements ActionListener, PoseBateauListener{

	private TypeBattle battle;
	private BatailleController controller;
	private static LayoutManager layout =new BorderLayout();
	private boolean horizontal=true, positionnement=true;
	private TypeBateau currentBoat=null;
	private JLabel j1Nom,j2Nom;
	private JTextArea console;


	public boolean isHorizontal() {
		return horizontal;
	}

	public boolean isPositionnement() {
		return positionnement;
	}

	public JeuPanel(BatailleController controller) {
		super(layout);
		this.controller=controller;
	}

	public void buildJeu(String j1Nom, String j2Nom, int taille, TypeBateau currentBoat) {
		//add(new JLabel(j1Nom));
		this.currentBoat=currentBoat;
		add(buildNorth(j1Nom,j2Nom), BorderLayout.NORTH);
		add(buildGridPane(taille),BorderLayout.WEST);
		add(buildGridPane(taille), BorderLayout.CENTER);
		add(buildSouth(), BorderLayout.SOUTH);
	}

	private JPanel buildNorth(String j1Nom, String j2Nom){
		JPanel north = new JPanel(new GridLayout(1,2));
		north.add(this.j1Nom=new JLabel(j1Nom));
		north.add(this.j2Nom=new JLabel(j2Nom));
		return north;
	}
	private JPanel buildSouth(){
		JPanel south = new JPanel();
		Dimension dscroll = new Dimension(800,85);
		console = new JTextArea(2,5);
		console.setEditable(false);
		JScrollPane scroll = new JScrollPane(console);
		scroll.setPreferredSize(dscroll);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		south.add(scroll);
		
		if(currentBoat!=null)
			console.append("Pour poser un bateau cliquez sur la case en haut à gauche\n"
					+ "afin de selectionner sa direction\n"
					+ "puis appuyez sur la case désirée.\n\n"+j1Nom.getText()+" peut poser ses bateaux:\n\t-"
					+currentBoat+" -> taille:"+currentBoat.getTaille()+"\n");
		
		return south;
	}

	private JPanel buildGridPane(int taille){
		taille++;
		JPanel gridPanel = new JPanel(new GridLayout(taille,taille));
		Dimension d = new Dimension(50,50);
		Border blackline = BorderFactory.createLineBorder(Color.black,1);
		Border blueline = BorderFactory.createLineBorder(Color.blue, 1);
		for(int o = 0; o<taille;o++){
			for(int a = 0; a<taille; a++){
				if((a == o) && a == 0){
					JButton ptest0 = new JButton();
					ptest0.setPreferredSize(d);
					ptest0.setBackground(new Color(150,150,150));
					ptest0.setBorder(blackline);
					gridPanel.add(ptest0);
				}
				else if(a == 0){
					JButton ptest = new JButton(o+"");
					ptest.setPreferredSize(d);
					ptest.setBackground(new Color(100,100,100));
					ptest.setBorder(blackline);
					
					ptest.addActionListener(this);
					gridPanel.add(ptest);
				}
				else if(o == 0){
					JButton ptest = new JButton((char)(64+a)+"");
					ptest.setPreferredSize(d);
					ptest.setBackground(new Color(100,100,100));
					ptest.setBorder(blackline);
					gridPanel.add(ptest);
				}

				else{
					JButton ptest2 = new JButton(new CaseAction(controller,this, a,o));
					ptest2.setPreferredSize(d);
					ptest2.setBackground(new Color(200));
					ptest2.setBorder(blueline);
					gridPanel.add(ptest2);

				}
			}
		}
		gridPanel.setBorder(blackline);

		return gridPanel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		horizontal=!horizontal;
	}

	public TypeBateau getCurrentBoat() {
		System.out.println(currentBoat);
		return currentBoat;
	}

	@Override
	public void poseBateau(TypeBateau currentBoat) {
		if(this.currentBoat==currentBoat)
			console.append("\nErreur de positionnement, essaye encore!\n");
		else if(currentBoat!=null){
			this.currentBoat=currentBoat;
			console.append("\t"+currentBoat+" -> taille:"+currentBoat.getTaille()+"\n");
		}
		else{
			positionnement=false;
		}
			
		
	}

}
