package com.oracle.objects;

import java.io.IOException;
import java.math.BigDecimal;

import com.tangosol.io.pof.PofReader;
import com.tangosol.io.pof.PofWriter;
import com.tangosol.io.pof.PortableObject;

public class Demand implements PortableObject{

	private Integer supplyerCode;
	private Integer customerCode;
	private BigDecimal volume;
	private Integer supplyDate;
	private Integer timeCode;
	private Integer deficit;
	private Integer areaCode;
	
	public Demand() {
		super();
		// TODO Auto-generated constructor stub
		this.supplyerCode = 0;
		this.customerCode = 0;
		this.volume = BigDecimal.ZERO;
		this.supplyDate = 19890819;
		this.timeCode = 0;
		this.deficit = 0;
	}
	
	public Demand(Integer supplyerCode, Integer customerCode, BigDecimal volume, Integer supplyDate, Integer timeCode,
			Integer deficit, Integer areaCode) {
		super();
		this.supplyerCode = supplyerCode;
		this.customerCode = customerCode;
		this.volume = volume;
		this.supplyDate = supplyDate;
		this.timeCode = timeCode;
		this.deficit = deficit;
		this.areaCode = areaCode;
	}

	public Demand(Demand dem) {
		super();
		this.supplyerCode = dem.getSupplyerCode();
		this.customerCode = dem.getCustomerCode();
		this.volume = dem.getVolume();
		this.supplyDate = dem.getSupplyDate();
		this.timeCode = dem.getTimeCode();
		this.deficit = dem.getDeficit();
		this.areaCode = dem.getAreaCode();
	}

	public Integer getSupplyerCode() {
		return supplyerCode;
	}

	public void setSupplyerCode(Integer supplyerCode) {
		this.supplyerCode = supplyerCode;
	}

	public Integer getCustomerCode() {
		return customerCode;
	}

	public void setCustomerCode(Integer customerCode) {
		this.customerCode = customerCode;
	}

	public BigDecimal getVolume() {
		return volume;
	}

	public void setVolume(BigDecimal volume) {
		this.volume = volume;
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

	public Integer getDeficit() {
		return deficit;
	}

	public void setDeficit(Integer deficit) {
		this.deficit = deficit;
	}

	public Integer getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(Integer areaCode) {
		this.areaCode = areaCode;
	}

	public Object getKey(){
		return new DemandKey(this);
	
	}
	
	
	@Override
	public void readExternal(PofReader arg0) throws IOException {
		// TODO Auto-generated method stub
		this.supplyerCode = arg0.readInt(0);
		this.customerCode = arg0.readInt(1);
		this.volume = arg0.readBigDecimal(2);
		this.supplyDate = arg0.readInt(3);
		this.timeCode = arg0.readInt(4);
		this.deficit = arg0.readInt(5);
		this.areaCode = arg0.readInt(6);
	}

	@Override
	public void writeExternal(PofWriter arg0) throws IOException {
		// TODO Auto-generated method stub
		arg0.writeInt(0, this.supplyerCode);
		arg0.writeInt(1, this.customerCode);
		arg0.writeBigDecimal(2, this.volume);
		arg0.writeInt(3, this.supplyDate);
		arg0.writeInt(4, this.timeCode);
		arg0.writeInt(5, this.deficit);
		arg0.writeInt(6, this.areaCode);
	}

	@Override
	public String toString() {
		return "Demand [supplyerCode=" + supplyerCode + ", customerCode=" + customerCode + ", volume=" + volume
				+ ", supplyDate=" + supplyDate + ", timeCode=" + timeCode + ", deficit=" + deficit + ", areaCode="
				+ areaCode + "]";
	}
	
}
