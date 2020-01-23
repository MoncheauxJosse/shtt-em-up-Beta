package cdi.projet.ihm.component;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import cdi.projet.ihm.tools.Util;

public class GameInformationPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private Image imgMiddle = Util.getImg("resources/img/avion/avion_middle_position.png");
	private Image bitCoin = Util.getImg("resources/img/Bitcoin.png");
	private JLabel nbViesLabel;
	private JLabel scoreLabel;
	private JLabel nomLabel;
	private GamePanel gamePanel;
	public static int score=0;
	
	public GameInformationPanel(GamePanel pan) {
		this.gamePanel = pan;
		this.nbViesLabel = new JLabel(Integer.toString(this.gamePanel.getPlane().getNbVies()));
		this.scoreLabel = new JLabel ();
		this.nomLabel = new JLabel(Fenetre.nom);
		this.setBackground(Color.CYAN);
		this.setPreferredSize(new Dimension(500, 26));
		BorderLayout borderLayout = new BorderLayout();
		this.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 7));
		this.setLayout(borderLayout);
		this.add(this.nbViesLabel,BorderLayout.EAST);
		this.add(this.scoreLabel,BorderLayout.WEST);
		nomLabel.setHorizontalAlignment(JLabel.CENTER);
		nomLabel.setVerticalAlignment(JLabel.CENTER);
		this.add(this.nomLabel,BorderLayout.CENTER);
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		this.nomLabel.setText(Fenetre.nom);
		this.nomLabel.repaint();
		this.nbViesLabel.setText(Integer.toString(this.gamePanel.getPlane().getNbVies()));
		this.nbViesLabel.repaint();
		if (score<10) {
			this.scoreLabel.setText("         "+"00"+score);
		} else if (score>=10 && score<100) {
			this.scoreLabel.setText("         "+"0"+score);
		} else {
			this.scoreLabel.setText("         "+score);
		}
		this.scoreLabel.repaint();
		
		Graphics2D g2d = (Graphics2D) g.create();
		g2d.drawImage(imgMiddle, 452, 3, 20, 20, this);
		g2d.drawImage(bitCoin, 5, 3, 20, 20, this);
	}
	
}
