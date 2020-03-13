package com.iplanalyser;

import com.google.gson.Gson;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class IplAnalyser {
    List<IplRunsCSV> iplCSVList;
    Map<SortedField,Comparator<IplRunsCSV>> sortedMap;

    public IplAnalyser() {
        this.iplCSVList = new ArrayList<>();
        this.sortedMap = new HashMap<>();

        this.sortedMap.put(SortedField.AVERAGE,Comparator.comparing(iplStat -> iplStat.battingAvg));
        this.sortedMap.put(SortedField.STRIKE_RATE,Comparator.comparing(iplStat -> iplStat.strikeRate));
        this.sortedMap.put(SortedField.MAXIMUM_FOURS_AND_SIXES,Comparator.comparing(iplstat -> iplstat.fours + iplstat.sixes));
        Comparator<IplRunsCSV> foursAndSixesComparator = Comparator.comparing(iplstat -> iplstat.fours + iplstat.sixes);
        this.sortedMap.put(SortedField.FOURS_AND_SIXES_WITH_STRIKE_RATE,foursAndSixesComparator.thenComparing(iplstat -> iplstat.strikeRate ));
    }

    public int loadIplData(String csvFilePath) {
        try(Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));){
            CsvToBeanBuilder<IplRunsCSV> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
            csvToBeanBuilder.withType(IplRunsCSV.class);
            csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
            CsvToBean<IplRunsCSV> csvToBean = csvToBeanBuilder.build();
            Iterator<IplRunsCSV> iplRunsCSVIterator = csvToBean.iterator();
            int numOfEateries=0;
            while (iplRunsCSVIterator.hasNext()) {
                numOfEateries++;
                IplRunsCSV runsData = iplRunsCSVIterator.next();
                iplCSVList.add(runsData);
            }
            return numOfEateries;
        }catch (IOException e){
            throw  new IplAnalyserException(e.getMessage(),IplAnalyserException.ExceptionType.CRICKET_FILE_PROBLEM);
        }
    }

    public String getSortedCricketData(SortedField sortedField) {

        if(iplCSVList == null || iplCSVList.size() == 0){
            throw new IplAnalyserException("No Data",IplAnalyserException.ExceptionType.CRICKET_DATA_NOT_FOUND);
        }
        this.sort(this.sortedMap.get(sortedField));
        Collections.reverse(iplCSVList);
        String sortedStateCensus=new Gson().toJson(iplCSVList);
        return sortedStateCensus;
    }

    private void sort(Comparator<IplRunsCSV> csvComparator) {
        for(int i=0;i<iplCSVList.size()-1;i++){
            for(int j=0;j<iplCSVList.size()-i-1;j++){
                IplRunsCSV run1 = iplCSVList.get(j);
                IplRunsCSV run2 = iplCSVList.get(j+1);
                if(csvComparator.compare(run1,run2)>0){
                    iplCSVList.set(j,run2);
                    iplCSVList.set(j+1,run1);
                }
            }
        }
    }
}

