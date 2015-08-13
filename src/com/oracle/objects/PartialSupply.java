package com.oracle.objects;

import java.math.BigDecimal;
import java.util.concurrent.ThreadLocalRandom;

public class PartialSupply {
	
	private static ThreadLocalRandom rnd = ThreadLocalRandom.current();

	private Integer mainSupplyerCode;
	private Integer subSupplyerCode;
	private BigDecimal totalVolume;
	private Integer customerCode;
	private Integer supplyDate;
	private Integer timeCode;
	
	
	public PartialSupply() {
		super();
		// TODO Auto-generated constructor stub
		this.mainSupplyerCode = 0;
		this.subSupplyerCode = 0;
		this.totalVolume = null;
		this.customerCode = 0;
		this.supplyDate = 19890819;
		this.timeCode = 0;
	}
	
	public PartialSupply(Integer mainSupplyerCode, Integer subSupplyerCodeMax, Integer customerCodeMax, Integer supplyDate, Integer timeCode) {
		super();
		this.mainSupplyerCode = mainSupplyerCode;
		this.subSupplyerCode = rnd.nextInt(0, subSupplyerCodeMax);
		this.customerCode = rnd.nextInt(0, customerCodeMax);
		this.totalVolume = new BigDecimal(rnd.nextInt(100,200));
		this.supplyDate = supplyDate;
		this.timeCode = timeCode;
		
		System.out.println(this);
	}


	public PartialSupply(Integer mainSupplyerCode, Integer subSupplyerCode, BigDecimal totalVolume,
			Integer customerCode, Integer supplyDate, Integer timeCode) {
		super();
		this.mainSupplyerCode = mainSupplyerCode;
		this.subSupplyerCode = subSupplyerCode;
		this.totalVolume = totalVolume;
		this.customerCode = customerCode;
		this.supplyDate = supplyDate;
		this.timeCode = timeCode;
	}
	
	public Integer getMainSupplyerCode() {
		return mainSupplyerCode;
	}
	public void setMainSupplyerCode(Integer mainSupplyerCode) {
		this.mainSupplyerCode = mainSupplyerCode;
	}
	public Integer getSubSupplyerCode() {
		return subSupplyerCode;
	}
	public void setSubSupplyerCode(Integer subSupplyerCode) {
		this.subSupplyerCode = subSupplyerCode;
	}
	public BigDecimal getTotalVolume() {
		return totalVolume;
	}
	public void setTotalVolume(BigDecimal totalVolume) {
		this.totalVolume = totalVolume;
	}
	public Integer getCustomerCode() {
		return customerCode;
	}
	public void setCustomerCode(Integer customerCode) {
		this.customerCode = customerCode;
	}
	public Integer getSupplyDate() {
		return supplyDate;
	}
	public void setSupplyDate(Integer supplyDate) {
		this.supplyDate = supplyDate;
	}
	public Integer getTimeCode() {
		return timeCode;
	}
	public void setTimeCode(Integer timeCode) {
		this.timeCode = timeCode;
	}
	
	public DemandKey getDemandKey(){
		return new DemandKey(mainSupplyerCode, customerCode, supplyDate, timeCode);
	}
	
	public Demand getDifferenceDemand(Demand base){
		if(base == null)
			return new Demand(subSupplyerCode, customerCode, totalVolume, supplyDate, timeCode, 2);
		
		return new Demand(subSupplyerCode, 
						customerCode,
						(totalVolume.compareTo(base.getVolume()) >= 0 ? totalVolume.subtract(base.getVolume()) : BigDecimal.ZERO),
						supplyDate, 
						timeCode, 
						2);
	}

	@Override
	public String toString() {
		return "PartialSupply [mainSupplyerCode=" + mainSupplyerCode + ", subSupplyerCode=" + subSupplyerCode
				+ ", totalVolume=" + totalVolume + ", customerCode=" + customerCode + ", supplyDate=" + supplyDate
				+ ", timeCode=" + timeCode + "]";
	}
	
	
}
