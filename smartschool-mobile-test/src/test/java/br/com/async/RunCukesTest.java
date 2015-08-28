package br.com.async;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import net.masterthought.cucumber.ReportBuilder;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import br.com.async.server.Server;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(format = { 
		"pretty",
		"html:build/reports/report.html",
		"json:build/reports/report.json"
		})
public class RunCukesTest {
	
	public final static String REPORT_FOLDER = "build/reports";
	protected static Server server = new Server();
	
	@BeforeClass
	public static void beforeClass() throws InterruptedException, IOException {
		server.startup();
	}
	
	@AfterClass
	public static void afterClass() throws IOException {
		server.shutdown();
		
		List<String> jsonReportFiles = new ArrayList<String>();
		jsonReportFiles.add(REPORT_FOLDER+"/report.json");
		
		File reportOutputDirectory = new File(RunCukesTest.REPORT_FOLDER);
		
		String pluginUrlPath = "";
		String buildNumber = Calendar.getInstance().getTime().toString();
		String buildProject = "cucumber-jvm";
		boolean skippedFails = false;
		boolean pendingFails = false;
		boolean undefinedFails = false;
		boolean missingFails = false;
		boolean flashCharts = false;
		boolean runWithJenkins = false;
		boolean artifactsEnabled = false;
		String artifactConfig = "";
		boolean highCharts = false;
		boolean parallelTesting = false;
		ReportBuilder reportBuilder = new ReportBuilder(
				jsonReportFiles,
				reportOutputDirectory,
				pluginUrlPath, 
				buildNumber, 
				buildProject, skippedFails, pendingFails, undefinedFails, missingFails, flashCharts, runWithJenkins, artifactsEnabled, artifactConfig, highCharts, parallelTesting);
    	reportBuilder.generateReports();
    	Logger.logInfo("Done!");
	}
	
}
