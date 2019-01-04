/* Copyright 2016 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.engedu.anagrams;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import javax.crypto.spec.DESedeKeySpec;

public class AnagramDictionary {

    private static final int MIN_NUM_ANAGRAMS = 5;
    private static final int DEFAULT_WORD_LENGTH = 3;
    private static final int MAX_WORD_LENGTH = 7;
    private int wordLength = DEFAULT_WORD_LENGTH;
    private Random random = new Random();
    private ArrayList<String> wordList;
    private HashSet<String> wordSet;
    private HashMap<String, ArrayList<String>> lettersToWord;
    private HashMap<Integer, ArrayList<String>> sizeToWords;


    public AnagramDictionary(Reader reader) throws IOException {
        wordList = new ArrayList<String>();
        wordSet = new HashSet<String>();
        lettersToWord = new HashMap<String, ArrayList<String>>();
        sizeToWords = new HashMap<Integer, ArrayList<String>>();
        BufferedReader in = new BufferedReader(reader);
        String line;
        while((line = in.readLine()) != null) {
            String word = line.trim();
            String wordSorted = sortLetters(word);
            wordList.add(word);
            wordSet.add(word);
            if(!lettersToWord.containsKey(wordSorted)) lettersToWord.put(wordSorted, new ArrayList<String>());
            lettersToWord.get(wordSorted).add(word);
            int l = word.length();
            if(!sizeToWords.containsKey(l)) sizeToWords.put(l, new ArrayList<String>());
            sizeToWords.get(l).add(word);
        }
    }

    public boolean isGoodWord(String word, String base) {
        if(!wordSet.contains(word)) return false;
        if(word.contains(base)) return false;
        return true;
    }

    private String sortLetters(String w) {
        char[] s = w.toCharArray();
        Arrays.sort(s);
        w = String.valueOf(s);
        return w;
    }

    public List<String> getAnagrams(String targetWord) {
        ArrayList<String> result = new ArrayList<String>();
        String targetWordSorted = sortLetters(targetWord);
        for (int i = 0; i < wordList.size(); i++) {
            String w = wordList.get(i);
            String wordSorted = sortLetters(w);
            if(wordSorted.equals(targetWordSorted)) result.add(w);
        }
        return result;
    }

    public List<String> getAnagramsWithOneMoreLetter(String word) {
        ArrayList<String> result = new ArrayList<String>();
        for (char c = 'a'; c < 'z'; c++) {
            String w = sortLetters(word + c);
            if(lettersToWord.containsKey(w)) {
                ArrayList<String> t = lettersToWord.get(w);
                for (int i = 0; i < t.size(); i++) result.add(t.get(i));
            }
        }
        return result;
    }

    public String pickGoodStarterWord() {
        if(wordLength > MAX_WORD_LENGTH) wordLength = MAX_WORD_LENGTH;
        int x = random.nextInt(sizeToWords.get(wordLength).size());
        while(getAnagramsWithOneMoreLetter(sizeToWords.get(wordLength).get(x)).size() < MIN_NUM_ANAGRAMS)
            x = random.nextInt(sizeToWords.get(wordLength).size());
        return sizeToWords.get(wordLength++).get(x);
    }
}
