package API;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

public abstract class JSelect extends JComponent implements MouseListener, MouseWheelListener {

	private static final long serialVersionUID = 2806438017491583793L;
	private BufferedImage background;
	private BufferedImage option;

	private int fontSize = 14;
	private int border = 10;
	private int height;
	private int textHeight = 30;
	private int textDesc;

	private int offset;

	String title = "";
	String subtitle = "Guild Wars 2";

	Object selected;
	protected Object[] selection;

	public JSelect(String back, String options) {
		super();
		try {
			InputStream b = getClass().getResourceAsStream(back);
			if (b != null)
				background = ImageIO.read(b);
			InputStream o = getClass().getResourceAsStream(options);
			if (o != null)
				option = ImageIO.read(o);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		Dimension d = new Dimension(200, 300);
		setPreferredSize(d);
		setSize(d);
		addMouseListener(this);
		addMouseWheelListener(this);
	}

	public abstract void select(int i);

	public abstract void unselect();

	public void setSelection(Object[] options) {
		selection = options;
		offset = 0;
	}

	private void clampOffset() {
		offset = Math.max(0, Math.min(selection.length - getMax(), offset));
	}

	private int getSelection(int x, int y) {
		if (x < border || x >= getWidth() - border)
			return -1;
		y -= border * 3 + textHeight * 2;
		if (y % (textHeight + border) < border)
			return -1;
		return y / (textHeight + border) + offset;
	}

	private int getMax() {
		return (getHeight() - border * 3 - textHeight * 2) / (textHeight + border);
	}

	public void paint(Graphics g) {
		super.paint(g);

		g.setFont(new Font(Font.DIALOG, Font.BOLD, fontSize));
		FontMetrics fm = g.getFontMetrics();

		height = textHeight;

		/**
		 * Draw background.
		 */
		g.setColor(Color.white);
		g.fillRect(0, 0, getWidth(), getHeight());
		if (background != null)
			g.drawImage(background, 0, 0, getWidth(), getHeight(), null);

		/**
		 * Draw title and subtitle.
		 */
		g.drawString(title, (getWidth() - fm.getStringBounds(title, g).getBounds().width) / 2, height);
		height += textHeight + border;
		g.drawString(subtitle, (getWidth() - fm.getStringBounds(subtitle, g).getBounds().width) / 2, height);
		height += 2 * border;

		/**
		 * Draw selection background.
		 */
		if (selection != null) {
			if (option != null)
				for (int i = 0; i <= Math.min(selection.length, getMax()); i++)
					if (i % 2 == 0)
						g.drawImage(option, 0, height - border + i * (textHeight + border), getWidth(), textHeight, null);
					else
						g.drawImage(option, getWidth(), height - border + i * (textHeight + border), -getWidth(),
								textHeight, null);
			for (int i = offset; i < Math.min(selection.length, offset + getMax()); i++) {
				height += textHeight + border;
				g.setColor(Color.white);
				String s = selection[i].toString();
				g.drawString(s, (getWidth() - fm.getStringBounds(s, g).getBounds().width) / 2, height - textDesc - 12);
			}
		}

	}

	@Override
	public void mouseClicked(MouseEvent me) {

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {}

	@Override
	public void mouseExited(MouseEvent arg0) {}

	@Override
	public void mousePressed(MouseEvent me) {
		if (me.getButton() == MouseEvent.BUTTON1) {
			int i = getSelection(me.getX(), me.getY());
			if (i < 0)
				return;
			if (i >= selection.length)
				return;
			select(i);
		}
		if (me.getButton() == MouseEvent.BUTTON3)
			unselect();
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {}

	@Override
	public void mouseWheelMoved(MouseWheelEvent me) {
		if (me.getWheelRotation() < 0)
			offset--;
		if (me.getWheelRotation() > 0)
			offset++;
		clampOffset();
		repaint();
	}

}
