package API;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

public class SplashScreen extends JComponent {
	private static final long serialVersionUID = -2911679286363355249L;
	private BufferedImage splash;

	public SplashScreen(String file) {
		try {
			splash = ImageIO.read(getClass().getResourceAsStream(file));
		}
		catch (IOException e) {
			e.printStackTrace();
			System.out.println("Failed to load splash screen.");
			splash = null;
		}
	}

	public void paint(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(0, 0, getWidth(), getHeight());
		if (splash != null)
			g.drawImage(splash, (getWidth() - splash.getWidth()) / 2, (getHeight() - splash.getHeight()) / 2, null);
	}

}
