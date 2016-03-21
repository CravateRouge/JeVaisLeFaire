package vue;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.Border;

import controleur.BatailleControleur;
import enumeration.TypeBateau;
import enumeration.TypeBattle;
import enumeration.TypeMode;
import listener.MenuListener;

public class BatailleFenetre extends JFrame implements ActionListener, MenuListener{
	private JPanel mainPanel;
	private JTextField field=null;
	private BatailleControleur c;
	private JLabel j1Nom, j2Nom;
	private Console console;
	private JButton valider;

	public BatailleFenetre(BatailleControleur c, int taille) {
		super();
		this.c=c;

		build(taille);
	}

	private void build(int taille) {
		setTitle("Bataille Navale");
		setSize(1200, 700);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(buildMainPane(taille));
	}

	private JPanel buildMainPane(int taille) {
		mainPanel=new JPanel(new CardLayout());
		mainPanel.add(buildModePane(), "ModePanel");
		mainPanel.add(buildBattlePane(), "BattlePanel");
		mainPanel.add(buildFlottePane(), "FlottePanel");
		mainPanel.add(buildJeuPane(taille), "JeuPanel");

		return mainPanel;
	}

	private JPanel buildBattlePane() {
		JPanel battlePanel = new JPanel(new FlowLayout());

		JLabel label = new JLabel("Choisissez un type de bataille:");
		battlePanel.add(label);

		for (TypeBattle typeBattle : TypeBattle.values())
			battlePanel.add(new JButton(new BattleAction(c,typeBattle)));

		return battlePanel;
	}



	private JPanel buildModePane() {
		JPanel menuPanel=new JPanel(new FlowLayout());

		JLabel label = new JLabel("Choisissez un mode:");
		menuPanel.add(label);

		for (TypeMode typeMode : TypeMode.values())
			menuPanel.add(new JButton(new ModeAction(c, typeMode)));

		return menuPanel;
	}

	private JPanel buildFlottePane(){
		JPanel menuPanel=new JPanel(new FlowLayout());

		menuPanel.add(new JLabel("Saisissez votre nom:"));

		menuPanel.add(field=new JTextField(10));

		menuPanel.add(new JLabel("Choisissez votre flotte:"));

		for (TypeBateau boat : TypeBateau.values())
			menuPanel.add(new CheckBoatBox(c, boat));


		valider = new JButton("validez");
		menuPanel.add(valider);
		valider.addActionListener(this);

		return menuPanel;
	}

	public void display(){
		setVisible(true);
	}

	@Override
	public void modeChoisi(TypeMode mode) {
		((CardLayout) mainPanel.getLayout()).next(mainPanel);
	}

	@Override
	public void battleChoisie() {
		((CardLayout) mainPanel.getLayout()).next(mainPanel);
	}

	@Override
	public void j1NameChoisi() {
		field.setText(null);	
	}

	@Override
	public void initGame(String j1Nom, String j2Nom, TypeBateau boat) {
		this.j1Nom.setText(j1Nom);
		this.j2Nom.setText(j2Nom);

		console.append(this.j1Nom.getText()+" place tes bateaux:\n");
		
		console.nextBoat(boat);
		((CardLayout) mainPanel.getLayout()).last(mainPanel);
	}

	public JPanel buildJeuPane(int taille) {
		JPanel jeuPanel=new JPanel(new BorderLayout());
		jeuPanel.add(buildNorth(), BorderLayout.NORTH);
		jeuPanel.add(buildGridPane(taille),BorderLayout.WEST);
		jeuPanel.add(buildGridPane(taille), BorderLayout.CENTER);
		jeuPanel.add(buildSouth(), BorderLayout.SOUTH);

		return jeuPanel;
	}

	private JPanel buildNorth(){
		JPanel north = new JPanel(new GridLayout(1,2));
		north.add(j1Nom=new JLabel());
		north.add(j2Nom=new JLabel());
		return north;
	}
	private JPanel buildSouth(){
		JPanel south = new JPanel();
		Dimension dscroll = new Dimension(800,85);
		console = new Console(2,5);
		console.setEditable(false);
		JScrollPane scroll = new JScrollPane(console);
		scroll.setPreferredSize(dscroll);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		south.add(scroll);

		return south;
	}

	private JPanel buildGridPane(int taille){
		taille++;
		JPanel gridPanel = new JPanel(new GridLayout(taille,taille));
		JButton button;
		Dimension d = new Dimension(50,50);
		Border blackline = BorderFactory.createLineBorder(Color.BLACK,1);
		Border blueline = BorderFactory.createLineBorder(Color.BLUE, 1);
		for(int y = 0; y<taille;y++){
			for(int x = 0; x<taille; x++){				
				if(x!=0 && y!=0){
					button=new CaseButton(c,x,y);
					button.setBackground(new Color(200));
					button.setBorder(blueline);
				}
				else{
					button=new JButton();
					button.setBorder(blackline);

					if(x==0 && y == 0){
						button.setBackground(Color.GRAY);
						button.addActionListener(this);
					}

					else{
						button.setBackground(new Color(100,100,100));

						if(x == 0)
							button.setText(y+"");
						else
							button.setText((char)(64+x)+"");
					}
				}

				button.setPreferredSize(d);
				gridPanel.add(button);
			}
		}
		gridPanel.setBorder(blackline);

		return gridPanel;
	}

	@Override
	public void actionPerformed(ActionEvent a) {
		if(a.getSource() == valider)
			c.notifyNameChanged(field.getText());
		else
			c.notifyDirChanged();
	}

	public Console getConsole() {
		return console;
	}
}
