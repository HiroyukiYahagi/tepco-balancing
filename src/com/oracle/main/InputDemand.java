package com.oracle.main;

import java.util.Collections;
import java.util.Set;

import com.oracle.invocation.InputDemandInvocation;
import com.oracle.invocation.InputDemandInvocationObserver;
import com.oracle.invocation.InputPartialSupplyInvocation;
import com.oracle.invocation.InputPartialSupplysObserver;
import com.oracle.objects.DemandKey;
import com.tangosol.net.CacheFactory;
import com.tangosol.net.DistributedCacheService;
import com.tangosol.net.InvocationService;
import com.tangosol.net.Member;

public class InputDemand {
	
	public static Integer invocationServiceThreadCount = 1; //

	public static void main(String[] args) {
		
		Integer supplyDate = 20150815;
		Integer timeCode = 0;
		
		System.out.println("start input demand");
		long start = System.currentTimeMillis();
		long now = System.currentTimeMillis();
		
		inputDemandOnly(supplyDate, timeCode);
		
		System.out.println("end input demand:" + (System.currentTimeMillis() - now) + "ms");
		now = System.currentTimeMillis();
		
		inpuPartialSupply(supplyDate, timeCode);
		
		System.out.println("end input partial supply:" + (System.currentTimeMillis() - now) + "ms");
		
		System.out.println("end all:" + (System.currentTimeMillis() - start) + "ms");
	}
	
	public static void inputDemandOnly(Integer supplyDate, Integer timeCode){

		InvocationService invService = (InvocationService) CacheFactory.getService("invo-service");
		DistributedCacheService distService = (DistributedCacheService)CacheFactory.getService("dist-service");
		Object lock = new Object();
		InputDemandInvocationObserver idiObserver = new InputDemandInvocationObserver(lock);
		
		String flag = System.getProperty("com.oracle.objects.demand.affinity");
		if(flag != null && flag.equals("true")){
			for(int supplyerCode=0; supplyerCode< 10; supplyerCode++){
				DemandKey dummyKey = new DemandKey(supplyerCode, 0, 0, 0);
				Member mem = distService.getKeyOwner(dummyKey);	
				InputDemandInvocation idi = new InputDemandInvocation(supplyerCode, supplyDate, timeCode, "demand-cache-"+supplyDate);
				invService.execute(idi, Collections.singleton(mem), idiObserver);
			}	
		}else{
			Set memberSet = distService.getOwnershipEnabledMembers();
			for (Member men : memberSet) {
				
			}
		}
		
		
		synchronized (lock) {
			try {
				lock.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	
	public static void inpuPartialSupply(Integer supplyDate, Integer timeCode){
		InvocationService invService = (InvocationService) CacheFactory.getService("invo-service");
		DistributedCacheService distService = (DistributedCacheService)CacheFactory.getService("dist-service");
		Object lock = new Object();
		InputPartialSupplysObserver ipiObserver = new InputPartialSupplysObserver(lock);
		
		String flag = System.getProperty("com.oracle.objects.demand.affinity");
		if(flag != null && flag.equals("true")){
			for(int supplyerCode=0; supplyerCode< 10; supplyerCode++){
				DemandKey dummyKey = new DemandKey(supplyerCode, 0, 0, 0);
				Member mem = distService.getKeyOwner(dummyKey);			
				InputPartialSupplyInvocation ipi = new InputPartialSupplyInvocation(supplyerCode, supplyDate, timeCode, "demand-cache-"+supplyDate);
				invService.execute(ipi, Collections.singleton(mem), ipiObserver);
			}
		}else{
			Set memberSet = distService.getOwnershipEnabledMembers();
			for (Member men : memberSet) {
				
			}
		}
		
		synchronized (lock) {
			try {
				lock.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}

	}
	
}
