package com.cpsc323.assignments;
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
		  Deque<Character> postfixStack = new ArrayDeque<Character>();
		  Map<Character, Integer> m = new HashMap<Character, Integer>();
		  Deque<Integer> values = new ArrayDeque<Integer>();

		  System.out.print("Enter a postfix expression: ");
		  String postfix = s.nextLine();
		  for (int i = 0; i < postfix.length(); i++) {
			char c = postfix.charAt(i);
			if (Character.isAlphabetic(c)) {
				if (!postfixStack.contains(c)) {
					System.out.print ("\tEnter the value of " + c + ": ");
					m.put(c, s.nextInt()); s.nextLine();
				}
				values.addFirst(m.get(c));
			} else if (c == '*' || c == '+') {
				int x = values.getFirst(); values.removeFirst();
				int y = values.getFirst(); values.removeFirst();
				if (c == '*') {
					values.addFirst(x*y);
				} else if (c == '+') {
					values.addFirst(x+y);
				}
			} else if (c == '$') {
				System.out.println("\t\tFinal value = " + values.getFirst());		  
			} else {
				System.out.println("Your postfix string is bad.");
			}
			postfixStack.addFirst(c);
		  }
		  
		  System.out.print("Continue(y/n)? ");
		  
		  if (s.nextLine().charAt(0) == 'n') { continueLoop = false; }
	  }
	  s.close();
	}
}