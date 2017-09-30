package com.ru.Main;

import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class HottestTemperature {

	public static void main(String[] args) {
		HottestTemperature ob = new HottestTemperature();
		ob.testHottestTemperatureInDay();
		ob.testHottestTemperatureInManyDays();
	}
	private void testHottestTemperatureInManyDays() {
		CSVRecord largest = hottestTemperatureInManyDays();
		System.out.println("Hottest temperature was " + largest.get("TemperatureF") + " at " + largest.get("DateUTC"));
		
	}
	private CSVRecord hottestTemperatureInManyDays() {
		CSVRecord largestSoFar = null;
		DirectoryResource dr = new DirectoryResource();
		for(File f : dr.selectedFiles()){
			FileResource fr = new FileResource(f);
			CSVRecord currentRow = hottestHourInFile(fr.getCSVParser());
			if(largestSoFar == null){
				largestSoFar = currentRow;
			}
			else{
				double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
				double largestTemp = Double.parseDouble(largestSoFar.get("TemperatureF"));
				if(currentTemp > largestTemp){
					largestSoFar = currentRow;
				}
			}
		}
		return largestSoFar;
	}
	private void testHottestTemperatureInDay() {
		FileResource fr = new FileResource();
		CSVRecord largest = hottestHourInFile(fr.getCSVParser());
		System.out.println("Hottest temperature was " + largest.get("TemperatureF") + " at " + largest.get("TimeEST"));
	}
	private CSVRecord hottestHourInFile(CSVParser csvParser) {
		CSVRecord largestSoFar = null;
		for(CSVRecord currentRow : csvParser){
			if(largestSoFar == null){
				largestSoFar = currentRow;
			}
			else{
				double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
				double largestTemp = Double.parseDouble(largestSoFar.get("TemperatureF"));
				if(currentTemp > largestTemp){
					largestSoFar = currentRow;
				}
				
			}
		}
		return largestSoFar;
	}
	

}
