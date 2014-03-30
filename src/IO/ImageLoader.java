package IO;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.imageio.ImageIO;



/**
 * Image loading thread. Loads Images from File and caches them as BufferedImages. Requested files are pushed onto a
 * loading queue. As images finish loading, new ones are pulled off the queue. If the queue reaches maximum capacity,
 * the oldest requests are pushed out of queue. Capacity should be set high enough that new requests do not constantly
 * cycle through the queue without a chance to load. Loaded images are caches for future calls.
 * 
 * @author Shadow Stalker
 */

public class ImageLoader {
	private final HashMap<File, BufferedImage>	cache		= new HashMap<File, BufferedImage>();

	private final Queue<File>					files		= new ConcurrentLinkedQueue<File>();

	private final Queue<LoadListener>			listeners	= new ConcurrentLinkedQueue<LoadListener>();

	private int									cap			= Integer.MAX_VALUE;
	private boolean								halt;
	private boolean								interrupt;
	private Thread								thread;
	private Runnable							run			= new Runnable() {
																@Override
																public void run() {
																	while (!files.isEmpty()) {
																		if (halt) {
																			files.clear();
																			cache.clear();
																			return;
																		}
																		if (interrupt) {
																			files.clear();
																			return;
																		}
																		File file = files.poll();
																		if (files.size() > cap)
																			continue;
																		try {
																			cache.put(file, ImageIO.read(file));
																			for (LoadListener listener : listeners)
																				listener.loadComplete();
																		}
																		catch (IOException e) {
																		}
																	}
																}
															};

	public ImageLoader() {

	}

	/**
	 * Sets the image loading queue capacity. If the queue is at maximum capacity, files that are queued for loading
	 * push older requests out of queue.
	 * 
	 * @param cap
	 *            Maximum number of files in queue.
	 */
	public ImageLoader(int cap) {
		this.cap = cap;
	}

	public BufferedImage getImage(File file) {
		if (file == null)
			return null;
		System.err.println(DownloadManager.getMark(file).getAbsolutePath());
		if (DownloadManager.getMark(file).exists())
			return null;
		if (cache.containsKey(file)) 
			return cache.get(file);
		if (files.contains(file))
			return null;
		files.add(file);
		if (thread == null || !thread.isAlive()) {
			thread = new Thread(run);
			thread.start();
		}
		return null;
	}

	public void addLoadListener(LoadListener dl) {
		listeners.add(dl);
	}

	public void removeLoadListener(LoadListener dl) {
		listeners.remove(dl);
	}

	public void interrupt() {
		interrupt = true;
	}

	public void halt() {
		halt = true;
	}
}
