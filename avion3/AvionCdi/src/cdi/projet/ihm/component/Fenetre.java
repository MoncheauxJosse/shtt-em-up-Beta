package cdi.projet.ihm.component;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;
import java.text.SimpleDateFormat;
import javax.swing.ImageIcon;
import javax.swing.Icon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import cdi.projet.ihm.tools.Util;

public class Fenetre extends JFrame{

	private static final long serialVersionUID = 1L;//serialiser les donné (les mettre en tableaux)
	private GamePanel gamePanel;
	private GameInformationPanel gameInformationPanel;
	private JPanel container = new JPanel();
	protected static String nom="" ;//pour mettre le nom
	private boolean pointV=false;
	private Date date;
	private static final  SimpleDateFormat SDF = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	
	public Fenetre(){
		this.setTitle("Jeu d'avion");
		this.setSize(500, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);

		container.setBackground(Color.white);
		container.setLayout(new BorderLayout());
		
		gamePanel = new GamePanel();
		container.add(gamePanel, BorderLayout.CENTER);
		
		gameInformationPanel = new GameInformationPanel(gamePanel);
		container.add(gameInformationPanel, BorderLayout.NORTH);//bloc top
		
		this.setContentPane(container);
		this.setVisible(true);
		go();
	}

	private void go(){
		
		try {
		while (pointV==false) {
			nom = (String)JOptionPane.showInputDialog( null, "Entrez votre nom : (3 à 6 caractères)", "Nom du joueur?", JOptionPane.QUESTION_MESSAGE);//pour demander entrée
			if (nom.length()<3 || nom.length()>6) {
				pointV=false;
			} else if (nom.length()>2 && nom.length()<7) {
				for (int i=0; i<nom.length(); i++) {
					pointV=true;
					if (nom.charAt(i)==';') {
						pointV=false;
						break;
					}
				}
			}
		}
		}
		catch (NullPointerException e) {
			go();
		}
				
		while(! this.gamePanel.isGameOver()){//si different a game over
			gameInformationPanel.repaint(); //repaint affichage bloc top
			gamePanel.repaint();
			try {
				Thread.sleep(10);//vitesse temps
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} 
		gameInformationPanel.repaint();
		gamePanel.repaint();
		String temp = System.getProperty("java.io.tmpdir");
	    File historique= new File(temp+"/save.txt");
	    try {
			historique.createNewFile();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		this.date = new Date();	
		Save p= new Save(nom,GameInformationPanel.score, SDF.format(date));
		try {
			FileOutputStream sav= new FileOutputStream( historique,true);
			ObjectOutputStream os = new ObjectOutputStream (sav);
			os.writeObject(p);
			os.close();
			}catch(Exception e) {
				
			e.printStackTrace();}
			
		
		 JOptionPane fin= new JOptionPane();//création de la boite
		 //creation de l'icon de la fenetre game over
		 ImageIcon icon = new ImageIcon(new ImageIcon("resources/img/avion/avion_middle_position.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
		 String lesTextes[]={ "Oui", "Non", "Score"}; // texte sur les bouton
		 //creation de la fenetre avec: le texte, le titre, l'option panel,option information message,l'image icon tous juste creer, les bouton et le bouton de defaut ("oui").
		 int retour=fin.showOptionDialog(this, "Fin de partie, voulez-vous recommencez?", "Game Over!",JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,icon, lesTextes, lesTextes[0]); 
		 
		if(retour == 0) {
		  this.gamePanel.init();
		  pointV=false;
		  go();
		} if(retour == 2) {
			try {
				FileInputStream score = new FileInputStream(historique);
				ObjectInputStream ois= new ObjectInputStream(score); 
				Save p1=(Save)ois.readObject();
				System.out.println("Nom: "+p.getNom()+" -/- score: "+p.getsScore()+"-/- Date: "+p.getDate());
				ois.close();
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			} else {
		  System.exit(0);
		} 
	}

}
