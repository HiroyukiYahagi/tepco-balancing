package com.oracle.objects;

import java.io.IOException;

import com.tangosol.io.pof.PofReader;
import com.tangosol.io.pof.PofWriter;
import com.tangosol.io.pof.PortableObject;
import com.tangosol.net.cache.KeyAssociation;

public class GeneratedKey implements PortableObject, KeyAssociation{
	
	private Integer generaterCode;
	private Integer instrumentCode;
	private Integer supplyDate;
	private Integer timeCode;
	private Integer contruction;
	
	public GeneratedKey() {
		super();
	}
	
	public GeneratedKey(Generated gen){
		super();
		this.generaterCode = gen.getGeneraterCode();
		this.instrumentCode = gen.getInstrumentCode();
		this.supplyDate = gen.getSupplyDate();
		this.timeCode = gen.getTimeCode();
		this.contruction = gen.getContruction();
	}
	
	public GeneratedKey(Integer generaterCode, Integer instrumentCode, Integer supplyDate, Integer timeCode,
			Integer contruction) {
		super();
		this.generaterCode = generaterCode;
		this.instrumentCode = instrumentCode;
		this.supplyDate = supplyDate;
		this.timeCode = timeCode;
		this.contruction = contruction;
	}
	public Integer getGeneraterCode() {
		return generaterCode;
	}
	public void setGeneraterCode(Integer generaterCode) {
		this.generaterCode = generaterCode;
	}
	public Integer getInstrumentCode() {
		return instrumentCode;
	}
	public void setInstrumentCode(Integer instrumentCode) {
		this.instrumentCode = instrumentCode;
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
	public Integer getContruction() {
		return contruction;
	}
	public void setContruction(Integer contruction) {
		this.contruction = contruction;
	}
	@Override
	public Object getAssociatedKey() {
		// TODO Auto-generated method stub
		return this.generaterCode;
	}
	@Override
	public void readExternal(PofReader arg0) throws IOException {
		// TODO Auto-generated method stub
		this.generaterCode = arg0.readInt(0);
		this.instrumentCode = arg0.readInt(1);
		this.supplyDate = arg0.readInt(2);
		this.timeCode = arg0.readInt(3);
		this.contruction = arg0.readInt(4);
	}
	@Override
	public void writeExternal(PofWriter arg0) throws IOException {
		// TODO Auto-generated method stub
		arg0.writeInt(0, this.generaterCode);
		arg0.writeInt(1, this.instrumentCode);
		arg0.writeInt(2, this.supplyDate);
		arg0.writeInt(3, this.timeCode);
		arg0.writeInt(4, this.contruction);
	}
}
