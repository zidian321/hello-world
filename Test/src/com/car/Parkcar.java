package com.car;

import java.util.ArrayList;

public class Parkcar extends Thread {

	private ArrayList arr;

	public ArrayList getArr() {
		return arr;
	}

	public void setArr(ArrayList arr) {
		this.arr = arr;
	}


	public void run(){
		synchronized(arr){
			while(true){
				
			}
		}
	}
	
}
