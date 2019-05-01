package com.footballstatistics.backend.service.impl;

import com.footballstatistics.backend.model.Match;
import com.footballstatistics.backend.service.MatchService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class MatchServiceImpl implements MatchService {

    private static String fileName = "C:\\Users\\Stefan\\IdeaProjects\\Test_excel.xlsx";
    private Workbook workBook = null;
    private File file;
    private FileInputStream inputStream = null;
    private String fileExtensionName;
    private Match match;

    public MatchServiceImpl() {
        file = new File(fileName);
        try {
            inputStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        fileExtensionName = fileName.substring(fileName.indexOf('.'));

        if(fileExtensionName.equals(".xlsx")) {
            try {
                workBook = new XSSFWorkbook(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            try {
                workBook = new HSSFWorkbook(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public ArrayList<Integer> getIntegerArray(ArrayList<String> stringArray) {

        if(stringArray.isEmpty()){
            throw new NullPointerException();
        }

        ArrayList<Integer> result = new ArrayList<>();
        for(String stringValue : stringArray) {
            try {
                //Convert String to Integer, and store it into integer array list.
                result.add(Integer.parseInt(stringValue));
            } catch(NumberFormatException nfe) {
                //System.out.println("Could not parse " + nfe);
                //Log.w("NumberFormat", "Parsing failed! " + stringValue + " can not be an integer");
            }
        }
        return result;
    }

    @Override
    public List<Match> getAllMatches(){
        List<Match> allMatches = new ArrayList<>();
        Sheet sheet = workBook.getSheet("Sheet1");
        ArrayList<String> list;
        for(Row row: sheet){

            match  = new Match(row.getCell(0).getStringCellValue(), row.getCell(1).getStringCellValue(), row.getCell(2).getStringCellValue(), row.getCell(3).getStringCellValue(), row.getCell(4).getStringCellValue(), row.getCell(11).getStringCellValue(), row.getCell(12).getStringCellValue());
            list = new ArrayList<>(Arrays.asList(row.getCell(5).getStringCellValue().split(" ")));
            match.setHomeTeamLastNineGoals(getIntegerArray(list));

            list = new ArrayList<>(Arrays.asList(row.getCell(6).getStringCellValue().split(" ")));
            match.setHomeTeamHomeLastNineGoals(getIntegerArray(list));

            list = new ArrayList<>(Arrays.asList(row.getCell(7).getStringCellValue().split(" ")));
            match.setAwayTeamLastNineGoals(getIntegerArray(list));

            list = new ArrayList<>(Arrays.asList(row.getCell(8).getStringCellValue().split(" ")));
            match.setAwayTeamAwayLastNineGoals(getIntegerArray(list));

            list = new ArrayList<>(Arrays.asList(row.getCell(9).getStringCellValue().split(" ")));
            match.setAgainstEachOther(getIntegerArray(list));

            list = new ArrayList<>(Arrays.asList(row.getCell(10).getStringCellValue().split(" ")));
            match.setAgainstEachOtherHomeAway(getIntegerArray(list));


            allMatches.add(match);
        }

        return allMatches;
    }


    @Override
    public Match getMatch(String homeTeam, String awayTeam){

        Sheet sheet = workBook.getSheet("Sheet1");
        ArrayList<String> list;

        if(homeTeam.equals("") || awayTeam.equals("")){
            throw new NullPointerException();
        }

        for(Row row: sheet){
            if(row.getCell(1).getStringCellValue().equals(homeTeam) && row.getCell(3).getStringCellValue().equals(awayTeam)){
                match  = new Match(row.getCell(0).getStringCellValue(), row.getCell(1).getStringCellValue(), row.getCell(2).getStringCellValue(), row.getCell(3).getStringCellValue(), row.getCell(4).getStringCellValue(), row.getCell(11).getStringCellValue(), row.getCell(12).getStringCellValue());

                list = new ArrayList<>(Arrays.asList(row.getCell(5).getStringCellValue().split(" ")));
                match.setHomeTeamLastNineGoals(getIntegerArray(list));

                list = new ArrayList<>(Arrays.asList(row.getCell(6).getStringCellValue().split(" ")));
                match.setHomeTeamHomeLastNineGoals(getIntegerArray(list));

                list = new ArrayList<>(Arrays.asList(row.getCell(7).getStringCellValue().split(" ")));
                match.setAwayTeamLastNineGoals(getIntegerArray(list));

                list = new ArrayList<>(Arrays.asList(row.getCell(8).getStringCellValue().split(" ")));
                match.setAwayTeamAwayLastNineGoals(getIntegerArray(list));

                list = new ArrayList<>(Arrays.asList(row.getCell(9).getStringCellValue().split(" ")));
                match.setAgainstEachOther(getIntegerArray(list));

                list = new ArrayList<>(Arrays.asList(row.getCell(10).getStringCellValue().split(" ")));
                match.setAgainstEachOtherHomeAway(getIntegerArray(list));
            }

        }

        return match;
    }

}
