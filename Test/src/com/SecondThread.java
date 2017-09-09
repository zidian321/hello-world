package com;

public class SecondThread implements Runnable{
    private int i=0;

    private synchronized void out(String s){
    	System.out.println(s);
    }
	@Override
	public void run() {
		// TODO Auto-generated method stub
			
		for(;i<100;i++){
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			out(Thread.currentThread().getName()+"   "+i);
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		for(int i=0;i<100;i++){
			System.out.println(Thread.currentThread().getName());
			if(i==20){
				SecondThread st = new SecondThread();
				new Thread(st,"新线程1").start();
				new Thread(st,"新线程2").start();
			}
		}

	}
}
