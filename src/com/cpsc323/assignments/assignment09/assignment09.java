package com.cpsc323.assignments.assignment09;
import java.util.ArrayDeque;
import java.util.Scanner;

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
    
	public static Integer getIndex(Character x) {
		int index = -1;
		switch (x) {
		case 'i':
			index = 0;
			break;
		case '+':
			index = 1;
			break;
		case '-':
			index = 2;
			break;
		case '*':
			index = 3;
			break;
		case '/':
			index = 4;
			break;
		case '(':
			index = 5;
			break;
		case ')':
			index = 6;
			break;
		case '$':
			index = 7;
			break;
		case 'E':
			index = 8;
			break;
		case 'T':
			index = 9;
			break;
		case 'F':
			index = 10;
			break;
		default:
			break;	
		}
		return index;
	}
	
	public static void main(String[] args) {
		ArrayDeque<String> myStack = new ArrayDeque<String>();
		Scanner s = new Scanner(System.in);
		System.out.print("Enter a valid string: ");
		String st = s.nextLine();
		Integer index = 0, tableVal, tableMod = 0;
		Character currentCharacter;
		String topStack;
		System.out.println("stack = " + myStack.toString());
		myStack.push("0"); System.out.println("stack = " + myStack.toString());
		while (index < st.length()) {
			topStack = myStack.pop(); System.out.println("stack = " + myStack.toString());
			currentCharacter = st.charAt(index);
			if (getIndex(currentCharacter) == -1) {
				System.out.println("Rejected - Character " + currentCharacter + " does not exist in grammar");
				break;
			} else {
				tableVal = table[Integer.parseInt(topStack)][getIndex(currentCharacter)];
				if (tableVal % 500 >= 0 && tableVal % 500 < 100) { // some type of failure..
					System.out.println("Rejected");
					break;
				} else if (tableVal == 400) {	
					System.out.println("accepted");
					break;
				} else if (tableVal % 200 >= 0 && tableVal % 200 < 100) { // reduce
					tableMod = (tableVal % 200);
					myStack.push(topStack); System.out.println("stack = " + myStack.toString());
					for (int i = 0; i < (2*rules[tableMod - 1][1].length()); i++ ) {
						myStack.pop(); System.out.println("stack = " + myStack.toString());
					}
					topStack = myStack.pop(); System.out.println("stack = " + myStack.toString()); // redundant
					tableVal = table[Integer.parseInt(topStack)][getIndex(rules[tableMod - 1][0].charAt(0))]; // unique
					System.out.println("goto: " + tableVal); // redundant
					myStack.push(topStack); System.out.println("stack = " + myStack.toString());
					myStack.push(rules[tableMod - 1][0]); System.out.println("stack = " + myStack.toString());
					tableMod = (tableVal % 100);
					myStack.push(tableMod.toString()); System.out.println("stack = " + myStack.toString());
				} else if (tableVal % 100 >= 0 && tableVal % 100 < 100) { // switch
					tableMod = (tableVal % 100);
					myStack.push(topStack); System.out.println("stack = " + myStack.toString());
					myStack.push(currentCharacter.toString()); System.out.println("stack = " + myStack.toString());
					myStack.push(tableMod.toString()); System.out.println("stack = " + myStack.toString());
					index++;
				} else { // goto
					System.out.println("never arrive");
				}
			}
		}
		s.close();	
	}
}