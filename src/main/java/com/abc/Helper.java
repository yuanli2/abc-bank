package com.abc;

import java.util.Calendar;

public final class Helper {
	public static double calcInterest(double principle, double rate, double days) {
		double factor = Math.pow(1.0 + rate/days, days) - 1;
		return principle*factor;
	}
	
	public static long calcDayDiff(Calendar c1, Calendar c2) {
		return (c2.getTimeInMillis()-c1.getTimeInMillis())/(24*60*60*1000);
	}
}
