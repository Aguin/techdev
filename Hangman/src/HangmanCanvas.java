/*
 * File: HangmanCanvas.java
 * ------------------------
 * This file keeps track of the Hangman display.
 */

import acm.graphics.*;
import com.sun.deploy.util.UpdateCheck;

public class HangmanCanvas extends GCanvas {
	/** Resets the display so that only the scaffold appears */
	public void reset() {
		guess = null;
		incorrect = "";
		incorrectlabel = null;
		removeAll();
		add(SCAFFOLD);
		add(BEAM);
		add(ROPE);
	}

/**
 * Updates the word on the screen to correspond to the current
 * state of the game.  The argument string shows what letters have
 * been guessed so far; unguessed letters are indicated by hyphens.
 */
	GLabel guess = null;
	public void displayWord(String word) {
		if(guess != null) remove(guess);
		guess = new GLabel(word, 50, 420);
		guess.setFont("Calibri-30");
		add(guess);
	}

/**
 * Updates the display to correspond to an incorrect guess by the
 * user.  Calling this method causes the next body part to appear
 * on the scaffold and adds the letter to the list of incorrect
 * guesses that appears at the bottom of the window.
 */
	String incorrect = "";
	GLabel incorrectlabel = null;
	public void noteIncorrectGuess(char letter) {
		int len = incorrect.length();
		for (int i = 0; i < len; i++)
			if(incorrect.charAt(i) == letter) return;
		if(incorrectlabel != null) remove(incorrectlabel);
		incorrect += letter;
		incorrectlabel = new GLabel(incorrect, 50, 450);
		incorrectlabel.setFont("Calibri-18");
		add(incorrectlabel);
		if(len == 0) add(HEAD);
		else if(len == 1) add(BODY);
		else if(len == 2) {add(LEFT_UPPER_ARM); add(LEFT_LOWER_ARM);}
		else if(len == 3) {add(RIGHT_UPPER_ARM); add(RIGHT_LOWER_ARM);}
		else if(len == 4) {add(LEFT_HIP); add(LEFT_LEG);}
		else if(len == 5) {add(RIGHT_HIP); add(RIGHT_LEG);}
		else if(len == 6) add(LEFT_FOOT);
		else add(RIGHT_FOOT);
	}

/* Constants for the simple version of the picture (in pixels) */
	private static final int SCAFFOLD_HEIGHT = 360;
	private static final int BEAM_LENGTH = 144;
	private static final int ROPE_LENGTH = 18;
	private static final int HEAD_RADIUS = 36;
	private static final int BODY_LENGTH = 144;
	private static final int ARM_OFFSET_FROM_HEAD = 28;
	private static final int UPPER_ARM_LENGTH = 72;
	private static final int LOWER_ARM_LENGTH = 44;
	private static final int HIP_WIDTH = 36;
	private static final int LEG_LENGTH = 108;
	private static final int FOOT_LENGTH = 28;
	int x0 = 70, y0 = 370;
	int x1 = x0, y1 = y0 - SCAFFOLD_HEIGHT;
	GLine SCAFFOLD = new GLine(x0, y0, x1, y1);
	int x2 = x0 + BEAM_LENGTH, y2 = y1;
	GLine BEAM = new GLine(x1, y1, x2, y2);
	int x3 = x2, y3 = y2 + ROPE_LENGTH;
	GLine ROPE = new GLine(x2, y2, x3, y3);
	GOval HEAD = new GOval(x3 - HEAD_RADIUS, y3, 2 * HEAD_RADIUS, 2 * HEAD_RADIUS);
	int x4 = x3, y4 = y3 + 2 * HEAD_RADIUS + BODY_LENGTH;
	GLine BODY = new GLine(x3, y3 + 2 * HEAD_RADIUS, x4, y4);
	int x5 = x3, y5 = y3 + 2 * HEAD_RADIUS + ARM_OFFSET_FROM_HEAD;
	int x6 = x4 - UPPER_ARM_LENGTH, y6 = y5;
	GLine LEFT_UPPER_ARM = new GLine(x5, y5, x6, y6);
	int x7 = x4 + UPPER_ARM_LENGTH, y7 = y5;
	GLine RIGHT_UPPER_ARM = new GLine(x5, y5, x7, y7);
	int x8 = x6, y8 = y6 + LOWER_ARM_LENGTH;
	GLine LEFT_LOWER_ARM = new GLine(x6, y6, x8, y8);
	int x9 = x7, y9 = y7 + LOWER_ARM_LENGTH;
	GLine RIGHT_LOWER_ARM = new GLine(x7, y7, x9, y9);
	int x10 = x4 - HIP_WIDTH, y10 = y4;
	GLine LEFT_HIP = new GLine(x4, y4, x10, y10);
	int x11 = x4 + HIP_WIDTH, y11 = y4;
	GLine RIGHT_HIP = new GLine(x4, y4, x11, y11);
	int x12 = x10, y12 = y10 + LEG_LENGTH;
	GLine LEFT_LEG = new GLine(x10, y10, x12, y12);
	int x13 = x11, y13 = y11 + LEG_LENGTH;
	GLine RIGHT_LEG = new GLine(x11, y11, x13, y13);
	int x14 = x12 - FOOT_LENGTH, y14 = y12;
	GLine LEFT_FOOT = new GLine(x12, y12, x14, y14);
	int x15 = x13 + FOOT_LENGTH, y15 = y13;
	GLine RIGHT_FOOT = new GLine(x13, y13, x15, y15);
}
