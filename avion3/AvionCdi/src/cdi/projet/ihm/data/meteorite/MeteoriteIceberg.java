package cdi.projet.ihm.data.meteorite;

import java.awt.Image;

import javax.swing.JPanel;

import cdi.projet.ihm.component.GameInformationPanel;
import cdi.projet.ihm.data.avion.Avion;
import cdi.projet.ihm.tools.Util;

public class MeteoriteIceberg extends MeteoriteAbstract{
	private static final int MOVE_STEP = 4;
	private static final int MOVE_STEP_DESTRUCTION = 1;
	private static final int DEGAT_VAL = 4;
	private static final int WIDTH = 60;
	private static final int HEIGHT = 40;
	private static final int SCORE_VAL = 5;

	private static Image imgImpaire = Util.getImg("resources/img/meteorite-glace/meteorite_impaire.png");
	private static Image imgPaire = Util.getImg("resources/img/meteorite-glace/meteorite_paire.png");
	private static Image imgImpacte = Util.getImg("resources/img/meteorite-glace/meteorite_impacte.png");

	public MeteoriteIceberg(JPanel p, Meteorite m, Avion pl) {
		super(p, m, pl);
	}
	
	@Override
	public String toString() {
		return "MeteoriteIceberg";
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
