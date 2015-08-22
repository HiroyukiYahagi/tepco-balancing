package com.oracle.invocation;

import com.tangosol.net.InvocationObserver;
import com.tangosol.net.Member;

public class InputPartialSupplysObserver implements InvocationObserver{

	public Integer endCount = 0;
	private final int invokedCount = 10;
	private Object lock;
	
	public InputPartialSupplysObserver(Object lock) {
		super();
		this.lock = lock;
	}

	@Override	
	public void invocationCompleted() {
		// TODO Auto-generated method stub
		endCount ++;
		
		if(endCount == invokedCount){
			synchronized (lock) {
				lock.notify();
			}
		}
	}

	@Override
	public void memberCompleted(Member arg0, Object arg1) {
		// TODO Auto-generated method stub
		System.out.println("member:" + arg0 + " is finished endCount:" + endCount);
	}

	@Override
	public void memberFailed(Member arg0, Throwable arg1) {
		// TODO Auto-generated method stub
		arg1.printStackTrace();
	}

	@Override
	public void memberLeft(Member arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
