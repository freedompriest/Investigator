package com.investigator;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import com.investigator.LineMatcher.Matching;

public class LineTest {

	LineParser lineParser = new LineParser();
	LineMatcher lineMatcher = new LineMatcher();
	
	@Test
	public void testLineParser() {
		String line = "01-01-2012 19:45:00 Naomi is getting into the car";
		ArrayList<String> lineParsed = lineParser.splitLine(line);
		
		assertEquals(6, lineParsed.size());
	}

	
	@Test
	public void testLineMatcher() {
		String line1 = "01-01-2012 19:45:00 Naomi is getting into the car";
		String line2 = "01-01-2012 20:12:39 Naomi is eating at a restaurant";
		Matching areLinesMatching1 = lineMatcher.areLinesMatching(line1, line2);
		
		assertNull(areLinesMatching1);

	
		String line3 = "02-01-2012 09:13:15 George is getting into the car";
		Matching areLinesMatching2 = lineMatcher.areLinesMatching(line1, line3);
		Matching match = lineMatcher.new Matching(line1, line3, "Naomi", "George");
		
		
		assertEquals(match, areLinesMatching2);
}

}
