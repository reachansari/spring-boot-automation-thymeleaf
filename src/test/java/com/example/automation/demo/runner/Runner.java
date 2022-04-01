package com.example.automation.demo.runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(strict = true, features = "src/test/resources/features", glue = "com.example.automation", monochrome = true, plugin = {
		"pretty", "html:target/cucumber", "json:target/Cucumber.json", "junit:target/Cucumber.xml" })
public class Runner {

}