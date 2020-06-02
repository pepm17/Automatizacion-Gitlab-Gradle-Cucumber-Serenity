package com.accenture.cucumbergradle.runner;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.junit.runner.RunWith;

import com.accenture.cucumbergradle.other.DataToFeature;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;

@RunWith(RunnerPersonalizado.class)
@CucumberOptions(
	features = "src/test/resources/features",
	tags = "@tag",
	glue = "com.accenture.cucumbergradle.step_definitions",
	snippets = SnippetType.UNDERSCORE,
	monochrome = true,
	dryRun = false
)

public final class Runner {
	@BeforeSuite
	  public static void test() throws InvalidFormatException, IOException {	
		 System.out.println("Holi4\n\n\n");
		 System.out.println("Holi4\n\n\n");
		 System.out.println("Holi4\n\n\n");
	    DataToFeature.overrideFeatureFiles("src/test/resources/features/Gitlab.feature");
	  }
}

