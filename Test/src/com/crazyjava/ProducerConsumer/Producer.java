package com.crazyjava.ProducerConsumer;

import java.util.concurrent.BlockingQueue;

public class Producer extends Thread {
	BlockingQueue<String> bq;
	public Producer(BlockingQueue<String> bq){
		this.bq=bq;
	}
	
	public void run(){
		String[] strArr = new String[]{"Java","Structs","Spring"};
		
		
		for(int i=0;i<99999;i++){
			System.out.println(getName()+"生产者准备生产元素！");
			try{
				Thread.sleep(200);
				bq.put(strArr[i%3]);
			}
			catch(InterruptedException ex){
				ex.printStackTrace();
			}
			System.out.println(getName()+"生产完成"+bq);
		}
	}

}
