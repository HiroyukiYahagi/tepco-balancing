package com.oracle.main;

import java.util.Collections;
import java.util.Set;

import com.oracle.invocation.InputDemandInvocation;
import com.oracle.invocation.InputDemandInvocationObserver;
import com.tangosol.net.CacheFactory;
import com.tangosol.net.DistributedCacheService;
import com.tangosol.net.InvocationService;
import com.tangosol.net.Member;

public class InputDemand {
	
	public static Integer invocationServiceThreadCount = 3;

	public static void main(String[] args) {
		System.out.println("start input demand");
		long start = System.currentTimeMillis();
		inputDemandOnly(20150819, 0);
		
		long end = System.currentTimeMillis();
		System.out.println("end input demand:" + (end -start) + "ms");
	}
	
	public static void inputDemandOnly(Integer supplyDate, Integer timeCode){

		InvocationService invService = (InvocationService) CacheFactory.getService("invo-service");
		Set<Member> memberSet = CacheFactory.getCluster().getMemberSet();
		
		for (int threadNum = 0; threadNum < invocationServiceThreadCount; threadNum++) {
			for (int memberNum = 0; memberNum < memberSet.size(); memberNum ++) {
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
			}
		}
		
	}
	
}
