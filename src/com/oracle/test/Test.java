package com.oracle.test;

import java.util.Set;

import com.oracle.objects.Demand;
import com.oracle.test.entryprocessor.ShowDataEntryProcessor;
import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;
import com.tangosol.util.filter.AlwaysFilter;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("OK");
		
		if(args.length == 0){
			showDataTest();
		}else if(args[0] == "put"){
			dataPutTest();
		}else if(args[0] == "count"){
			dataCountTest();
		}
		
		System.out.println("end");
	}
	
	public static void dataPutTest(){
		NamedCache cache = CacheFactory.getCache("demand-cache");
		Demand dem = new Demand();
		cache.put(dem.getKey(), dem);
		
		Demand got = (Demand) cache.get(dem.getKey());
		System.out.println(got);
	}

	public static void dataCountTest(){
		NamedCache cache = CacheFactory.getCache("demand-cache");
		
		Set memberSet = cache.entrySet();
		System.out.println("total count:"+ memberSet.size());
	}
	
	public static void showDataTest() {
		NamedCache cache = CacheFactory.getCache("demand-cache");
		cache.invokeAll(AlwaysFilter.INSTANCE, new ShowDataEntryProcessor());
	}
}
