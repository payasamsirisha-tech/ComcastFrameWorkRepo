package com.comcarst.crm.listnerutility;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryListnerImp implements IRetryAnalyzer{
int count=0;
int Limitcount=5;

	@Override
	public boolean retry(ITestResult result) {
		if(count<5) {
			count++;
			return true;
		}
		else {
			return false;
		}
	}

}
