package com.cpsc323.assignments.assignment02;

/*
 * Name			Michael Romero, Austin Suarez, Sean Hillenbrand
 * Project No.	2 
 * Due Date		Feb 09, 2017
 * Professor	Ray Ahmadnia
 * 
 * Purpose:		This program evaluates postfix expressions which can include words and integers. 
 */

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class PostfixCalculator02 {
	public static void main(String[] args) {
	  Scanner s = new Scanner(System.in);
	  boolean continueLoop = true;
	  while(continueLoop) {
		  Map<String, Integer> m = new HashMap<String, Integer>();
		  Deque<Integer> values = new ArrayDeque<Integer>();
		  String strTemp = new String(), intTemp = new String();
		  System.out.print("Enter a postfix expression: ");
		  String postfix = s.nextLine();
		  for (int i = 0; i < postfix.length(); i++) {
			char c = postfix.charAt(i);
			if (Character.isAlphabetic(c)) {
				strTemp += c;
			} else if (Character.isDigit(c)) {
				intTemp += c;
			} else if (c == ' ') {
				if (!strTemp.isEmpty()) {
					if (!m.containsKey(strTemp)) {	
						System.out.print ("\tEnter the value of " + strTemp + ": ");
						m.put(strTemp, s.nextInt()); s.nextLine();
					}
					values.addFirst(m.get(strTemp));
					strTemp = "";
				} else if (!intTemp.isEmpty()) {
					values.addFirst(Integer.parseInt(intTemp));
					intTemp = "";
				} // if we have additional spaces and no ints or strings to match, ignore.				
			} else if (c == '*' || c == '+' || c == '-' || c == '/') {
				if (values.size() >= 2) {
					int x = values.getFirst(); values.removeFirst();
					int y = values.getFirst(); values.removeFirst();
					if (c == '*') {
						values.addFirst(x*y);
					} else if (c == '+') {
						values.addFirst(x+y);
					} else if (c == '-') {
						values.addFirst(y-x);
					} else {
						values.addFirst(y/x);
					}
				} else {
					System.out.println("Error: Two values must precede an operation.");
					break;
				}
			} else if (c == '$') {
				System.out.println("\t\tFinal value = " + values.getFirst());
				break;
			} else {
				System.out.println("Error: Invalid postfix input.");
				break;
			}
		  }  
		  // will exit without result if no '$' in expression, which is fine
		  System.out.print("Continue(y/n)? "); 
		  if (s.nextLine().charAt(0) == 'n') { continueLoop = false; }
	  }
	  s.close();
	}
}