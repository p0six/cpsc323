package com.cpsc323.assignments.assignment09;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

/*
 * Name			Michael Romero, Austin Suarez, Sean Hillenbrand
 * Project No.	9 
 * Due Date		Apr 13, 2017
 * Professor	Ray Ahmadnia
 * 
 * Purpose:		LR Parser 
 */

public class assignment09 {
	static int table[][] = { //LR Parsing Table
            // i	+	  -	   *	/	(	 )	  $	   E	T    F
            { 105, 501, 502, 503, 504, 104, 506, 507,   1,   2,	  3 },
            { 500, 106, 107, 503, 504, 505, 506, 400, 507, 509, 510 },
            { 500, 203, 203, 108, 109, 505, 203, 203, 507, 509, 510 },
            { 500, 206, 206, 206, 206, 505, 206, 206, 507, 509, 510 },
            { 105, 501, 502, 503, 504, 104, 506, 507,  10,   2,   3 },
            { 500, 208, 208, 208, 208, 505, 208, 208, 507, 509, 510 },
            { 105, 501, 502, 503, 504, 104, 506, 507, 507,  11,   3 },
            { 105, 501, 502, 503, 504, 104, 506, 507, 507,  12,   3 },
            { 105, 501, 502, 503, 504, 104, 506, 507, 507, 509,  13 },
            { 105, 501, 502, 503, 504, 104, 506, 507, 507, 509,  14 },
            { 500, 106, 107, 503, 504, 505, 115, 507, 507, 509, 510 },
            { 500, 201, 201, 108, 109, 505, 201, 201, 507, 509, 510 },
            { 500, 202, 202, 108, 109, 505, 202, 202, 507, 509, 510 },
            { 500, 204, 204, 204, 204, 505, 204, 204, 507, 509, 510 },
            { 500, 205, 205, 205, 205, 505, 205, 205, 507, 509, 510 },
            { 500, 207, 207, 207, 207, 505, 207, 207, 507, 509, 510 }};

	static String rules[][] = { //LR Parsing Rules
			{"E", "E+T"},
            {"E", "E-T"},
            {"E", "T"},
            {"T", "T*F"},
            {"T", "T/F"},
            {"T", "F"},
            {"F", "(E)"},
            {"F", "i"}};
	
	static Character characterIndex[] = {'i','+','-','*','/','(',')','$','E','T','F'};
		
	public static void printAsStack(ArrayDeque<String> d) { // makes output easier to read
		Iterator<String> it = d.descendingIterator();
		System.out.print("stack = [ ");
		while (it.hasNext()) {
			System.out.print("\"" + it.next() + "\"");
			if (it.hasNext()) {
				System.out.print(", ");
			} 
		}
		System.out.println(" ]");
	}
	
	public static void main(String[] args) {
		HashMap<Character,Integer> myMap = new HashMap<Character,Integer>();
		for (int i = 0; i < characterIndex.length; i++) {
			myMap.put(characterIndex[i], i);
		}
		
		ArrayDeque<String> myStack = new ArrayDeque<String>();
		Scanner s = new Scanner(System.in);
		System.out.print("Enter a valid string: ");
		String st = s.nextLine();
		Integer index = 0, tableVal, tableMod = 1;
		Character currentCharacter;
		String topStack;
		printAsStack(myStack);
		myStack.push("0"); printAsStack(myStack);
		boolean reduced = false;
		while (index < st.length()) {
			topStack = myStack.pop(); printAsStack(myStack);
			currentCharacter = st.charAt(index);
			if (!myMap.containsKey(currentCharacter)) {
				System.out.println("Rejected - Character " + currentCharacter + " does not exist in grammar");
				break;
			} else {
				if (reduced) {  // after a reduce operation, goto case has special tableVal
					tableVal = table[Integer.parseInt(topStack)][myMap.get(rules[tableMod - 1][0].charAt(0))];
					reduced = false;
				} else { 
					tableVal = table[Integer.parseInt(topStack)][myMap.get(currentCharacter)];
				}
				
				if (tableVal == 400) {	// accepted
					System.out.println("accepted");
					break;
				} else if (tableVal >= 500) { // rejected
					System.out.println("Rejected: unexpected character " + characterIndex[tableVal % 500]);
					break;
				} else if (tableVal >= 200 && tableVal < 300) { // reduce
					tableMod = (tableVal % 200);
					System.out.println("reduce: " + tableMod);
					myStack.push(topStack); printAsStack(myStack);
					for (int i = 0; i < (2*rules[tableMod - 1][1].length()); i++ ) {
						myStack.pop(); printAsStack(myStack);
					}
					reduced = true;
				} else if (tableVal >= 100 && tableVal < 200) { // switch
					tableMod = (tableVal % 100);
					System.out.println("switch: " + tableMod);
					myStack.push(topStack); printAsStack(myStack);
					myStack.push(currentCharacter.toString()); printAsStack(myStack);
					myStack.push(tableMod.toString()); printAsStack(myStack);
					index++;
				} else { // goto
					System.out.println("goto: " + tableVal);
					myStack.push(topStack); printAsStack(myStack);
					myStack.push(rules[tableMod - 1][0]); printAsStack(myStack);
					myStack.push(tableVal.toString()); printAsStack(myStack);
				}
			}
		}
		s.close();	
	}
}