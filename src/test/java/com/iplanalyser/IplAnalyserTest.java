package com.iplanalyser;

import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class IplAnalyserTest {
    private static IplAnalyser iplAnalyser;
    private static final String IPL_MOST_RUNS_FILE_PATH = "./src/test/resources/IPL2019FactsheetMostRuns.csv";

    @BeforeClass
    public static void setUp() throws RuntimeException {
        iplAnalyser = new IplAnalyser();
    }

    @Test
    public void givenIplMostRunData_WhenSortedWithBattingAvg_ShouldReturnPlayerName() {
        iplAnalyser.loadIplData(IPL_MOST_RUNS_FILE_PATH);
        String sortedCricketData = iplAnalyser.getSortedCricketData(SortedField.AVERAGE);
        IplRunsCSV[] mostRunCsv = new Gson().fromJson(sortedCricketData, IplRunsCSV[].class);
        Assert.assertEquals("MS Dhoni", mostRunCsv[0].playerName);
    }

    @Test
    public void givenIplMostRunData_WhenSortedWithStrikeRate_ShouldReturnPlayerName() {
        iplAnalyser.loadIplData(IPL_MOST_RUNS_FILE_PATH);
        String sortedCricketData = iplAnalyser.getSortedCricketData(SortedField.STRIKE_RATE);
        IplRunsCSV[] mostRunCsv = new Gson().fromJson(sortedCricketData, IplRunsCSV[].class);
        Assert.assertEquals("Ishant Sharma", mostRunCsv[0].playerName);
    }

    @Test
    public void givenIplMostRunData_WhenSortedWithMaximumFoursAndSixes_ShouldReturnPlayerName() {
        iplAnalyser.loadIplData(IPL_MOST_RUNS_FILE_PATH);
        String sortedCricketData = iplAnalyser.getSortedCricketData(SortedField.MAXIMUM_FOURS_AND_SIXES);
        IplRunsCSV[] mostRunCsv = new Gson().fromJson(sortedCricketData, IplRunsCSV[].class);
        Assert.assertEquals("Andre Russell", mostRunCsv[0].playerName);
    }

    @Test
    public void givenIplMostRunData_WhenSortedWithFoursSixesAndStrikeRate_ShouldReturnPlayerName() {
        iplAnalyser.loadIplData(IPL_MOST_RUNS_FILE_PATH);
        String sortedCricketData = iplAnalyser.getSortedCricketData(SortedField.FOURS_AND_SIXES_WITH_STRIKE_RATE);
        IplRunsCSV[] mostRunCsv = new Gson().fromJson(sortedCricketData, IplRunsCSV[].class);
        Assert.assertEquals("Andre Russell", mostRunCsv[0].playerName);
        }

    @Test
    public void givenIplMostRunData_WhenSortedWithAverageAndStrikeRate_ShouldReturnPlayerName() {
        iplAnalyser.loadIplData(IPL_MOST_RUNS_FILE_PATH);
        String sortedCricketData = iplAnalyser.getSortedCricketData(SortedField.AVERAGE_WITH_STRIKE_RATE);
        IplRunsCSV[] mostRunCsv = new Gson().fromJson(sortedCricketData, IplRunsCSV[].class);
        Assert.assertEquals("MS Dhoni", mostRunCsv[0].playerName);
    }

    @Test
    public void givenIplMostRunData_WhenSortedWithMaximumRunsAndAverage_ShouldReturnPlayerName() {
        iplAnalyser.loadIplData(IPL_MOST_RUNS_FILE_PATH);
        String sortedCricketData = iplAnalyser.getSortedCricketData(SortedField.MAXIMUM_RUNS_WITH_AVERAGE);
        IplRunsCSV[] mostRunCsv = new Gson().fromJson(sortedCricketData, IplRunsCSV[].class);
        Assert.assertEquals("David Warner", mostRunCsv[0].playerName);
    }
}
