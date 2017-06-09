package com.investigator;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.investigator.LineMatcher.Matching;

public class InvestigatorMain {

	LineMatcher lineMatcher = new LineMatcher();
	ArrayList<String> output = new ArrayList<String>();
	
	
	public ArrayList<String> readInputFile(String fileName) 
	{
		BufferedReader buf = null;
		ArrayList<String> lines = new ArrayList<String>();
		
		try
		{
			InputStream is = new FileInputStream(fileName); 
			buf = new BufferedReader(new InputStreamReader(is)); 
			String line = buf.readLine(); 
			
			while(line != null)
			{ 
				lines.add(line); 
				line = buf.readLine(); 
			} 

			return lines;
		}
		catch (IOException ioe)
		{
			ioe.printStackTrace();
			return null;
		}
		finally {
			if (buf != null)
			{
				try
				{
					buf.close();
				}
				catch (IOException ioe)
				{
					ioe.printStackTrace();
					return null;
				}
			}
		}
	}
	
	/**
	 * get first line, search for matches
	 */
	public MatchResult scanFirstLineForMatches(ArrayList<String> inputLines) {
		
			// get first line
			String mainLine = inputLines.get(0);
			Set<Integer> idxMatches = new HashSet<Integer>();  
			idxMatches.add(0);
			Set<String> matchingLines = new HashSet<>(); //avoid duplicates
			Set<String> matchingWords = new HashSet<>(); //avoid duplicates

			
			for (int j = 1; j < inputLines.size(); j++) {
				Matching match = lineMatcher.areLinesMatching(mainLine, inputLines.get(j));
				if (match != null)
				{
					idxMatches.add(j);
					matchingLines.add(match.line1);
					matchingLines.add(match.line2);
					matchingWords.add(match.word1);
					matchingWords.add(match.word2);
				}
			}
			
			return new MatchResult(idxMatches, matchingLines, matchingWords);
	}
	
	

	private void appendResultToOutput(MatchResult matchResult) {
		if (matchResult.matchingLines.size() < 2)
		{
			return;
		}
		
		for (String line : matchResult.matchingLines) 
		{
			output.add(line);
		}
		
		StringBuilder words = new StringBuilder();
		words.append("The changing word was: ");
		for (String word : matchResult.matchingWords) 
		{
			words.append(word).append(",");
		}
		words.deleteCharAt(words.length()-1).append("\n");
		output.add(words.toString());
	}

	private ArrayList<String> getOutput() {
		return output;
	}


	
	/**
	 * 
	 * @param args input file name
	 */
	public static void main(String[] args) {
		InvestigatorMain investigatorMain = new InvestigatorMain();
		
		// read file
		ArrayList<String> inputLines = investigatorMain.readInputFile(args[0]);
		
		// scan first line for matching, add result
		// iteratively remove matching lines from array until array is empty
		while (!inputLines.isEmpty())
		{
			MatchResult matchResult = investigatorMain.scanFirstLineForMatches(inputLines);
			investigatorMain.appendResultToOutput(matchResult);
			
			//remove used indices
			List<Integer> idxArr = new ArrayList<Integer>();
			idxArr.addAll(matchResult.idxMatches);	
			
			for (int i = idxArr.size() -1; i >= 0; i--) {
				inputLines.remove((int)idxArr.get(i));
			}		
		} 
			
		//print result
		ArrayList<String> output = investigatorMain.getOutput();
		for (String outputLine : output) {
			System.out.println(outputLine);
			
		}
	}

	public class MatchResult
	{
		Set<Integer> idxMatches = new HashSet<Integer>();  
		Set<String> matchingLines = new HashSet<>();
		Set<String> matchingWords = new HashSet<>();
		
		public MatchResult(Set<Integer> idxMatches, Set<String> matchingLines, Set<String> matchingWords) {
			this.idxMatches = idxMatches;
			this.matchingLines = matchingLines;
			this.matchingWords = matchingWords;
		} 
		
		
		
	}


}
