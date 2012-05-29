package net.minecraft.server;

import java.util.logging.Handler;
import java.util.logging.Logger;

import net.minecraft.server.util.io.PrintStreamHandler;

public class Server implements Runnable {
	private static final int BUILD = 0;

	private static final Logger logger = Logger.getLogger(Server.class.getName());

	public static void main(final String[] params) {
		final Server server = new Server();
		new Thread(server).start();
	}

	public void run() {
		setup();
		logger.info("Starting minecraft server daemon (build " + BUILD + ")");
	}

	private void setup() {
		final Logger log = Logger.getLogger("");
		for (final Handler handler : log.getHandlers()) {
			log.removeHandler(handler);
		}
		log.addHandler(new PrintStreamHandler());
	}
}
