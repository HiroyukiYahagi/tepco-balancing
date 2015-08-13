package com.oracle.main;

import java.util.Collections;
import java.util.Set;

import com.oracle.invocation.InputDemandInvocation;
import com.oracle.invocation.InputDemandInvocationObserver;
import com.oracle.invocation.InputGeneratedInvocation;
import com.oracle.invocation.InputGeneratedInvocationObserver;
import com.tangosol.net.CacheFactory;
import com.tangosol.net.DistributedCacheService;
import com.tangosol.net.InvocationService;
import com.tangosol.net.Member;

public class InputGenerated {
	
	public static Integer invocationServiceThreadCount = 1;

	public static void main(String[] args) {
		Integer supplyDate = 20150819;
		Integer timeCode = 0;
		
		System.out.println("start input demand");
		long start = System.currentTimeMillis();
		long now = System.currentTimeMillis();
		
		inputGenerated(supplyDate, timeCode);
		
		System.out.println("end input demand:" + (System.currentTimeMillis() - now) + "ms");
		now = System.currentTimeMillis();
		
		sortGenerated(supplyDate, timeCode);
		
		System.out.println("end input partial supply:" + (System.currentTimeMillis() - now) + "ms");
		
		System.out.println("end all:" + (System.currentTimeMillis() - start) + "ms");
	
	}
	
	public static void inputGenerated(Integer supplyDate, Integer timeCode){
		
		InvocationService invService = (InvocationService) CacheFactory.getService("invo-service");
		Set<Member> memberSet = ((DistributedCacheService)(CacheFactory.getService("dist-service"))).getOwnershipEnabledMembers();
		
		for (int threadNum = 0; threadNum < invocationServiceThreadCount; threadNum++) {
			for (int memberNum = 0; memberNum < memberSet.size(); memberNum ++) {
				
				System.out.println("invoked member:" + memberNum + " thread:"+threadNum);
				
				InputGeneratedInvocation igi = new InputGeneratedInvocation(threadNum*memberSet.size() + memberNum, supplyDate, timeCode, "demand-cache-"+supplyDate);
				InputGeneratedInvocationObserver igiObserver = new InputGeneratedInvocationObserver();
				invService.execute(igi, Collections.singleton(memberSet.toArray()[memberNum]), igiObserver);
			}
		}
		
		while(true){
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(InputGeneratedInvocationObserver.endCount >= invocationServiceThreadCount * memberSet.size()){
				break;
			}else{
				System.out.println("waiting endcount:" + InputGeneratedInvocationObserver.endCount);
			}
		}
		
	}
	
	public static void sortGenerated(Integer supplyDate, Integer timeCode){
		
		
		
	}
	
}
