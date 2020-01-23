package cdi.projet.ihm.data.meteorite;

import java.awt.Graphics;
import java.awt.Image;

public interface Meteorite {
	public void dessiner (Graphics g, boolean pause);

	public String toString();
	
	public int getPosY();
	
	public Meteorite getPrecedent() ;

	public void setPrecedent(Meteorite precedent) ;

	boolean isDetruit();

	boolean isRemplacable();
	
	Image getImagePaire();
	
	Image getImageImpaire();
	
	Image getImageImpacte();
	
	int getMoveStep();
	
	int getMoveStepAfterDestruction();
	
	int getDegat();
	
	int getWidth();
	
	int getHeight();
	
	int GetscoreValue();
}
