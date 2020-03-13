package com.iplanalyser;

import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class IplAnalyserTest {
    private static IplAnalyser iplAnalyser;
    private static final String IPL_MOST_RUNS_FILE_PATH = "./src/test/resources/IPL2019FactsheetMostRuns.csv";

    @BeforeClass
    public static void setUp() throws Exception {
        iplAnalyser = new IplAnalyser();
    }

    @Test
    public void givenIplMostRunData_WhenSortedWithBattingAvg_ShouldReturnPlayerName() {
        try {
            iplAnalyser.loadIplData(IPL_MOST_RUNS_FILE_PATH);
            String sortedCricketData = iplAnalyser.getSortedCricketData(SortedField.AVERAGE);
            IplRunsCSV[] mostRunCsv = new Gson().fromJson(sortedCricketData, IplRunsCSV[].class);
            Assert.assertEquals("MS Dhoni", mostRunCsv[0].playerName);
        }catch (IplAnalyserException e){}
    }

    @Test
    public void givenIplMostRunData_WhenSortedWithStrikeRate_ShouldReturnPlayerName() {
        try {
            iplAnalyser.loadIplData(IPL_MOST_RUNS_FILE_PATH);
            String sortedCricketData = iplAnalyser.getSortedCricketData(SortedField.STRIKE_RATE);
            IplRunsCSV[] mostRunCsv = new Gson().fromJson(sortedCricketData, IplRunsCSV[].class);
            Assert.assertEquals("Ishant Sharma", mostRunCsv[0].playerName);
        }catch (IplAnalyserException e){}
    }
}
