package IO;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;



public class DownloadManager {

	private static final String			temp		= System.getProperty("java.io.tmpdir");
	private static final String			mark		= ".part";

	private final File					root;
	private boolean						halt;

	private int							capacity	= Integer.MAX_VALUE;

	private final Queue<Download>		priority	= new ConcurrentLinkedQueue<Download>();
	private final Queue<Download>		active		= new ConcurrentLinkedQueue<Download>();

	private final Queue<LoadListener>	listeners	= new ConcurrentLinkedQueue<LoadListener>();

	private DownloadManager				manager;

	private Thread						thread;
	private Runnable					run			= new Runnable() {
														@Override
														public void run() {
															while (!priority.isEmpty() || !active.isEmpty()) {
																while (!priority.isEmpty()) {
																	if (halt) {
																		shutdown();
																		return;
																	}
																	new DownloadWorker(manager, priority.poll());
																}
																while (!active.isEmpty()) {
																	if (halt) {
																		shutdown();
																		return;
																	}
																	if (!priority.isEmpty())
																		break;
																	try {
																		if (active.size() > capacity) {
																			active.poll();
																			break;
																		}
																		new DownloadWorker(manager, active.poll());
																	}
																	catch (NullPointerException e) {}
																}
															}
														}
													};

	public DownloadManager(String folder) {
		root = new File(temp, folder);
		root.mkdirs();
		manager = this;
	}

	public DownloadManager(String folder, int capacity) {
		this(folder);
		this.capacity = capacity;
	}

	public void addLoadListener(LoadListener dl) {
		listeners.add(dl);
	}

	public void removeLoadListener(LoadListener dl) {
		listeners.remove(dl);
	}

	public boolean queueDownload(String name, URL src, String dst, boolean urgent) {
		Download d = new Download(name, src, new File(root, dst));
		if (priority.contains(d))
			return false;
		if (active.contains(d)) {
			if (urgent) {
				active.remove(d);
				priority.add(d);
			}
			return false;
		}
		if (urgent)
			priority.add(d);
		else
			active.add(d);
		if (thread == null || !thread.isAlive()) {
			thread = new Thread(run);
			thread.start();
		}
		return true;
	}

	public File getFile(String file) {
		File marker = new File(root, file + mark);
		if (marker.exists()){
			return null;
		}
		File f = new File(root, file);
		if (f.exists())
			return f;
		return null;
	}

	public void halt() {
		halt = true;
	}

	private void shutdown() {
		priority.clear();
		active.clear();
	}

	public void doneLoading() {
		for (LoadListener listener : listeners)
			listener.loadComplete();
	}
	
	public static File getMark(File src){
		return new File(src.getParentFile(), src.getName() + ".part");
	}
}
