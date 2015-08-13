package com.oracle.invocation;

import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

import com.oracle.objects.Demand;
import com.oracle.objects.PartialSupply;
import com.tangosol.io.pof.PofReader;
import com.tangosol.io.pof.PofWriter;
import com.tangosol.io.pof.PortableObject;
import com.tangosol.net.AbstractInvocable;
import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;

public class InputPartialSupplyInvocation extends AbstractInvocable implements PortableObject{

	private static final long serialVersionUID = 1L;
	private static final Integer partialSupplyNumberMax = 10;
	private static final Integer subSupplyerCodeMax = 3;
	private static final Integer customerCodeMax = 100;
	private ThreadLocalRandom rnd = ThreadLocalRandom.current();
	
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
		NamedCache demandCache = CacheFactory.getCache("demand-cache");
		
		for(int i=0; i<partialSupplyNumberMax; i++){
			
			PartialSupply ps = new PartialSupply(invokedCode, subSupplyerCodeMax, customerCodeMax, supplyDate, timeCode);
			System.out.println("try key:"+ps.getDemandKey());
			Demand base = (Demand) demandCache.get(ps.getDemandKey());
			Demand difference = ps.getDifferenceDemand(base);
			Demand sameParameterData = (Demand) demandCache.get(difference.getKey());
			if(sameParameterData != null){
				difference.setVolume(difference.getVolume().add(sameParameterData.getVolume()));
			}
			System.out.println("dase key:" + (base == null ? "null" : base.getKey()) + " data:"+base);
			System.out.println("differenced key:" + difference.getKey() + " data:"+difference);
			demandCache.put(difference.getKey(), difference);
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
