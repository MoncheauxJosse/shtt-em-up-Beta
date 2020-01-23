package cdi.projet.ihm.data.meteorite;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.Random;

import javax.swing.JPanel;

import cdi.projet.ihm.component.GameInformationPanel;
import cdi.projet.ihm.data.avion.Avion;
import cdi.projet.ihm.tools.Util;

final class MeteoriteZigzag extends MeteoriteAbstract {

	private static final int MOVE_STEP = 4;
	private static final int MOVE_STEP_DESTRUCTION = 1;
	private static final int DEGAT_VAL = 2;
	private static final int WIDTH = 23;
	private static final int HEIGHT = 30;
	private static final int SCORE_VAL = 5;
	
	
	private static Image imgImpaire = Util.getImg("resources/img/meteorite-zigzag/meteorite_impaire.png");
	private static Image imgPaire = Util.getImg("resources/img/meteorite-zigzag/meteorite_paire.png");
	private static Image imgImpacte = Util.getImg("resources/img/meteorite-zigzag/meteorite_impacte.png");

	private int premierePosition;
	private boolean versDroite;
	private int intervalZigzag;
	
	public MeteoriteZigzag(JPanel p, Meteorite m, Avion pl) {
		super(p, m, pl);
	}
	
	@Override
	public void dessiner(Graphics g, boolean pause) {
		if(pause) {
			Graphics2D g2d = (Graphics2D) g.create();
			g2d.drawImage(img, this.posX, this.posY, this.width, this.height, pan);
			return;
		}
		if ((this.posX == Integer.MIN_VALUE && this.id == 0)
				|| (this.posX == Integer.MIN_VALUE)
				&& (this.precedent.getPosY() > this.pan.getHeight() / 4)) {
			if(this.detruit) {
				this.remplacable = true;
			}else {
				if(new Random().nextBoolean()) {
					this.intervalZigzag = this.pan.getWidth()/10;
				} else {
					this.intervalZigzag = this.pan.getWidth()/5;
				}
				this.posX = new Random().nextInt(this.pan.getWidth() - (this.width+this.intervalZigzag));
				this.premierePosition = this.posX;
				this.versDroite = true;
				this.posY = 0;
			}
		} else if ( this.posY >= this.pan.getHeight() ) {
			this.remplacable = true;
		}
		if (this.posX != Integer.MIN_VALUE) {
			if (nbEmptyStep < WHITE_STEP) {
				this.nbEmptyStep++;
			} else {
				if(this.detruit) {
					this.posY += getMoveStepAfterDestruction();
				} else {
					if(this.versDroite) {
						this.posX +=10;
						if(this.posX >= this.premierePosition+this.intervalZigzag) {
							this.versDroite = false;
						}
					} else {
						this.posX-=10;
						if(this.posX <= this.premierePosition) {
							this.versDroite = true;
						}
					}
					this.posY += getMoveStep();
				}

				this.nbEmptyStep = 0;
			}
		}

		if(! this.detruit) {
			this.indexImage %= 2;
			if (indexImage == 0) {
				this.img = getImagePaire();
			} else {
				this.img = getImageImpaire();
			}
			this.indexImage++;
		}

		Graphics2D g2d = (Graphics2D) g.create();
		g2d.drawImage(img, this.posX, this.posY, this.width, this.height, pan);

		this.verifierImpacte();

	}
	
	@Override
	public Meteorite getPrecedent() {
		return precedent;
	}

	@Override
	public void setPrecedent(Meteorite precedent) {
		this.precedent = precedent;
	}

	@Override
	public Image getImagePaire() {
		return imgPaire;
	}

	@Override
	public Image getImageImpaire() {
		return imgImpaire;
	}

	@Override
	public Image getImageImpacte() {
		return imgImpacte;
	}

	@Override
	public int getMoveStep() {
		return MOVE_STEP;
	}

	@Override
	public int getMoveStepAfterDestruction() {
		return MOVE_STEP_DESTRUCTION;
	}

	@Override
	public int getDegat() {
		return DEGAT_VAL;
	}
	
	@Override
	public int getWidth() {
		return WIDTH;
	}

	@Override
	public int getHeight() {
		return HEIGHT;
	}

	@Override
	public int GetscoreValue() {
		GameInformationPanel.score+=SCORE_VAL;
		return GameInformationPanel.score;
	}

	
}
