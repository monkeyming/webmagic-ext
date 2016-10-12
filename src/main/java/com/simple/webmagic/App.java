package com.simple.webmagic;

import us.codecraft.webmagic.Spider;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) throws InterruptedException {
		// 1
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 2
		Thread thread = new Thread(new Runnable() {
			public void run() {
				System.out.println(112345);
				// 2.1
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
				}
				// 2.2
				synchronized (this) {
					try {
						wait(3000);
					} catch (InterruptedException e) {
					}
				}
			}
		});
		thread.start();
		thread.join();

		// 3
		System.out.println(3);
		new App().waitTest();

		System.out.println("main");
	}

	private synchronized void waitTest() {
		try {
			wait(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
