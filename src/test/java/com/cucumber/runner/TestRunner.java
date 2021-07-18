package com.cucumber.runner;

import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import org.junit.AfterClass;
import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@RunWith(Cucumber.class)
@CucumberOptions(
        //format={"html","json:target/"},
        monochrome = true,
        features = "src/test/resources/features",
        plugin = {"html:target/cucumber-html-report", "json:target/jsonReports/cucumber-report.json"},
        dryRun = false,
        glue = "stepDefinitions",
        tags = "@orderrequest")
public class TestRunner {
//tags= {"@DeletePlace"}  //compile test verify

    @AfterClass
    public static void generateReports() {


        File reportOutputDirectory = new File("target");
        ArrayList<String> jsonFiles = new ArrayList<String>();
        Configuration configuration = new Configuration(reportOutputDirectory, "Test Project");
        jsonFiles.add("target/jsonReports/cucumber-report.json");
        List<String> classificationFiles = new ArrayList<String>();
        classificationFiles.add("src/test/resources/config/config.properties");
        configuration.addClassificationFiles(classificationFiles);

        ReportBuilder reportBuilder = new ReportBuilder(jsonFiles, configuration);
        reportBuilder.generateReports();

    }


}

