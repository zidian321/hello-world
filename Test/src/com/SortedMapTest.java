package com;

import java.util.Comparator;
import java.util.Map.Entry;
import java.util.TreeMap;

public class SortedMapTest {
	
	public static void main(String[] args){
		TreeMap<String,String> myTreeMap = new TreeMap<>();
		myTreeMap.put("4","four");
		myTreeMap.put("3","three");
		myTreeMap.put("2","two");
		myTreeMap.put("1","one");
		
		for(Entry en:myTreeMap.entrySet()){
			System.out.println(en.getKey()+" "+en.getValue());
			
		}
	}

}

class MyComparator implements Comparator<String>{

	@Override
	public int compare(String o1, String o2) {
		// TODO Auto-generated method stub
		return o1.compareTo(o2);
	}

	
}