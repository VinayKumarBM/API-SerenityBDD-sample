package com.serenityAPI.runner;

import java.io.IOException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import com.serenityAPI.utils.StudentApplication;
import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import net.serenitybdd.cucumber.CucumberWithSerenity;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(	
			plugin = {"pretty" ,"html:target/generated-report", "json:target/generated-report/cucumber.json",
                    "junit:target/generated-report/cucumber.xml"},
			features= {"src/test/resources/features"},
			glue = "com.serenityAPI.stepdefinitions",
		//	dryRun = true,		
			monochrome = true,
			snippets = SnippetType.CAMELCASE,
			tags = "@Smoke"
		)
public class FeatureRunner {

	@BeforeClass
	public static void init() throws IOException, InterruptedException {
		StudentApplication application = new StudentApplication();
		application.start();
	}

	@AfterClass
	public static void teardown() throws IOException, InterruptedException {
		StudentApplication application = new StudentApplication();
		application.shutdown();
	}
}
