package com.serenityAPI.runner;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.serenityAPI.datadriventests.APISerenityDataDrivenTest;
import com.serenityAPI.testbase.TestBase;
import com.serenityAPI.tests.APISerenityTests;

@RunWith (Suite.class)
@SuiteClasses({
	APISerenityDataDrivenTest.class,
	APISerenityTests.class
})
public class JunitTestRunner  extends TestBase{

}
