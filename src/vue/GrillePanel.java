package vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;

import controleur.BatailleController;

public class GrillePanel extends JPanel {

	public GrillePanel(BatailleController controller) {
		super();
		add(buildGridPane());
	
	}
	
	private JPanel buildGridPane(){
	JPanel gridPanel = new JPanel(new GridLayout(11,11));
	Dimension d = new Dimension(50,50);
	Border blackline = BorderFactory.createLineBorder(Color.black,1);
	Border blueline = BorderFactory.createLineBorder(Color.blue, 1);
	for(int o = 0; o<11;o++){
		for(int a = 0; a<11; a++){
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
				JButton ptest2 = new JButton();
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

}
