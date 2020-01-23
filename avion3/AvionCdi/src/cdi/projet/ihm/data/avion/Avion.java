package cdi.projet.ihm.data.avion;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import cdi.projet.ihm.component.GameInformationPanel;
import cdi.projet.ihm.component.GamePanel;
import cdi.projet.ihm.tools.Util;

public class Avion {

	private static final int MOVE_STEP = 8;

	private static Image imgMiddle = Util.getImg("resources/img/avion/avion_middle_position.png");
	private static Image imgMoveRight = Util.getImg("resources/img/avion/avion_move_right.png");
	private static Image imgMoveLeft = Util.getImg("resources/img/avion/avion_move_left.png");
	private static Image imgMoveUp = Util.getImg("resources/img/avion/avion_move_up.png");
	private static Image imgDestruction = Util.getImg("resources/img/avion/avion_destruction.png");

	private Image img;
	private int posX = Integer.MIN_VALUE;
	private int posY;
	private GamePanel gamePanel;
	private int width;
	private int height;
	private int nbVies;

	public Avion(GamePanel p) {
		this.img = imgMiddle;
		this.gamePanel = p;
		this.width = 60;
		this.height = 60;
		this.nbVies = 5;
		this.posX = Integer.MIN_VALUE;
	}

	public void paint(Graphics g) {
		if (this.posX == Integer.MIN_VALUE) {
			this.posX = (gamePanel.getWidth() / 2) - (this.width / 2);
			this.posY = gamePanel.getHeight() - this.height;
		}

		Graphics2D g2d = (Graphics2D) g.create();

		g2d.drawImage(img, this.posX, this.posY, this.width, this.height, gamePanel);
	}

	public void moveToTheRight() {
		if (this.nbVies <= 0) {
			return;
		}
		if (this.gamePanel.getWidth() > this.posX + this.width) {
			this.posX += MOVE_STEP;
		}
		this.img = imgMoveRight;
	}

	public void moveToTheLeft() {
		if (this.nbVies <= 0) {
			return;
		}
		if (0 < this.posX) {
			this.posX -= MOVE_STEP;
		}
		this.img = imgMoveLeft;
	}

	public void moveToTheUp() {
		if (this.nbVies <= 0) {
			return;
		}
		if (0 < this.posY) {
			this.posY -= MOVE_STEP;
		}
		this.img = imgMoveUp;
	}

	public void moveToTheDown() {
		if (this.nbVies <= 0) {
			return;
		}
		if (this.gamePanel.getHeight() > this.posY + this.height) {
			this.posY += MOVE_STEP;
		}
	}

	public void impacte(int degat) {
		this.nbVies -= degat;
		if (this.nbVies <= 0) {
			this.img = imgDestruction;
			this.gamePanel.gameOver();
		}
	}

	public void plane() {
		if (this.nbVies <= 0) {
			return;
		}
		this.img = imgMiddle;
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	@Override
	public String toString() {
		return "CdiPlane [posX=" + posX + ", posY=" + posY + ", width=" + width + ", height=" + height + ", nbVies="
				+ nbVies + "]";
	}

	public int getNbVies() {
		return nbVies;
	}

	public void setNbVies(int nbVies) {
		this.nbVies = nbVies;
	}

	public void init() {
		this.img = imgMiddle;
		this.nbVies = 5;
		this.posX = Integer.MIN_VALUE;
		GameInformationPanel.score = 0;
	}
}
