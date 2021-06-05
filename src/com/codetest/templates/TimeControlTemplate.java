package com.codetest.templates;

public class TimeControlTemplate {

	/**
	 *  time[0] = hh
	 *  time[1] = mm
	 */
	int[] time = new int[2];
	
	static void spendTime(int[] time, int timeToSpend) {
		time[1] += timeToSpend;
		while (time[1] >= 60) {
			time[0]++;
			time[1] -= 60;
		}
	}
	
	static String toStrTime(int[] time) {
		return String.format("%02d", time[0]) + ":" + String.format("%02d", time[1]);
	}
	
	
}

/** sample using Object */ 
class SimpleTime {
	
	/** omit getter/setter */
 	int hh;
	int mm;
	
	SimpleTime(int hh, int mm) {
		this.hh = hh;
		this.mm = hh;
	}
	
	void spendTime(int timeToSpend) {
		mm += timeToSpend;
		while (mm >= 60) {
			hh++;
			mm -= 60;
		}
	}
	
	String getStrTime() {
		return String.format("%02d", hh) + ":" + String.format("%02d", mm);
	}
}