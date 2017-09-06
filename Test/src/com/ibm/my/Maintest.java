package com.ibm.my;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Maintest {
	
	public static void main(String args[]) throws IOException{
		File fil = new File("123.txt");
		System.out.println(System.getProperty("java.class.path"));
		FileReader fr = new FileReader(fil);
		BufferedReader br = new BufferedReader (fr);
		String temp;
		while((temp=br.readLine())!=null){
			System.out.print(temp);
			
		}
	}

}
