package com.investigator;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import com.investigator.InvestigatorMain.MatchResult;

public class InvestigatorTest {

	InvestigatorMain investigatorMain = new InvestigatorMain();
	
	@Test
	public void testReadFile() {
		ArrayList<String> inputLines = investigatorMain.readInputFile("investigator.data");
		
		assertEquals(6, inputLines.size());
	}

	@Test
	public void testScanFirstLine() {
		ArrayList<String> inputLines = investigatorMain.readInputFile("investigator.data");
		MatchResult res = investigatorMain.scanFirstLineForMatches(inputLines);
		
		assertEquals(3, res.matchingLines.size());
	}

	
	@Test
	public void testInvestigatorMain() {
		String[] args = {"investigator.data"};
		InvestigatorMain.main(args);
	}


}
