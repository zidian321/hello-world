package com;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Test {
	public static void main(String[] args){
//		int a = 5;
//		System.out.println((a>5)?10.9:9);
//		char x ='x';
//		int i =10;
//		System.out.println((a>5)?i:x);
//		System.out.println((a>5)?10:x);
//		int num =32;
//		System.out.prtln(num<<32);
		//System.out.println(new Test().readFileByLines("CPSDemo.xml"));
		short a =10;
	    short b=2;
		a = (short) ((int) a+b);
	}
	public  String readFileByLines(String fileName) {

        InputStream file = null;

        BufferedReader reader = null;

        InputStreamReader inputFileReader = null;

        String content = "";

        String tempString = null;

        try {

            file = this.getClass().getResourceAsStream(fileName);

            inputFileReader = new InputStreamReader(file, "utf-8");

            reader = new BufferedReader(inputFileReader);

            // 一次读入一行，直到读入null为文件结束

            while ((tempString = reader.readLine()) != null) {

                content += tempString+"\r\n";

            }

            reader.close();

        } catch (IOException e) {

            e.printStackTrace();

            return null;

        } finally {

            if (reader != null) {

                try {

                    reader.close();

                } catch (IOException e1) {

                }

            }

        }

        return content;

    }
}
