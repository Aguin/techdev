/*
 * File: Hangman.java
 * ------------------
 * This program will eventually play the Hangman game from
 * Assignment #4.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Hangman extends ConsoleProgram {
    private HangmanCanvas canvas;
    public void init() {
        canvas = new HangmanCanvas();
        add(canvas);
    }
    public void run() {
        println("Welcome to Hangman!");
        canvas.reset();
        HangmanLexicon lex = new HangmanLexicon();
        RandomGenerator rgen = new RandomGenerator();
        int num = lex.getWordCount();
        int x = rgen.nextInt(0, num - 1);
        String w = lex.getWord(x), show = "";
        int len = w.length(), left = 8, ok = 0;
        for (int i = 0; i < len; ++i) show += '-';
        int [] vis = new int[26], has = new int[26];
        for (int i = 0; i < len; i++) has[w.charAt(i) - 'A'] = 1;
        canvas.displayWord(show);
        while (left > 0) {
            print("The word now looks like this: ");
            for (int i = 0; i < len; ++i) {
                if(vis[w.charAt(i) - 'A'] == 1) print(w.charAt(i));
                else print("-");
            }
            println();
            if(left > 1) {
                print("You have ");
                print(left);
                println(" guesses left.");
            }
            else println("You have only one guess left.");
            print("Your guess: ");
            String str = readLine();
            str = str.toUpperCase();
            if(str.length() != 1) {
                println("Illegal guess, try again.");
                continue;
            }
            char c = str.charAt(0);
            if(c < 'A' || c > 'Z') {
                println("Illegal guess, try again.");
                continue;
            }
            if(vis[c - 'A'] == 1) {
                println("Already guessed, try again.");
                continue;
            }
            vis[c - 'A'] = 1;
            if(has[c - 'A'] == 1) {
                println("That guess is correct.");
                show = "";
                for(int i = 0; i < len; ++i)
                    if(vis[w.charAt(i) - 'A'] == 1) show += w.charAt(i);
                    else show += "-";
                canvas.displayWord(show);
            }
            else {
                print("There are no ");
                print(c);
                println("'s in the word.");
                left--;
                canvas.noteIncorrectGuess(c);
            }
            ok = 1;
            for (int i = 0; i < len; i++)
                if(vis[w.charAt(i) - 'A'] == 0) ok = 0;
            if(ok == 1) break;
        }
        if(ok == 1) {
            print("You guessed the word: ");
            println(w);
            println("You win.");
        }
        else {
            println("You're completely hung.");
            print("The word was: ");
            println(w);
            println("You lose.");
        }
    }
}