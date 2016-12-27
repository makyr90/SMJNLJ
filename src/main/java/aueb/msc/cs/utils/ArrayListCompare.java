package aueb.msc.cs.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ArrayListCompare {
	public static void sort(ArrayList<String[]> list,final int col){
		 Collections.sort(list,new Comparator<String[]>() {
	            public int compare(String[] strings, String[] otherStrings) {
	                if (Integer.parseInt(strings[col])>(Integer.parseInt(otherStrings[col])))
	                	return  1;
	                else if (Integer.parseInt(strings[col])<(Integer.parseInt(otherStrings[col]))) 
	                	return -1;
	                else
	                	return 0;
						
					}
	            
	        });
		
	}

}
