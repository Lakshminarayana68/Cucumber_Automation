package utilities;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {
	
	int attempt=1;
	int max_attempts=3;
	
	public boolean retry(ITestResult result) {
		
		if(attempt<=max_attempts) {
			System.out.println("Retrying:"+result.getName()+" "+attempt);
			attempt++;
			return true;
		}
		
		return false;
		
	}

}
