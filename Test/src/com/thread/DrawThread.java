package com.thread;

public class DrawThread extends Thread {
	private Account account;
	private double drawAmount;
	public DrawThread(String name,Account account,double drawAmount){
		super(name);
		this.account =account;
		this.drawAmount =drawAmount;
	}
	public void run(){
		for(int i=0;i<30;i++){
			//System.out.println("取钱者第" +i+ "次取款");
			account.draw(drawAmount);
		}
	}

}
