package com.serenityAPI.runner;

import org.junit.runner.RunWith;

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

}
