package com.investigator;

import java.util.ArrayList;

public class LineParser {

	//avoids reg expressions
	String timeFormat = "\\d\\d:\\d\\d:\\d\\d";
	String dateFormat = "\\d\\d-\\d\\d-\\d\\d\\d\\d";
	String[] regExps = {timeFormat, dateFormat};
	
	ArrayList<String> splitLine(String s)
	{
		String[] tokens =  s.split(" ");
		ArrayList<String> result = new ArrayList<String>();
		for (int i = 0; i < tokens.length; i++) {
			boolean useToken = true;
			
			for (int j = 0; j < regExps.length; j++) {
				if (tokens[i].matches(regExps[j]))
				{
					useToken = false;
					break;
				}
			}
			
			if (useToken)
			{
				result.add(tokens[i]);
			}
		}
		
		return result;
	}
	
	public static void main(String[] args) {
		
	}
}
