package com.marakana.concurrency;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public abstract class Actor implements Runnable {
	private final BlockingQueue<Object> messages = new LinkedBlockingQueue<Object>();

	public final void tell(Object message) {
		try {
			messages.put(message);
		} catch (InterruptedException e) {
		}
	}

	protected abstract void receive(Object message);

	@Override
	public final void run() {
		while (true) {
			try {
				receive(messages.take());
			} catch (InterruptedException e) {
			}
		}
	}

}
