package Map;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JComponent;

import API.API;
import IO.LoadListener;

public class MapDisplay extends JComponent implements MouseListener, MouseMotionListener, MouseWheelListener, LoadListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5299630973689002080L;

	protected int zoomCache = 3;

	/**
	 * Map dimensions in pixels at max zoom level.
	 */
	protected Rectangle mapBounds;
	protected Rectangle lock;
	
	/**
	 * Range of zoom levels. Each increase in zoom level equates a resulting doubling of map resolution.
	 */
	protected int minZoom;
	protected int maxZoom;
	protected int zoom;

	/**
	 * The number of map pixels a pixel on screen represents at each zoom level. At max zoom, a pixel on screen represents a
	 * 1x1 block of the map.
	 */
	private int[] mapScale;

	protected int dragX, dragY;
	protected Point offset = new Point();

	public MapDisplay() {
		addMouseListener(this);
		addMouseMotionListener(this);
		addMouseWheelListener(this);
		API.mapTileLoader.addLoadListener(this);
		API.mapLoader.addLoadListener(this);
	}

	public void init() {
		mapScale = new int[maxZoom - minZoom + 1];
		mapScale[maxZoom - minZoom] = 1;
		for (int i = maxZoom - 1; i >= minZoom; i--)
			mapScale[i - minZoom] = mapScale[i - minZoom + 1] * 2;
	}

	public void clamp() {
		if (getWidth() * mapScale[zoom] > lock.x2 - lock.x1)
			offset.x = -(getWidth() * mapScale[zoom] - (lock.x2 - lock.x1)) / 2;
		else
			offset.x = Math.max(lock.x1, Math.min(lock.x2 - getWidth() * mapScale[zoom], offset.x));
		if (getHeight() * mapScale[zoom] > lock.y2 - lock.y1)
			offset.y = -(getHeight() * mapScale[zoom] - (lock.y2 - lock.y1)) / 2;
		else
			offset.y = Math.max(lock.y1, Math.min(lock.y2 - getHeight() * mapScale[zoom], offset.y));
	}

	public int mapWidthTile(int zoom) {
		return (mapWidthPx(zoom) - 1) / 256;
	}

	public int mapHeightTile(int zoom) {
		return (mapHeightPx(zoom) - 1) / 256;
	}

	public int mapWidthPx(int zoom) {
		return (mapBounds.x2 - mapBounds.x1) / mapScale[zoom];
	}

	public int mapHeightPx(int zoom) {
		return (mapBounds.y2 - mapBounds.y1) / mapScale[zoom];
	}

	/*
	 * private int screenWidthMap() { return getWidth() * mapScale[zoom]; }
	 * 
	 * private int screenHeightMap() { return getHeight() * mapScale[zoom]; }
	 */
	public int screenWidthTile() {
		return (getWidth() + 255) / 256;
	}

	public int screenHeightTile() {
		return (getHeight() + 255) / 256;
	}

	public int offsetXTile(int zoom) {
		return offsetXPx(zoom) / 256;
	}

	public int offsetYTile(int zoom) {
		return offsetYPx(zoom) / 256;
	}

	public int offsetXPx(int zoom) {
		return offset.x / mapScale[zoom];
	}

	public int offsetYPx(int zoom) {
		return offset.y / mapScale[zoom];
	}
	
	public void setMapBounds(int x1, int y1, int x2, int y2){
		if (x2 <= x1||y2 <= y1)
			return;
		mapBounds = new Rectangle(x1, y1, x2, y2);
	}

	public void setLock(int x1, int y1, int x2, int y2){
		if (x2 <= x1||y2 <= y1)
			return;
		lock = new Rectangle(x1, y1, x2, y2);
	}

	public Rectangle getScreenBounds(int zoom) {
		int xoff = Math.max(0, offsetXTile(zoom));
		int yoff = Math.max(0, offsetYTile(zoom));
		int xMin = Math.max(0, xoff - 1);
		int yMin = Math.max(0, yoff - 1);
		int xMax = Math.min(mapWidthTile(zoom), xoff + screenWidthTile() + 1);
		int yMax = Math.min(mapHeightTile(zoom), yoff + screenHeightTile() + 1);
		return new Rectangle(xMin, yMin, xMax, yMax);
	}

	public void paint(Graphics g) {
		clamp();
		for (int z = zoomCache; z > 0; z--)
			if (zoom >= minZoom + z)
				drawMap(g, Math.max(minZoom, zoom - z), z == zoomCache);
		drawMap(g, zoom, false);
	}

	private void drawMap(Graphics g, int zoom, boolean urgent) {
		Rectangle bounds = getScreenBounds(zoom);
		for (int x = bounds.x1; x <= bounds.x2; x++)
			for (int y = bounds.y1; y <= bounds.y2; y++) {
				int scale = 256 * mapScale[zoom] / mapScale[this.zoom];
				int dx1 = scale * x - offsetXPx(this.zoom);
				int dx2 = dx1 + scale;
				int dy1 = scale * y - offsetYPx(this.zoom);
				int dy2 = dy1 + scale;
				Tile t = Tile.getTile((byte) 1, (byte) zoom, (byte) x, (byte) y, urgent);
				if (t != null)
					g.drawImage(t.tileImage, dx1, dy1, dx2, dy2, 0, 0, t.tileImage.getWidth(), t.tileImage.getHeight(), null);
			}
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {

	}

	@Override
	public void mouseExited(MouseEvent arg0) {

	}

	@Override
	public void mousePressed(MouseEvent me) {
		dragX = me.getX();
		dragY = me.getY();
	}

	@Override
	public void mouseReleased(MouseEvent me) {}

	@Override
	public void mouseDragged(MouseEvent me) {
		int nx = me.getX();
		int ny = me.getY();
		offset.translate((dragX - nx) * mapScale[zoom], (dragY - ny) * mapScale[zoom]);
		dragX = nx;
		dragY = ny;
		repaint();
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {

	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent me) {
		offset.x += me.getX() * mapScale[zoom];
		offset.y += me.getY() * mapScale[zoom];
		if (me.getWheelRotation() > 0) {
			zoom = Math.max(minZoom, Math.min(maxZoom, zoom - 1));
		}
		if (me.getWheelRotation() < 0) {
			zoom = Math.max(minZoom, Math.min(maxZoom, zoom + 1));
		}
		offset.x -= me.getX() * mapScale[zoom];
		offset.y -= me.getY() * mapScale[zoom];
		repaint();
	}

	@Override
	public void loadComplete() {
		repaint();
	}

}
