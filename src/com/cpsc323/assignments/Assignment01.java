package com.cpsc323.assignments;

/*
 * Name			Michael Romero, Austin Suarez, Sean Hillenbrand
 * Project No.	1
 * Due Date		Feb 02, 2017
 * Professor	Ray Ahmadnia
 * 
 * Purpose:		This program evaluates postfix expressions. 
 */

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Assignment01 {
	public static void main(String[] args) {
	  Scanner s = new Scanner(System.in);
	  boolean continueLoop = true;
	  while(continueLoop) {
		  Map<Character, Integer> m = new HashMap<Character, Integer>();
		  Deque<Integer> values = new ArrayDeque<Integer>();
		  System.out.print("Enter a postfix expression: ");
		  String postfix = s.nextLine();
		  for (int i = 0; i < postfix.length(); i++) {
			char c = postfix.charAt(i);
			if (Character.isAlphabetic(c)) {
				if (!m.containsKey(c)) {	
					System.out.print ("\tEnter the value of " + c + ": ");
					m.put(c, s.nextInt()); s.nextLine();
				}
				values.addFirst(m.get(c));
			} else if (c == '*' || c == '+') {
				if (values.size() >= 2) {
					int x = values.getFirst(); values.removeFirst();
					int y = values.getFirst(); values.removeFirst();
					if (c == '*') {
						values.addFirst(x*y);
					} else if (c == '+') {
						values.addFirst(x+y);
					}
				} else {
					System.out.println("Error: Two values must precede an operation.");
					break;
				}
			} else if (c == '$') {
				System.out.println("\t\tFinal value = " + values.getFirst());		  
			} else {
				System.out.println("Error: Invalid postfix input.");
			}
		  }
		  System.out.print("Continue(y/n)? "); 
		  if (s.nextLine().charAt(0) == 'n') { continueLoop = false; }
	  }
	  s.close();
	}
}