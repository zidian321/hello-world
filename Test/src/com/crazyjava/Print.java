package com.crazyjava;

public class Print {

	// 测试

	public static void main(String[] args) {

	Object obj = new Object();

	// 启动两个线程

	Thread1 t1 = new Thread1(obj);

	Thread2 t2 = new Thread2(obj);


	t1.start();

	t2.start();

	}

	}


	 

	class Thread1 extends Thread {

	private Object obj;


	public Thread1(Object obj) {

	this.obj = obj;

	}


	public void run() {

	// 加锁

	synchronized (obj) {

	// 打印1-52

	for (int i = 1; i < 53; i++) {

	System.out.print(i);

	if (i % 2 == 0) {

	// 不能忘了唤醒其它线程

	obj.notifyAll();

	try {

	obj.wait();

	} catch (InterruptedException e) {

	e.printStackTrace();

	}

	}

	}


	}

	}

	}


	 

	class Thread2 extends Thread {

	private Object obj;


	public Thread2(Object obj) {

	this.obj = obj;

	}


	public void run() {

	synchronized (obj) {

	// 打印A-Z

	for (int i = 0; i < 26; i++) {

	System.out.print((char) ('A' + i));

	// 不能忘了唤醒其它线程

	obj.notifyAll();

	try {

	obj.wait();

	} catch (InterruptedException e) {

	e.printStackTrace();

	}

	}

	}

	}

	}