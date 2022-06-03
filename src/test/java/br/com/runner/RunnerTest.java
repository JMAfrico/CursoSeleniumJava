package br.com.runner;

import org.junit.runner.RunWith;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.CucumberOptions.SnippetType;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "classpath:features/locadora.feature",
		glue = "br.com.steps",
		tags = "",
		plugin = {"pretty","html:target/report.html","json:target/report.json"},
		monochrome = true,
		snippets = SnippetType.CAMELCASE
		)
public class RunnerTest {

}
