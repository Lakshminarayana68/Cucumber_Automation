package utilities;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

public class RetryTransformer implements IAnnotationTransformer {
        
	public void transform(ITestAnnotation annotation, Class testClass, Constructor constructor, Method method) {
		
		annotation.setRetryAnalyzer(RetryAnalyzer.class);
	}
}
