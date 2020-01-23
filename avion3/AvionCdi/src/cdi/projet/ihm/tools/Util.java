package cdi.projet.ihm.tools;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public final class Util {

	private Util() {
	}
	
	public static Image getImg(String s)  {
		Image res = null;
		try {
			res = ImageIO.read(new File(s));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return res;
	}
	
}
