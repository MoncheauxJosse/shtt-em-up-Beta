package cdi.projet.ihm.data.meteorite;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
//import java.util.Random;

import cdi.projet.ihm.component.GamePanel;
import cdi.projet.ihm.data.avion.Avion;

public class MeteoriteFactory {

	public static List<Meteorite> listMeteorites = new ArrayList<>();

	private static GamePanel gamePanel;

	private static Avion avion;

	public static void init(GamePanel gp, Avion av) {
		MeteoriteAbstract.init();
		
		listMeteorites.clear();
		
		gamePanel = gp;
		avion = av;

		Meteorite meteorite1 = produireUnMeteorite();
		Meteorite meteorite2 = produireUnMeteorite();
		Meteorite meteorite3 = produireUnMeteorite();
		Meteorite meteorite4 = produireUnMeteorite();

		meteorite1.setPrecedent(meteorite4);
		meteorite2.setPrecedent(meteorite1);
		meteorite3.setPrecedent(meteorite2);
		meteorite4.setPrecedent(meteorite3);

		listMeteorites.add(meteorite1);
		listMeteorites.add(meteorite2);
		listMeteorites.add(meteorite3);
		listMeteorites.add(meteorite4);
}

	public static Meteorite produireUnMeteorite() {
		Meteorite result = null;
		double choixMeteorite=Math.random()*100; // possibilité d'utiliser les case1/case2/case3....
		if(choixMeteorite<=20) {
			result = new MeteoriteSimple(gamePanel,null,avion);
		} else if (choixMeteorite>20 && choixMeteorite<=40){
			result = new MeteoriteFeu(gamePanel,null,avion);
		} else if (choixMeteorite>40 && choixMeteorite<=60){
			result = new MeteoriteZigzag(gamePanel,null,avion);
		} else if (choixMeteorite>60 && choixMeteorite<=80) {
			result = new MeteoriteIceberg(gamePanel,null,avion);
		} else {
			result = new MeteoriteGlace(gamePanel,null,avion);
		} 
//		
//		Random r = new Random(System.currentTimeMillis());
//		if(r.nextBoolean()) {
//			result = new MeteoriteSimple(gamePanel,null,avion);
//		} else {
//			result = new MeteoriteFeu(gamePanel,null,avion);
//		}
		return result;
	}

	public static List<Meteorite> getMeteorites(){
		return listMeteorites;
	}

	public static void dessiner(Graphics g, boolean gameOver) {
		List<Meteorite> listToAdd = new ArrayList<>();
		
		List<Meteorite> listToRemove = new ArrayList<>();
		
		listMeteorites.forEach(m->{
			if(m.isRemplacable()) {
				Meteorite mTmp = produireUnMeteorite();
				mTmp.setPrecedent(m.getPrecedent());
				listToAdd.add(mTmp);
				
				listToRemove.add(m);
			}
		});
		
		listMeteorites.removeAll(listToRemove);
		listMeteorites.addAll(listToAdd);
		
		listMeteorites.forEach(m->{
				m.dessiner(g, gameOver);
		});
	}

}
