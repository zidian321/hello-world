package com;

public class DrawTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Account acct =new Account("1234567",1000);
		new DrawThread("甲",acct,800).start();
		new DrawThread("乙",acct,800).start();
	}

}
