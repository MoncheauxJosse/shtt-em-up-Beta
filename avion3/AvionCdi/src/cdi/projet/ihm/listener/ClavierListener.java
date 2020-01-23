package cdi.projet.ihm.listener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import cdi.projet.ihm.data.avion.Avion;

public class ClavierListener implements KeyListener {

	private Avion avion;
	
	public ClavierListener(Avion a) {
		this.avion = a;
	}

	public void keyPressed(KeyEvent event) {
		if (event.getKeyCode() == KeyEvent.VK_RIGHT) {
			avion.moveToTheRight();
		} else if (event.getKeyCode() == KeyEvent.VK_LEFT) {
			avion.moveToTheLeft();
		} else if (event.getKeyCode() == KeyEvent.VK_UP) {
			avion.moveToTheUp();
		} else if (event.getKeyCode() == KeyEvent.VK_DOWN) {
			avion.moveToTheDown();
		}
	}

	public void keyReleased(KeyEvent event) {
		avion.plane();
	}

	public void keyTyped(KeyEvent event) {}
}
