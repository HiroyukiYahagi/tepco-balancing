package com.oracle.objects;

import java.io.IOException;
import java.math.BigDecimal;

import com.tangosol.io.pof.PofReader;
import com.tangosol.io.pof.PofWriter;
import com.tangosol.io.pof.PortableObject;

public class Generated implements PortableObject{

	private Integer generaterCode;
	private Integer instrumentCode;
	private BigDecimal volume;
	private Integer supplyDate;
	private Integer timeCode;
	private Integer deficit;
	
	private Integer contruction;
	
	public Generated() {
		super();
		// TODO Auto-generated constructor stub
		this.generaterCode = 0;
		this.instrumentCode = 0;
		this.volume = BigDecimal.ZERO;
		this.supplyDate = 19890819;
		this.timeCode = 0;
		this.deficit = 0;
		this.contruction = 0;
	}
	
	public Generated(Generated gen){
		super();
		this.generaterCode = gen.getGeneraterCode();
		this.instrumentCode = gen.getInstrumentCode();
		this.volume = gen.getVolume();
		this.supplyDate = gen.getSupplyDate();
		this.timeCode = gen.getTimeCode();
		this.deficit = gen.getDeficit();
		this.contruction = gen.getContruction();
	}

	public Generated(Integer generaterCode, Integer instrumentCode, BigDecimal volume, Integer supplyDate,
			Integer timeCode, Integer deficit, Integer contruction) {
		super();
		this.generaterCode = generaterCode;
		this.instrumentCode = instrumentCode;
		this.volume = volume;
		this.supplyDate = supplyDate;
		this.timeCode = timeCode;
		this.deficit = deficit;
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

	public Integer getContruction() {
		return contruction;
	}

	public void setContruction(Integer contruction) {
		this.contruction = contruction;
	}
	
	public GeneratedKey getKey() {
		return new GeneratedKey(this);
	}

	@Override
	public void readExternal(PofReader arg0) throws IOException {
		// TODO Auto-generated method stub
		this.generaterCode = arg0.readInt(0);
		this.instrumentCode = arg0.readInt(1);
		this.volume = arg0.readBigDecimal(2);
		this.supplyDate = arg0.readInt(3);
		this.timeCode = arg0.readInt(4);
		this.deficit = arg0.readInt(5);
		this.contruction = arg0.readInt(6);
	}

	@Override
	public void writeExternal(PofWriter arg0) throws IOException {
		// TODO Auto-generated method stub
		arg0.writeInt(0, this.generaterCode);
		arg0.writeInt(1, this.instrumentCode);
		arg0.writeBigDecimal(2, this.volume);
		arg0.writeInt(3, this.supplyDate);
		arg0.writeInt(4, this.timeCode);
		arg0.writeInt(5, this.deficit);
		arg0.writeInt(6, this.contruction);
	}

	@Override
	public String toString() {
		return "Generated [generaterCode=" + generaterCode + ", instrumentCode=" + instrumentCode + ", volume=" + volume
				+ ", supplyDate=" + supplyDate + ", timeCode=" + timeCode + ", deficit=" + deficit + ", contruction="
				+ contruction + "]";
	}

	
}
