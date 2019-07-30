package tests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class test2 {
	
	@BeforeSuite
	public  void BeforSuite () {
	System.out.println("BeforeSuite");
	}
	@BeforeClass
	public  void BeforClass () {
	System.out.println("BeforClass");
	}
	
	@BeforeMethod
	public  void BeforMethod () {
		System.out.println("BeforeMethod");
		//throw new IllegalStateException("Test ex");
	}
	
	@Test
		public  void Test () {
			System.out.println("Test");
	}
	
//	@AfterMethod
//		public  void AfterMethod () {
//			System.out.println("AfterMethod");	
//	}
			
	@AfterClass
		public  void AfterClass () {
			System.out.println("AfterClass");
	}
			
	@AfterSuite
		public  void AfterSuite () {
			System.out.println("AfterSuite");
		}
		

}
