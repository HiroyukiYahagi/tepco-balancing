package com.oracle.invocation;

import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

import com.tangosol.io.pof.PofReader;
import com.tangosol.io.pof.PofWriter;
import com.tangosol.io.pof.PortableObject;
import com.tangosol.net.AbstractInvocable;

public class SortGeneratedInvocation extends AbstractInvocable implements PortableObject{

	private static final long serialVersionUID = 1L;
	private static final Integer sortDataNumberMax = 100;
	private ThreadLocalRandom rnd = ThreadLocalRandom.current();
	
	private Integer invokedCode;	
	private Integer supplyDate;
	private Integer timeCode;
	private String cacheName;
	
	public SortGeneratedInvocation() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public SortGeneratedInvocation(Integer invokedCode, Integer supplyDate, Integer timeCode, String cacheName) {
		super();
		this.invokedCode = invokedCode;
		this.supplyDate = supplyDate;
		this.timeCode = timeCode;
		this.cacheName = cacheName;
	}

	public Integer getInvokedCode() {
		return invokedCode;
	}

	public void setInvokedCode(Integer invokedCode) {
		this.invokedCode = invokedCode;
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

	public String getCacheName() {
		return cacheName;
	}

	public void setCacheName(String cacheName) {
		this.cacheName = cacheName;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void readExternal(PofReader arg0) throws IOException {
		// TODO Auto-generated method stub
		this.invokedCode = arg0.readInt(0);
		this.supplyDate = arg0.readInt(1);
		this.timeCode = arg0.readInt(2);
		this.cacheName = arg0.readString(3);
	}

	@Override
	public void writeExternal(PofWriter arg0) throws IOException {
		// TODO Auto-generated method stub
		arg0.writeInt(0, this.invokedCode);
		arg0.writeInt(1, this.supplyDate);
		arg0.writeInt(2, this.timeCode);
		arg0.writeString(3, this.cacheName);
	}

}
