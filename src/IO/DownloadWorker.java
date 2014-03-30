package IO;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;



public class DownloadWorker implements Runnable {

	private boolean			halt;
	private DownloadManager	manager;
	private Download		download;

	public DownloadWorker(DownloadManager manager, Download download) {
		this.manager = manager;
		this.download = download;
		new Thread(this).start();
	}

	private void download() throws IOException {
		if (!download.dst.exists()) {
			download.dst.getParentFile().mkdirs();
			download.dst.createNewFile();
		}

		File marker = DownloadManager.getMark(download.dst);
		marker.createNewFile();

		File out = download.dst;
		DataInputStream dis = new DataInputStream(download.src.openStream());
		DataOutputStream dos = new DataOutputStream(new FileOutputStream(out));
		byte[] data = new byte[65536];
		int bytesRead;
		while ((bytesRead = dis.read(data)) != -1) {
			dos.write(data, 0, bytesRead);
			if (halt) {
				dos.flush();
				dos.close();
				out.delete();
				marker.delete();
				System.out.println("\tInterrupted download from [" + download.src + "]. Shutting down.");
				return;
			}
		}
		dos.flush();
		dis.close();
		dos.close();
		marker.delete();
		manager.doneLoading();
		System.out.println("\tFinished downloading from [" + download.src + "].");
	}

	public void halt() {
		halt = true;
	}

	@Override
	public void run() {
		try {
			download();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

}
