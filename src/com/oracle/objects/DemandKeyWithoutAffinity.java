package com.oracle.objects;

class DemandKeyWithoutAffinity extends DemandKey{

	@Override
	public Object getAssociatedKey() {
		// TODO Auto-generated method stub
		return null;
	}	
	
//
//	private Integer supplyerCode;
//	private Integer customerCode;
//	private Integer supplyDate;
//	private Integer timeCode;
//	
//	
	public DemandKeyWithoutAffinity() {
		super();
		// TODO Auto-generated constructor stub
	}
//
	public DemandKeyWithoutAffinity(Integer supplyerCode, Integer customerCode, Integer supplyDate, Integer timeCode) {
		super(supplyerCode, customerCode, supplyDate, timeCode);
	}
//
	public DemandKeyWithoutAffinity(Demand dem){
		super(dem);
	}
//
//
//	public Integer getSupplyerCode() {
//		return supplyerCode;
//	}
//
//	public void setSupplyerCode(Integer supplyerCode) {
//		this.supplyerCode = supplyerCode;
//	}
//
//	public Integer getCustomerCode() {
//		return customerCode;
//	}
//
//	public void setCustomerCode(Integer customerCode) {
//		this.customerCode = customerCode;
//	}
//
//	public Integer getSupplyDate() {
//		return supplyDate;
//	}
//
//	public void setSupplyDate(Integer supplyDate) {
//		this.supplyDate = supplyDate;
//	}
//
//	public Integer getTimeCode() {
//		return timeCode;
//	}
//
//	public void setTimeCode(Integer timeCode) {
//		this.timeCode = timeCode;
//	}
//
//	@Override
//	public void readExternal(PofReader arg0) throws IOException {
//		// TODO Auto-generated method stub
//		this.supplyerCode = arg0.readInt(0);
//		this.customerCode = arg0.readInt(1);
//		this.supplyDate = arg0.readInt(2);
//		this.timeCode = arg0.readInt(3);
//	}
//
//	@Override
//	public void writeExternal(PofWriter arg0) throws IOException {
//		// TODO Auto-generated method stub
//		arg0.writeInt(0, this.supplyerCode);
//		arg0.writeInt(1, this.customerCode);
//		arg0.writeInt(2, this.supplyDate);
//		arg0.writeInt(3, this.timeCode);
//	}
//
//	@Override
//	public boolean equals(Object obj) {
//		// TODO Auto-generated method stub
//		if(this == obj)
//			return true;
//		
//		if(obj.getClass() != this.getClass())
//			return false;
//		
//		DemandKeyWithoutAffinity objKey = (DemandKeyWithoutAffinity)obj;
//		
//		if(objKey.getCustomerCode() != this.customerCode)
//			return false;
//		
//		if(objKey.getSupplyerCode() != this.supplyerCode)
//			return false;
//		
//		if(objKey.getTimeCode() != this.timeCode)
//			return false;
//		
//		if(objKey.getSupplyDate() != this.supplyDate)
//			return false;
//		
//		return true;
//	}
//
//	@Override
//	public String toString() {
//		return "DemandKey [supplyerCode=" + supplyerCode + ", customerCode=" + customerCode + ", supplyDate="
//				+ supplyDate + ", timeCode=" + timeCode + "]";
//	}
}
