package com.cpsc323.assignments.assignment03;

/*
 * Name			Michael Romero, Austin Suarez, Sean Hillenbrand
 * Project No.	3, Problem 1
 * Due Date		Feb 16, 2017
 * Professor	Ray Ahmadnia
 * 
 * Purpose:		This program evaluates statements and determines whether each token is a number, 
 * 				identifier, a reserved word, or special character. 
 * i.e. "for ( int tom_jones = 22 ; tom_jones <= 100 ; tom_jones = tom + 1 ) cout<< 2tom ;"
 * 	or	"int i = 5 ; while ( i <= 10 ) cout<< myEye ;"
 */

import java.util.ArrayDeque;
import java.util.Arrays;
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
		Deque<String> reservedWords = new ArrayDeque<String>(Arrays.asList("cout<<", "for", "int", "while"));
		Deque<String> special = new ArrayDeque<String>(Arrays.asList("=","*","-",";","(",")","<=","+"));
		Deque<String> identifiers = new ArrayDeque<String>();
		Scanner s = new Scanner(System.in);
		boolean continueLoop = true;
		while(continueLoop) {			
			System.out.print("Enter a statement: ");
			String statement = s.nextLine();
			String[] tokens = statement.split("\\s");
			for (int i = 0; i < tokens.length; i++) {
				if (reservedWords.contains(tokens[i])) {
					System.out.println(tokens[i] + "\t\treserved word");
				} else if (special.contains(tokens[i])) {
					System.out.println(tokens[i] + "\t\tspecial symbol");
				} else if (isNumeric(tokens[i])) { 
					System.out.println(tokens[i] + "\t\tnumber");
				} else if (identifiers.contains(tokens[i])) {
					System.out.println(tokens[i] + "\tidentifier");
				} else { // either an identifier or not an identifier
					if (i < (tokens.length - 1)) { // handles boundary of tokens[i+1] below
						if (tokens[i+1].equals("=")) {
							System.out.println(tokens[i] + "\tidentifier");
							identifiers.addLast(tokens[i]);	
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