package com;

public class Wolf {
	public Wolf(){
	test();
	}
	private int leg =4;
	public static void main(String [] args){
		Wolf wol = new littleWolf();
		wol.leg=6;
		System.out.println(wol.leg);
		wol.test();
	}
	
	public void test(){
		System.out.println(this.leg);
	}
}

class littleWolf extends Wolf{
	//public String leg="4 legs";	
	public void test(){
		System.out.println("Son");
	}

}
