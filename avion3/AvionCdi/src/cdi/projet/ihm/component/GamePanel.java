package cdi.projet.ihm.component;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import cdi.projet.ihm.data.avion.Avion;
import cdi.projet.ihm.data.meteorite.MeteoriteFactory;
import cdi.projet.ihm.listener.ClavierListener;
import cdi.projet.ihm.tools.Util;

public class GamePanel extends JPanel {
	private static final long serialVersionUID = 1L;//???????????????????????????????????????????????????

	private Image bg =  Util.getImg("resources/img/bg_galaxy.png");//image fond;

	private Avion plane;
	private boolean gameOver; //boolean game over ou non

	public GamePanel() {
		super();
		this.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));//bordue fenetre
		this.setFocusable(true);
		this.requestFocusInWindow();//faire que le clavier repond a la fenetre

		this.plane = new Avion(this);//creation l'objet avion
		ClavierListener cl = new ClavierListener(this.plane);//ecoute le clavier
		this.addKeyListener(cl);//apelle le keylistener ( clavier)

		MeteoriteFactory.init(this, plane);
	}

	public void paintComponent(Graphics g){
		g.setColor(Color.white);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());//creation rectangle

		g.drawImage(bg, 0, 0, this.getWidth(), this.getHeight(), this);// image du fond

		this.plane.paint(g);//paint son avion dans le rectangle
		MeteoriteFactory.dessiner(g,gameOver);
		
	}

	public Avion getPlane() {
		return plane;
	}

	public void setPlane(Avion plane) {
		this.plane = plane;
	}

	public void gameOver() {
		this.gameOver = true;
	}

	public boolean isGameOver() {
		return gameOver;
	}

	public void init() {
		this.plane.init();
		MeteoriteFactory.init(this, plane);
		this.gameOver = false;
	}
}