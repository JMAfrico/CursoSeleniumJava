package br.com.runner;

import org.junit.runner.RunWith;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.CucumberOptions.SnippetType;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "classpath:features",
		glue = "br.com.steps",
		tags = "@aluguel_filme_categoria_extendida",
		plugin = "pretty",
		monochrome = true,
		snippets = SnippetType.CAMELCASE
		)
public class Runner {

}
