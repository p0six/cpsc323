package com.cpsc323.assignments.assignment03;

/*
 * Name			Michael Romero, Austin Suarez, Sean Hillenbrand
 * Project No.	3, Problem 1
 * Due Date		Feb 16, 2017
 * Professor	Ray Ahmadnia
 * 
 * Purpose:		This program evaluates statements and determines whether each token is a number, 
 * 				identifier, a reserved word, or special character. 
 */

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class statementParser {
	
	public static boolean isNumeric(String s) {
		for (char x : s.toCharArray()) {
			if (!Character.isDigit(x)) {
				return false;
			}
		}
		return true;
	}
	
	public static void main(String[] args) {		
		String[] reservedWords = {"cout<<", "for", "int", "while" };
		Deque<String> dreservedWords = new ArrayDeque<String>();
		for (String word : reservedWords) {
			dreservedWords.add(word);
		}
		
		String[] special = {"=","*","-",";","(",")","<=","+"};
		Deque<String> dspecial = new ArrayDeque<String>();
		for (String symbol : special) {
			dspecial.add(symbol);
		}
				
		Scanner s = new Scanner(System.in);
		boolean continueLoop = true;
		while(continueLoop) {			
			System.out.print("Enter a statement: ");
			String statement = s.nextLine();
			String[] tokens = statement.split("\\s");
			for (int i = 0; i < tokens.length; i++) {
				if (dreservedWords.contains(tokens[i])) {
					System.out.println(tokens[i] + "\t\treserved word");
				} else if (dspecial.contains(tokens[i])) {
					System.out.println(tokens[i] + "\t\tspecial symbol");
				} else if (isNumeric(tokens[i])) { // check if something isNumeric
					System.out.println(tokens[i] + "\t\tnumber");
				} else { // either an identifier or not an identifier
					if (i < tokens.length) {
						if (tokens[i+1].equals("=")) {
							System.out.println(tokens[i] + "\tidentifier");
						} else {
							System.out.println(tokens[i] + "\tnot identifier");
						}
					} else {
						System.out.println(tokens[i] + "\tnot identifier");
					}
				}
			}
			
			System.out.print("Continue(y/n)? "); 
			if (s.nextLine().charAt(0) == 'n') { continueLoop = false; }
		}
		s.close();
	}
}