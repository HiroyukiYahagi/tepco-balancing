package com.oracle.main;

import java.util.Collections;
import java.util.Set;

import com.oracle.invocation.InputDemandInvocation;
import com.oracle.invocation.InputDemandInvocationObserver;
import com.oracle.invocation.InputPartialSupplyInvocation;
import com.oracle.invocation.InputPartialSupplysObserver;
import com.tangosol.net.CacheFactory;
import com.tangosol.net.DistributedCacheService;
import com.tangosol.net.InvocationService;
import com.tangosol.net.Member;

public class InputDemand {
	
	public static Integer invocationServiceThreadCount = 1; //

	public static void main(String[] args) {
		
		Integer supplyDate = 20150819;
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
		Set<Member> memberSet = ((DistributedCacheService)(CacheFactory.getService("dist-service"))).getOwnershipEnabledMembers();
		
		for (int threadNum = 0; threadNum < invocationServiceThreadCount; threadNum++) {
			for (int memberNum = 0; memberNum < memberSet.size(); memberNum ++) {
				
				System.out.println("invoked member:" + memberNum + " thread:"+threadNum);
				
				InputDemandInvocation idi = new InputDemandInvocation(threadNum*memberSet.size() + memberNum, supplyDate, timeCode, "demand-cache-"+supplyDate);
				InputDemandInvocationObserver idiObserver = new InputDemandInvocationObserver();
				invService.execute(idi, Collections.singleton(memberSet.toArray()[memberNum]), idiObserver);
			}
		}
		
		while(true){
			try {
				Thread.sleep(5000);
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(InputDemandInvocationObserver.endCount >= invocationServiceThreadCount * memberSet.size()){
				break;
			}else{
				System.out.println("waiting endcount:" + InputDemandInvocationObserver.endCount);
			}
		}
		
	}
	
	
	public static void inpuPartialSupply(Integer supplyDate, Integer timeCode){
		InvocationService invService = (InvocationService) CacheFactory.getService("invo-service");
		Set<Member> memberSet = ((DistributedCacheService)(CacheFactory.getService("dist-service"))).getOwnershipEnabledMembers();
		
		for (int threadNum = 0; threadNum < invocationServiceThreadCount; threadNum++) {
			for (int memberNum = 0; memberNum < memberSet.size(); memberNum ++) {
				
				System.out.println("invoked member:" + memberNum + " thread:"+threadNum);
				
				InputPartialSupplyInvocation ipi = new InputPartialSupplyInvocation(threadNum*memberSet.size() + memberNum, supplyDate, timeCode, "demand-cache-"+supplyDate);
				InputPartialSupplysObserver ipiObserver = new InputPartialSupplysObserver();
				invService.execute(ipi, Collections.singleton(memberSet.toArray()[memberNum]), ipiObserver);
			}
		}
		
		while(true){
			try {
				Thread.sleep(5000);
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(InputPartialSupplysObserver.endCount >= invocationServiceThreadCount * memberSet.size()){
				break;
			}else{
				System.out.println("waiting endcount:" + InputPartialSupplysObserver.endCount);
			}
		}
	}
	
}
