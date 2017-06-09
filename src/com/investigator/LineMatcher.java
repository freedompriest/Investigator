package com.investigator;

import java.util.ArrayList;

public class LineMatcher {

	//avoids reg expressions in match
	String timeFormat = "\\d\\d:\\d\\d:\\d\\d";
	String dateFormat = "\\d\\d-\\d\\d-\\d\\d\\d\\d";
	String[] regExps = {timeFormat, dateFormat};

	
	LineParser lineParser = new LineParser();
	
	/**
	 * Checks if line have common phrase
	 * 
	 * @param s1, s2 two lines
	 * @return Matching if line match, return phrase 
	 *  	   null if lines do not match (more that 1 difference)
	 */
	Matching areLinesMatching(String s1, String s2)
	{
		ArrayList<String> splitLine1 = lineParser.splitLine(s1);
		ArrayList<String> splitLine2 = lineParser.splitLine(s2);
		int diffIdx = -1;
		int diffCount = 0;
		
		for (int i = 0; i < splitLine1.size(); i++) 
		{
			if (!splitLine1.get(i).equals(splitLine2.get(i)))
			{
				//found difference, save index
				diffIdx = i;
				diffCount++;
			}
		}
		
		if (diffCount == 1)
		{
			StringBuilder sb = new StringBuilder();
			
			//return phrase
			for (int i = 0; i < splitLine1.size(); i++) 
			{
				if (i != diffIdx)
				{
					sb.append(splitLine1.get(i));
					sb.append(" ");
				}
			}
			sb.deleteCharAt(sb.length()-1);
			
			return new Matching(s1, s2, splitLine1.get(diffIdx), splitLine2.get(diffIdx));
			
		}
		else
			return null;
	}
	
	
	
	public class Matching
	{
		public String line1;
		public String line2;

		public String word1;
		public String word2;
		
		public Matching(String line1, String line2, String word1, String word2) {
			this.line1 = line1;
			this.line2 = line2;
			this.word1 = word1;
			this.word2 = word2;
		}
		
		@Override
		public boolean equals(Object obj) {
			try
			{
				Matching objm = (Matching)obj;
				return line1.equals(objm.line1) && line2.equals(objm.line2) && word1.equals(objm.word1) && word2.equals(objm.word2);			
			} 
			catch (ClassCastException e) {
				return false;
			}
		}
	}
}
