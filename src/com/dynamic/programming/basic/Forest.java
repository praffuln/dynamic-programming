package com.dynamic.programming.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

public class Forest {

	public static void main(String[] args) {
		int[] M = { 1, 8, 7, 3, 4, 1, 8 };
		int[] N = { 6, 4, 1, 8, 5, 1, 7 };
		System.out.println(solution2(M, N));
		int[] M1 = { 5, 5, 5, 7, 7, 7 };
		int[] N1 = { 3, 4, 5, 1, 3, 4 };
		System.out.println(solution2(M1, N1));
		int[] M2 = { 6, 10, 1, 4, 4 };
		int[] N2 = { 2, 5, 3, 1, 6 };
		System.out.println(solution2(M2, N2));
		int[] M3 = { 4, 1, 5, 4 };
		int[] N3 = { 4, 5, 1, 3 };
		System.out.println(solution2(M3, N3));
		
		int[] M4 =null;
		int[] N4 = {};
		System.out.println(solution2(M4, N4));

		HashMap m = new HashMap();
		Object o1 = new Object();
		Object o2 = o1;
		m.put(o1, 1);
		m.put(o2, 2);
		System.out.println(m.get(o2));
		
		
		ArrayList l = new ArrayList<>(2);
		l.add(1);l.add(1);l.add(3);
		System.out.println(l.size());
		
		HashSet s = new HashSet();
		s.add(new Integer(1));
		s.add(new Integer(1));
		s.add(new Integer(2));
		System.out.println(s.size());
	}

	public static Integer solution2(int[] X, int[] Y) {
		if(X ==null || X.length == 0 || Y== null || Y.length == 0) return 0;
		int maxX = 0;
		int maxY = 0;
		ArrayList<Integer> xArray = new ArrayList<>();
		ArrayList<Integer> yArray = new ArrayList<>();

		for (int i : X) {
			xArray.add(i);
		}

		for (int i : Y) {
			yArray.add(i);
		}

		Collections.sort(xArray);
		Collections.sort(yArray);

		for (int i = 1; i < xArray.size(); i++) {
			int j = xArray.get(i) - xArray.get(i - 1);
			maxX = j >= maxX ? j : maxX;
		}

		for (int i = 1; i < yArray.size(); i++) {
			int j = yArray.get(i) - yArray.get(i - 1);
			maxY = j >= maxY ? j : maxY;
		}

		return maxX >= maxY ? maxX : maxY;
	}

}
