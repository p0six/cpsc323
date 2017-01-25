package com.cpsc323.lectures;

public class Lecture01 {

	public static void main(String[] args) {
		
		/*
		 CPSC 323 Day one 

		Definitions:
		an alphabet is a finite set of symbols which is denoted by greek letter SIGMA
		ex: Binary alphabet: SIGMA = {0, 1}
		English alphabet : SIGMA = {‘A’…’Z’,’a’…’z’};
		
		Definition given SIGMA, any string of symbols over SIGMA is called a word
		ex: if SIGMA = {alb}
		w1 = aba
		w2 = baab
		…
		w3 = a
		
		Definition, given SIGMA and word w, the length of w or |w| is the number of symbols used to form w
		ex: SIGMA = {a, b}
			w = abb, |w| = 3
		ex: SIGMA = {a, bb} // bb = one symbol
			w = abb
			|w| = 2
			w = bbbb
			|w| = 2
		
		def - the word of length zero is called the null word and is denoted by LAMBDA (or epsilon) but we will use LAMBDA, therefore, |lambda| = 0
		
		def: given SIGMA and w
		w^0 = lambda
		w^2 = ww
		ex: w = ab
		w^2 = (ab)^2 = abab != a^2b^2 // not the same as algebra - concatenation
		
		
		factoring:
		ab + ac = a(b +c) // 
		ba + ca = (b+c)a
		ba + a = (b+LAMBDA)a // factoring matters by side… lambda replaces ONE
		
		def. given SIGMA - the set of all words over SIGMA including LAMBDA is denoted by SIGMA^*
		ex: SIGMA = {alb}
		SIGMA^* = { LAMBDA, a, b, aa, bb, ab, ba, aaa, cab, aba, aba, baa, bob, bbs, bbb, …  }
		                    ^ length 0    ^ length 1  ^ length 2  ^ length 3
		
		Def: given SIGMA^*, any subset of SIGMA^* is called a language

		 */
		
		
	}
}