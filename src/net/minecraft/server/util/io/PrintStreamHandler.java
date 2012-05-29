package net.minecraft.server.util.io;

import java.io.PrintStream;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class PrintStreamHandler extends Handler {
	private final PrintStream out;

	public PrintStreamHandler() {
		this(System.out);
	}

	public PrintStreamHandler(final PrintStream out) {
		this.out = out;
	}

	@Override
	public void close() throws SecurityException {
		out.close();
	}

	@Override
	public void flush() {
		out.flush();
	}

	@Override
	public synchronized void publish(final LogRecord record) {
		final String text = record.getMessage().trim();
		if (text.length() == 0) {
			return;
		}
		out.print('[');
		out.print(record.getLevel().getName());
		out.print("] ");
		out.print(record.getLoggerName());
		out.print(": ");
		out.print(text);

		final Throwable throwable = record.getThrown();
		if (throwable != null) {
			throwable.printStackTrace(out);
		} else {
			out.println();
		}
	}
}
