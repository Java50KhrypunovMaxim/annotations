package telran.reflect.processor;

import java.lang.reflect.Method;

import telran.reflect.BeforeEach;
import telran.reflect.Test;

public class TestLibrary {
	public static void launchTest(Object testObj) throws Exception {
		Method [] methods = testObj.getClass().getDeclaredMethods();
		for(Method method: methods) {
			if(method.isAnnotationPresent(Test.class)) {
				method.setAccessible(true);
				method.invoke(testObj);
			}
		}
	}
	public static void launchBeforeEach(Object testBeforeEachObj) throws Exception {
		Method[] methods = testBeforeEachObj.getClass().getDeclaredMethods();
		for (Method method:methods) {
			if (method.isAnnotationPresent(BeforeEach.class))
			{
				method.setAccessible(true);
				method.invoke(testBeforeEachObj);
			}
		}
	}

}


