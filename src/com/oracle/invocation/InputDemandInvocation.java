package com.oracle.invocation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.concurrent.ThreadLocalRandom;

import com.oracle.objects.Demand;
import com.oracle.objects.DemandKey;
import com.tangosol.io.pof.PofReader;
import com.tangosol.io.pof.PofWriter;
import com.tangosol.io.pof.PortableObject;
import com.tangosol.net.AbstractInvocable;
import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;

public class InputDemandInvocation extends AbstractInvocable implements PortableObject{

	private static final long serialVersionUID = 1L;
	private ThreadLocalRandom rnd = ThreadLocalRandom.current();
	private NamedCache demandCache = CacheFactory.getCache("demand-cache");
	
	private Integer invokedCode;	
	private Integer timeCode;
	private Integer supplyDate;
	private String cacheName;
	
	public InputDemandInvocation() {
		super();
		// TODO Auto-generated constructor stub
	}

	public InputDemandInvocation(Integer invokedCode, Integer supplyDate, Integer timeCode, String cacheName) {
		super();
		this.invokedCode = invokedCode;
		this.supplyDate = supplyDate;
		this.timeCode = timeCode;
		this.cacheName = cacheName;
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
		System.out.println("start demand only");
		
		try {
			String filename = "files/demand_" + supplyDate + "_" + timeCode + "_" + invokedCode + ".csv";
			File file = new File(filename);
			FileReader filereader = new FileReader(file);
		
			BufferedReader br = new BufferedReader(filereader);
			
			String str;
			while((str = br.readLine()) != null){
				
				String[] demandData = str.split(",");
				if(demandData.length != 7){
					System.out.println("ファイル形式エラー length:" + demandData.length);
					continue;
				}
				
				Demand newDemand = null;
				
				if(demandData[6].equals("1")){
					//欠損データのため補完処理
					newDemand = completeDeficitDemandData(demandData);
				}else{
					//通常のデータput
					newDemand = new Demand(Integer.parseInt(demandData[0]), 
													Integer.parseInt(demandData[1]), 
													new BigDecimal(demandData[5]), 
													Integer.parseInt(demandData[2]),
													Integer.parseInt(demandData[3]), 
													Integer.parseInt(demandData[6]), 
													Integer.parseInt(demandData[4]));
				}
				System.out.println("data key:" + newDemand.getKey() + " data:"+newDemand);
				demandCache.put(newDemand.getKey(), newDemand);
			}
			  
			br.close();
			filereader.close();
	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	private Demand completeDeficitDemandData(String[] baseData){
		Integer compTimeCode = Integer.parseInt(baseData[4]);
		Integer compSupplyDate = Integer.parseInt(baseData[3]);
		
		if(timeCode != 0){
			compTimeCode = timeCode -1;
		}else{
			compTimeCode = 47;
			Calendar calendar = Calendar.getInstance();
			calendar.set(supplyDate/10000, (supplyDate/100)%100, supplyDate%100);
			calendar.add(Calendar.DAY_OF_MONTH, -1);
			compSupplyDate = calendar.get(Calendar.YEAR)*10000 + calendar.get(Calendar.MONTH)*100 + calendar.get(Calendar.DAY_OF_MONTH);
		}
		
		DemandKey key = new DemandKey(Integer.parseInt(baseData[0]), Integer.parseInt(baseData[1]), compSupplyDate, compTimeCode);
		
		Demand completionData = (Demand) demandCache.get(key);
		if(completionData == null){
			System.out.println("completion data missing:" + key);
			completionData = new Demand(Integer.parseInt(baseData[0]), 
					Integer.parseInt(baseData[1]), 
					new BigDecimal(baseData[5]), 
					Integer.parseInt(baseData[2]),
					Integer.parseInt(baseData[3]), 
					Integer.parseInt(baseData[6]), 
					Integer.parseInt(baseData[4]));
		}

		Demand newDemand = new Demand(completionData);
		newDemand.setDeficit(1);
		newDemand.setTimeCode(timeCode);
		newDemand.setSupplyDate(supplyDate);
		System.out.println("data missing and completion: key:" + newDemand.getKey() + " data:" + newDemand + " from date:" + compSupplyDate + " time:" + compTimeCode);
		return newDemand;
	}
	
	public Integer createCustomerCode(){
		return rnd.nextInt(0, 10000);
	}
	
	public BigDecimal createVolume(){
		return new BigDecimal(rnd.nextInt(0, 100));
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
