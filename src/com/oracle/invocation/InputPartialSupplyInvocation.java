package com.oracle.invocation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.concurrent.ThreadLocalRandom;

import com.oracle.objects.Demand;
import com.oracle.objects.DemandKey;
import com.tangosol.io.pof.PofReader;
import com.tangosol.io.pof.PofWriter;
import com.tangosol.io.pof.PortableObject;
import com.tangosol.net.AbstractInvocable;
import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;

public class InputPartialSupplyInvocation extends AbstractInvocable implements PortableObject{

	private static final long serialVersionUID = 1L;
	private ThreadLocalRandom rnd = ThreadLocalRandom.current();
	private NamedCache demandCache = CacheFactory.getCache("demand-cache");
	
	private Integer invokedCode;
	private Integer timeCode;
	private Integer supplyDate;
	private String cacheName;
	
	
	public InputPartialSupplyInvocation() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public InputPartialSupplyInvocation(Integer invokedCode, Integer supplyDate, Integer timeCode, String cacheName) {
		super();
		this.invokedCode = invokedCode;
		this.timeCode = timeCode;
		this.supplyDate = supplyDate;
		this.cacheName = cacheName;
	}

	public ThreadLocalRandom getRnd() {
		return rnd;
	}
	public void setRnd(ThreadLocalRandom rnd) {
		this.rnd = rnd;
	}
	public Integer getInvokedCode() {
		return invokedCode;
	}
	public void setInvokedCode(Integer invokedCode) {
		this.invokedCode = invokedCode;
	}
	public Integer getTimeCode() {
		return timeCode;
	}
	public void setTimeCode(Integer timeCode) {
		this.timeCode = timeCode;
	}
	public Integer getSupplyDate() {
		return supplyDate;
	}
	public void setSupplyDate(Integer supplyDate) {
		this.supplyDate = supplyDate;
	}
	public String getCacheName() {
		return cacheName;
	}
	public void setCacheName(String cacheName) {
		this.cacheName = cacheName;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("start partial");
		
		try {
			String filename = "files/partialsupply_" + supplyDate + "_" + timeCode + "_" + invokedCode + ".csv";
			File file = new File(filename);
			FileReader filereader = new FileReader(file);
		
			BufferedReader br = new BufferedReader(filereader);
			
			String str;
			while((str = br.readLine()) != null){
				
				String[] partialData = str.split(",");
				if(partialData.length != 6){
					System.out.println("ファイル形式エラー length:" + partialData.length);
					continue;
				}
				
				//部分供給仕分け処理
				
				DemandKey mainBaseKey = new DemandKey(Integer.parseInt(partialData[0]), Integer.parseInt(partialData[2]), Integer.parseInt(partialData[3]), Integer.parseInt(partialData[4]));
				Demand mainDemand = (Demand) demandCache.get(mainBaseKey);
				if(mainDemand == null){
					System.out.println("元データがありません key:" + mainBaseKey);
					continue;
				}
				
				DemandKey subDemandKey = new DemandKey(Integer.parseInt(partialData[1]), Integer.parseInt(partialData[2]), Integer.parseInt(partialData[3]), Integer.parseInt(partialData[4]));
				Demand subDemand = (Demand) demandCache.get(subDemandKey);
				if(subDemand == null){
					subDemand = new Demand(Integer.parseInt(partialData[1]), 
							Integer.parseInt(partialData[2]), BigDecimal.ZERO, Integer.parseInt(partialData[3]), Integer.parseInt(partialData[4]), 0, mainDemand.getAreaCode());
				}
				
				BigDecimal realMainVolume = new BigDecimal(partialData[5]);
				
				subDemand.setVolume(mainDemand.getVolume().subtract(realMainVolume));
				mainDemand.setVolume(realMainVolume);
				
				System.out.println("main data key:" + mainDemand.getKey() + " data:"+mainDemand);
				demandCache.put(mainDemand.getKey(), mainDemand);
				System.out.println("sub data key:" + subDemand.getKey() + " data:"+subDemand);
				demandCache.put(subDemand.getKey(), subDemand);
				
			}
			  
			br.close();
			filereader.close();
	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		System.out.println("end partial");
	}
	
	
	@Override
	public void readExternal(PofReader arg0) throws IOException {
		// TODO Auto-generated method stub
		this.invokedCode = arg0.readInt(0);
		this.supplyDate = arg0.readInt(1);
		this.timeCode = arg0.readInt(2);
		this.cacheName = arg0.readString(3);
	}
	
	@Override
	public void writeExternal(PofWriter arg0) throws IOException {
		// TODO Auto-generated method stub
		arg0.writeInt(0, this.invokedCode);
		arg0.writeInt(1, this.supplyDate);
		arg0.writeInt(2, this.timeCode);
		arg0.writeString(3, this.cacheName);
	}
	
}
