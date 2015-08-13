package com.oracle.invocation;

import com.tangosol.net.InvocationObserver;
import com.tangosol.net.Member;

public class InputGeneratedInvocationObserver implements InvocationObserver {

	public static Integer endCount = 0;
	
	@Override
	public void invocationCompleted() {
		// TODO Auto-generated method stub
		endCount ++;
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
