package com;

import java.util.concurrent.locks.ReentrantLock;

public class Account {
	private final ReentrantLock lock = new ReentrantLock();
	private String accountNo;
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	private double balance;
	public Account(){
		
	}
	public Account(String accountNo,double balance){
		this.accountNo=accountNo;
		this.balance= balance;
	}
	public int hashCode(){
		return accountNo.hashCode();
	}
	public void draw(double drawAmount){
		lock.lock();
		try{
			if(balance >= drawAmount){
				System.out.println(Thread.currentThread().getName()+"  取钱成功！突出钞票"+drawAmount);
	
			try{
				Thread.sleep(1);
			}
			catch(InterruptedException ex){
				ex.printStackTrace();
			}
			
			balance -= drawAmount;
			System.out.println("\t 余额为： "+ balance);
			}
			else {
				System.out.println(Thread.currentThread().getName()+"  取钱失败！余额不足");
			}
		}finally{
			lock.unlock();
		}
		
	}
	public boolean equals(Object obj){
		if(this.equals(obj))
			return true;
		if(obj!=null&&obj.getClass()==Account.class){
			Account target =(Account)obj;
			return target.getAccountNo().equals(accountNo);
		}
		return false;
	}

}
