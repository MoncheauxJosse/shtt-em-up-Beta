package cdi.projet.ihm.data.meteorite;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.Random;

import javax.swing.JPanel;

import cdi.projet.ihm.data.avion.Avion;

public abstract class MeteoriteAbstract implements Meteorite {
	protected static int CMPT = 0;
	protected static final int WHITE_STEP = 1; // vitesse de déplacement des meteorite?
	
	protected int posY;
	protected int posX = Integer.MIN_VALUE;
	protected boolean detruit;
	protected boolean remplacable;
	protected Meteorite precedent;
	protected int id;
	protected Avion plane;
	protected int width;
	protected int height;
	protected int nbEmptyStep; 
	protected Image img;
	protected JPanel pan;
	protected int degat; // point de vie retiré par un impact meteorite
	protected int indexImage = 0; // choix de l'image a afficher (alternance des image pour le visuel)
	protected String type;
	int i=0;
	
	public MeteoriteAbstract(JPanel p, Meteorite m, Avion pl) {
		this.id = MeteoriteAbstract.CMPT++;
		this.img = getImageImpaire();
		this.pan = p;
		this.width = getWidth();
		this.height = getHeight();
		this.degat = getDegat();
		this.precedent = m;
		this.plane = pl;
		this.type = toString();
		
	}
	
	public void dessiner (Graphics g, boolean pause) {
		if(pause) { // impact meteorite et changement image + repaint
			Graphics2D g2d = (Graphics2D) g.create();
			g2d.drawImage(img, this.posX, this.posY, this.width, this.height, pan);
			return;
		}
		if ((this.posX == Integer.MIN_VALUE && this.id == 0) //repositionnement random en X d'une meteorite.
				|| (this.posX == Integer.MIN_VALUE || this.posY >= this.pan.getHeight())
				&& (this.precedent.getPosY() > this.pan.getHeight() / (MeteoriteFactory.listMeteorites.size()*1.5))) {
			if (this.type.equals("MeteoriteZigzag")) {
			i=0;
			}
			if(this.detruit) { // si elle a eté detruite par l'avion
				this.remplacable = true; // on recree une nouvelle meteorite
			}else {
				this.posX = new Random().nextInt(this.pan.getWidth() - this.width); // postion random en X
				this.posY = 0; // position en Y
			}
		}
		if (this.posX != Integer.MIN_VALUE) { // si meteorite deja crée
			if (nbEmptyStep < WHITE_STEP) { // si emptystep =0
				this.nbEmptyStep++; // emptystep =1
			} else {
				if(this.detruit) {
					this.posY += getMoveStepAfterDestruction(); // si detruite la vitesse ralentie?
				} else if (this.type.equals("MeteoriteZigzag")) { // trouver le get "element en cours"
					if (i<this.pan.getWidth()/10 || (i>=(this.pan.getWidth()*2)/10 && i<(this.pan.getWidth()*3)/10) || i>=(this.pan.getWidth()*4)/10) {
						this.posY += getMoveStep();
						this.posX += getMoveStep();
						i++;
					}
					if ((i>=this.pan.getWidth()/10 && i<(this.pan.getWidth()*2)/10) || (i>=(this.pan.getWidth()*3)/10 && i<(this.pan.getWidth()*4)/10)) {
						this.posY += getMoveStep();
						this.posX -= getMoveStep();
						i++;
					}
				} else {
					this.posY += getMoveStep(); // sinon vitesse de base de la meteorite
				}

				this.nbEmptyStep = 0; // emptystep remis a 0
			}
		}

		if (this.posY>=this.pan.getHeight() && !this.detruit) { 
			GetscoreValue();
		}
		if(! this.detruit) {
			this.indexImage %= 2;
			if (indexImage == 0) {
				this.img = getImagePaire(); // alternance des 2 image de chaque meteorite ==> effet de clignotement
			} else {
				this.img = getImageImpaire(); // 
			}
			this.indexImage++;
		}

		Graphics2D g2d = (Graphics2D) g.create();
		g2d.drawImage(img, this.posX, this.posY, this.width, this.height, pan);

		this.verifierImpacte();
	}
	
	public void verifierImpacte() {
		if (!this.detruit && (((this.posX + this.width > this.plane.getPosX()
				&& this.posX + this.width < this.plane.getPosX() + this.plane.getWidth())
				|| (this.posX > this.plane.getPosX() && this.posX < this.plane.getPosX() + this.plane.getWidth()))
				&& (this.posY + this.height > this.plane.getPosY()
						&& this.posY + this.height < this.plane.getPosY() + this.plane.getHeight()))
				) {
			this.plane.impacte(this.degat);
			this.img = getImageImpacte();
			this.degat=0;
			this.detruit = true;
		}
	}
	
	@Override
	public int getPosY() {
		return this.posY;
	}
	
	@Override
	public boolean isDetruit() {
		return detruit;
	}

	@Override
	public boolean isRemplacable() {
		return remplacable;
	}

	public static void init() {
		CMPT = 0;
	}
	
}
