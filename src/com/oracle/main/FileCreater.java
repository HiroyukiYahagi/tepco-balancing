package com.oracle.main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class FileCreater {

	public static void main(String[] args) {
		
		int date=20150815, timeCode=0;
		
		if(args.length == 2){
			date = Integer.valueOf(args[0]);
			timeCode = Integer.valueOf(args[1]);
		}
		
		System.out.println("start file create date:" + date + " time:" + timeCode );
		
		try {
			createDemandWithDateAndTimeCode(date,timeCode);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("end file create");
		
	}
	
	public static void createDemandWithDateAndTimeCode(int date, int timeCode) throws IOException {
		Map<Integer, Integer> companyMap = createCompanyMap();
		
		for(Map.Entry<Integer, Integer> entry : companyMap.entrySet()){
			createDemand(entry.getKey(), entry.getValue(), date, timeCode);
			createPartialSupply(entry.getKey(), entry.getValue(), date, timeCode);
		}
	}
	
	private static Map<Integer, Integer> createCompanyMap() {
		
		Map<Integer, Integer> companyMap = new HashMap<Integer, Integer>(10);
		
//		companyMap.put(0, 1000000);  
//		companyMap.put(1, 1000000);  
//		companyMap.put(2, 100000);   
//		companyMap.put(3, 100000);   
//		companyMap.put(4, 100000);   
//		companyMap.put(5, 100000);   
//		companyMap.put(6, 100000);   
//		companyMap.put(7, 100000);  
//		companyMap.put(8, 10000);  
//		companyMap.put(9, 10000);  
		
		companyMap.put(0, 1000);  
		companyMap.put(1, 1000);  
		companyMap.put(2, 100);   
		companyMap.put(3, 100);   
		companyMap.put(4, 100);   
		companyMap.put(5, 100);   
		companyMap.put(6, 100);   
		companyMap.put(7, 100);  
		companyMap.put(8, 10);  
		companyMap.put(9, 10);  

		return companyMap;
	}
	
	private static void createDemand(int companyId, int meterCouont, int date, int timeCode) throws IOException{
		File file = new File("files/demand_" + date + "_" + timeCode + "_" + companyId + ".csv");
		FileWriter filewriter = new FileWriter(file);
		BufferedWriter bw = new BufferedWriter(filewriter);
		
		for(int i = 0; i < meterCouont; i++){
			bw.write(createLine(i, companyId, date, timeCode) + "\n");
		}
		
		bw.close();
		filewriter.close();
		
	}

	private static String createLine(int mi, int ci, int measureDateTime,int timeCode) {
		
		String meterId = String.valueOf(mi);
		String companyId = String.valueOf(ci);
		Integer areaId = ThreadLocalRandom.current().nextInt(0, 10);
		Integer volume = ThreadLocalRandom.current().nextInt(50, 100);
		Integer deficit = ThreadLocalRandom.current().nextInt(0, 100) == 1 ? 1 : 0;
		
		return companyId + "," + meterId + "," + measureDateTime + "," + timeCode + ","+ areaId + "," + volume + "," + deficit;
	}
	
	
	private static void createPartialSupply(int companyId, int meterCouont, int date, int timeCode) throws IOException{
		File file = new File("files/partialsupply_" + date + "_" + timeCode + "_" + companyId + ".csv");
		FileWriter filewriter = new FileWriter(file);
		BufferedWriter bw = new BufferedWriter(filewriter);
		
		for(int i = 0; i < meterCouont; i++){
			if(ThreadLocalRandom.current().nextInt(0,101) != 1) 
				continue;
			
			bw.write(createLinePS(i, companyId, date, timeCode) + "\n");
		}
		
		bw.close();
		filewriter.close();
		
	}

	private static String createLinePS(int mi, int ci, int measureDateTime,int timeCode) {
		
		String meterId = String.valueOf(mi);
		String mainCompanyId = String.valueOf(ci);
		Integer subCompantId = ThreadLocalRandom.current().nextInt(ci+1, ci + 10) % 10;
		Integer realVolume = ThreadLocalRandom.current().nextInt(1, 50);
		
		return mainCompanyId + "," + subCompantId + "," + meterId + "," + measureDateTime + "," + timeCode + "," + realVolume;
	}
}
