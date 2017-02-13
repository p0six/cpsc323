package com.cpsc323.assignments.assignment03;

/*
 * Name			Michael Romero, Austin Suarez, Sean Hillenbrand
 * Project No.	3, Problem 2
 * Due Date		Feb 16, 2017
 * Professor	Ray Ahmadnia
 * 
 * Purpose:		This program will determine if a given word, stored within "data.txt", is accepted 
 * 				through FA with L=aa*b + bb*
 */

import java.io.BufferedReader;
import java.io.FileReader;

public class Problem02 {
	public static void main(String[] args) throws Exception {
		int[][] cfa = { {1,2}, {1,3}, {4,2}, {4,4}, {4,4} }; // L = aa*b + bb*

		BufferedReader in = new BufferedReader(new FileReader("src//com//cpsc323//assignments//assignment03//data.txt"));

		String line;
		while ((line = in.readLine()) != null) {
			String token[] = line.split("=");
			String key = token[0];
			String value = token[1];
			
			int state = 0, col = 0;//, i = 0;
			boolean accepted = true;
			
			for (int i = 0; value.charAt(i) != '$'; i++) {

				switch (value.charAt(i)) {
				case 'a':
					col = 0;
					break;
				case 'b':
					col = 1;
					break;
				}
				state = cfa[state][col];
				if (state == 4) {
					accepted = false;
					break;
				}
			}

			if (accepted) {
				System.out.println(key + "=" + value + "\tAccepted");	
			} else {
				System.out.println(key + "=" + value + "\tRejected");
			}	
		}
		in.close();	
	}
}