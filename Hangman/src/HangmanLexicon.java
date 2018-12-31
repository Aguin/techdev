/*
 * File: HangmanLexicon.java
 * -------------------------
 * This file contains a stub implementation of the HangmanLexicon
 * class that you will reimplement for Part III of the assignment.
 */

import acm.util.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class HangmanLexicon {
	ArrayList list = new ArrayList();
	public HangmanLexicon() {
		try {
			BufferedReader bf = new BufferedReader(new FileReader("D:\\techdev\\Hangman\\src\\HangmanLexicon.txt"));
			String b = null;
			while ((b = bf.readLine()) != null) list.add(b);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

/** Returns the number of words in the lexicon. */
	public int getWordCount() {
		return list.size();
	}

/** Returns the word at the specified index. */
	public String getWord(int index) {
		if(index < list.size()) return list.get(index).toString();
		throw new ErrorException("getWord: Illegal index");
	}
}