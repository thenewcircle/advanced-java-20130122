package com.marakana.concurrency;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public abstract class Actor implements Runnable {
	private final BlockingQueue<Object> messages = new LinkedBlockingQueue<Object>();
	private boolean shutdown = false;

	public final void tell(Object message) {
		try {
			messages.put(message);
		} catch (InterruptedException e) {
		}
	}

	protected final void shutdown() {
		shutdown = true;
	}

	protected abstract void receive(Object message);

	@Override
	public final void run() {
		while (!shutdown) {
			try {
				receive(messages.take());
			} catch (InterruptedException e) {
			}
		}
	}

}
