package com.oracle.test.entryprocessor;

import java.io.IOException;

import com.tangosol.io.pof.PofReader;
import com.tangosol.io.pof.PofWriter;
import com.tangosol.io.pof.PortableObject;
import com.tangosol.util.InvocableMap.Entry;
import com.tangosol.util.processor.AbstractProcessor;

public class ShowDataEntryProcessor extends AbstractProcessor implements PortableObject{

	private static final long serialVersionUID = 1L;

	
	
	public ShowDataEntryProcessor() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public Object process(Entry arg0) {
		// TODO Auto-generated method stub
		
		System.out.println(arg0.getValue());
		
		return null;
	}

	@Override
	public void readExternal(PofReader arg0) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void writeExternal(PofWriter arg0) throws IOException {
		// TODO Auto-generated method stub
		
	}

}
