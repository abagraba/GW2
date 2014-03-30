package API;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;

import Map.TyriaMap;

public class Window extends JFrame implements WindowListener {

	NavList nv;
	TyriaMap md;
	SplashScreen ss;

	public Window() {
		GW2Data.init();
		Dimension size = new Dimension(1280, 800);
		setMinimumSize(size);
		setSize(size);

		addWindowListener(this);

		nv = new NavList(this);
		md = new TyriaMap();
		ss = new SplashScreen("/GW2Splash.png");

		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(nv, BorderLayout.EAST);
		openSplash();

		md.init("1");

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
		pack();
		setVisible(true);
	}

	public void openSplash(){
		getContentPane().remove(md);
		getContentPane().add(ss, BorderLayout.CENTER);
		validate();
		repaint();
	}
	
	public void openMap(){
		getContentPane().remove(ss);
		getContentPane().add(md, BorderLayout.CENTER);
		validate();
		repaint();
	}

	@Override
	public void windowActivated(WindowEvent e) {}

	@Override
	public void windowClosed(WindowEvent e) {
		API.clean();
	}

	@Override
	public void windowClosing(WindowEvent e) {}

	@Override
	public void windowDeactivated(WindowEvent e) {}

	@Override
	public void windowDeiconified(WindowEvent e) {}

	@Override
	public void windowIconified(WindowEvent e) {}

	@Override
	public void windowOpened(WindowEvent e) {}

	public static void main(String[] args) {
		new Window();
	}
}
