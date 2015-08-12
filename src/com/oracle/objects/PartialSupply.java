package com.oracle.objects;

import java.math.BigDecimal;

public class PartialSupply {

	private Integer mainSupplyerCode;
	private Integer subSupplyerCode;
	private BigDecimal totalVolume;
	private Integer customerCode;
	private Integer supplyDate;
	private Integer timeCode;
	
	
	public PartialSupply() {
		super();
		// TODO Auto-generated constructor stub
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
	
	
	
	
}
