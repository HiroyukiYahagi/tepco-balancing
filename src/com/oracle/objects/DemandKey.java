package com.oracle.objects;

import java.io.IOException;

import com.tangosol.io.pof.PofReader;
import com.tangosol.io.pof.PofWriter;
import com.tangosol.io.pof.PortableObject;
import com.tangosol.net.cache.KeyAssociation;

public class DemandKey implements PortableObject, KeyAssociation{
	
	private Integer supplyerCode;
	private Integer customerCode;
	private Integer supplyDate;
	private Integer timeCode;
	
	public DemandKey(Integer supplyerCode, Integer customerCode, Integer supplyDate, Integer timeCode) {
		super();
		this.supplyerCode = supplyerCode;
		this.customerCode = customerCode;
		this.supplyDate = supplyDate;
		this.timeCode = timeCode;
	}
	
	public DemandKey(Demand dem){
		super();
		this.supplyerCode = dem.getSupplyerCode();
		this.customerCode = dem.getCustomerCode();
		this.supplyDate = dem.getSupplyDate();
		this.timeCode = dem.getTimeCode();
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

	@Override
	public Object getAssociatedKey() {
		// TODO Auto-generated method stub
		return this.supplyerCode;
	}

	@Override
	public void readExternal(PofReader arg0) throws IOException {
		// TODO Auto-generated method stub
		this.supplyerCode = arg0.readInt(0);
		this.customerCode = arg0.readInt(1);
		this.supplyDate = arg0.readInt(2);
		this.timeCode = arg0.readInt(3);
	}

	@Override
	public void writeExternal(PofWriter arg0) throws IOException {
		// TODO Auto-generated method stub
		arg0.writeInt(0, this.supplyerCode);
		arg0.writeInt(1, this.customerCode);
		arg0.writeInt(2, this.supplyDate);
		arg0.writeInt(3, this.timeCode);
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if(this == obj)
			return true;
		
		if(obj.getClass() != this.getClass())
			return false;
		
		DemandKey objKey = (DemandKey)obj;
		
		if(objKey.getCustomerCode() != this.customerCode)
			return false;
		
		if(objKey.getSupplyerCode() != this.supplyerCode)
			return false;
		
		if(objKey.getTimeCode() != this.timeCode)
			return false;
		
		if(objKey.getSupplyDate() != this.supplyDate)
			return false;
		
		return true;
	}
}
