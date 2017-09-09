package com.crazyjava.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Account {
	private String accountNo;
	private final Lock lock = new ReentrantLock();
	private final Condition cond = lock.newCondition();
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
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	private double balance;
	private boolean flag=false;
	public Account(){
		
	}

	public Account(String accountNo, double balance){
		this.accountNo=accountNo;
		this.balance=balance;
	}
	int i=0;
	int j=0;
	public  void draw(double drawAmount){
		lock.lock();
		try
		{
		   if(!flag){
			   System.out.println("取钱者在等待");
			   cond.await();
			   System.out.println("取钱者等待结束");
			  
		   }
		   else {
			   System.out.println("取钱者在取钱");
			   System.out.println(Thread.currentThread().getName()+"第"+i+"取钱"+drawAmount);
			   balance-=drawAmount;
			   i++;
			   System.out.println("账户余额为"+balance);
			   flag =false;
			   cond.signalAll();
		   }
		}
		catch(InterruptedException ex){
			ex.printStackTrace();
		}
		finally{
			lock.unlock();
		}
		//notifyAll();
	}
	
	public  void deposit(double depositAmount){
		lock.lock();
		try
		{
			if(flag){
				cond.await();
			}
			else{
				System.out.println(Thread.currentThread().getName()+"第"+j+"存款"+depositAmount);
				balance+=depositAmount;
				j++;
				System.out.println("账户余额为：" + balance);
				flag=true;
				cond.signalAll();
				System.out.println("已通知取钱者");
			}
		}
		catch (InterruptedException ex){
			ex.printStackTrace();
		}
		finally{
			lock.unlock();
		}
		//notifyAll();
	}
	public int hashCode(){
		return accountNo.hashCode();
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
