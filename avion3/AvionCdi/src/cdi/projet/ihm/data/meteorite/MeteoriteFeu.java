package cdi.projet.ihm.data.meteorite;

import java.awt.Image;

import javax.swing.JPanel;

import cdi.projet.ihm.component.GameInformationPanel;
import cdi.projet.ihm.data.avion.Avion;
import cdi.projet.ihm.tools.Util;

final class MeteoriteFeu extends MeteoriteAbstract {

	private static final int MOVE_STEP = 2;
	private static final int MOVE_STEP_DESTRUCTION = 1;
	private static final int DEGAT_VAL = 2;
	private static final int WIDTH = 25;
	private static final int HEIGHT = 35;
	private static final int SCORE_VAL = 1;

	private static Image imgImpaire = Util.getImg("resources/img/meteorite-feu/meteorite_impaire.png");
	private static Image imgPaire = Util.getImg("resources/img/meteorite-feu/meteorite_paire.png");
	private static Image imgImpacte = Util.getImg("resources/img/meteorite-feu/meteorite_impacte.png");

	public MeteoriteFeu(JPanel p, Meteorite m, Avion pl) {
		super(p, m, pl);
	}
	
	@Override
	public String toString() {
		return "MeteoriteFeu";
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

	public int GetscoreValue() {
		GameInformationPanel.score+=SCORE_VAL;
		return GameInformationPanel.score;
	}
}
