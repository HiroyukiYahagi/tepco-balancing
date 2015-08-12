package com.oracle.invocation;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

import com.oracle.objects.Demand;
import com.oracle.objects.DemandKey;
import com.tangosol.io.pof.PofReader;
import com.tangosol.io.pof.PofWriter;
import com.tangosol.io.pof.PortableObject;
import com.tangosol.net.AbstractInvocable;
import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;
import com.tangosol.util.filter.AndFilter;
import com.tangosol.util.filter.EqualsFilter;

public class InputDemandInvocation extends AbstractInvocable implements PortableObject{

	private static final long serialVersionUID = 1L;
	private static final Integer damandNumberMax = 10000;
	private ThreadLocalRandom rnd = ThreadLocalRandom.current();
	private NamedCache demandCache = CacheFactory.getCache("demand-cache");
	
	private Integer invokedCode;	
	private Integer timeCode;
	private Integer supplyDate;
	private String cacheName;

	public InputDemandInvocation(Integer invokedCode, Integer timeCode, Integer supplyDate, String cacheName) {
		super();
		this.invokedCode = invokedCode;
		this.timeCode = timeCode;
		this.supplyDate = supplyDate;
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
		
		for(int i=0; i<damandNumberMax; i++){
			if(i%100 == 0)
				System.out.println("count:"+i);
			
			if(rnd.nextInt(0,100) == 1){
				//欠損データの補完
				Integer compTimeCode = 0;
				Integer compSupplyDate = supplyDate;
				
				if(timeCode != 0){
					compTimeCode = timeCode -1;
				}else{
					compTimeCode = 47;
					Calendar calendar = Calendar.getInstance();
					calendar.set(supplyDate/10000, (supplyDate/100)%100, supplyDate%100);
					calendar.add(Calendar.DAY_OF_MONTH, -1);
					compSupplyDate = calendar.get(Calendar.YEAR)*10000 + calendar.get(Calendar.MONTH)*100 + calendar.get(Calendar.DAY_OF_MONTH);
				}
				
				DemandKey key = new DemandKey(invokedCode, damandNumberMax, compSupplyDate, compTimeCode);
				Demand completionData = (Demand) demandCache.get(key);
				if(completionData == null){
					completionData = new Demand();
				}

				Demand newDemand = new Demand(completionData);
				newDemand.setDeficit(1);
				demandCache.put(newDemand.getKey(), newDemand);
				
			}else{
				//通常のput
				Demand newDemand = new Demand(invokedCode, damandNumberMax, createVolume(), supplyDate, timeCode, 0);
				demandCache.put(newDemand.getKey(), newDemand);
			}
		}
		
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
