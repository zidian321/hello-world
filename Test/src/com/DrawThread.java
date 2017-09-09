package com;

public class DrawThread extends Thread {
//6235 0101 7030 0536 252
	private Account account;
	private double drawAmount;
	public DrawThread(String name, Account account,double drawAmount){
		super(name);
		this.account=account;
		this.drawAmount=drawAmount;
	}
	public void run(){
		
          account.draw(drawAmount);
	}
	}
//}
