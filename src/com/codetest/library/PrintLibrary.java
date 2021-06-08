package com.codetest.library;

import java.io.PrintWriter;

public class PrintLibrary {

	void flushPrint(Object[] arr) {
		
		PrintWriter out = new PrintWriter(System.out); 
		for (int i = 0 ; i < arr.length ; i++) { 
		    out.println(arr[i]); 
		}  
		out.flush();
		
	}
	
	void separateByWS(Object[] arr) {
		
	    StringBuilder sb = new StringBuilder();
	    sb.append(arr[0]);
	    for (int i = 1; i < arr.length; i++) {
	        sb.append(" ");
	        sb.append(arr[i]);
	    }
	    System.out.println(sb.toString());
	    
	}
	
}
