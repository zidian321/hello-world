package com.thread;

public class DepositThread extends Thread {
 private Account account;
 private double depoistAmount;
 public DepositThread(String name,Account account,double depositAmount){
	 super(name);
	 this.account = account;
	 this.depoistAmount = depositAmount;
 }
 public void run(){
	 for(int i=0;i<10;i++){
		// System.out.println("存钱者"+getName()+"第"+i+"存钱");
		 account.deposit(depoistAmount);
	 }
 }
}
