package com.AutomationProject.EndToEndProject;

import java.io.File;

import org.apache.commons.lang3.StringUtils;
import java.io.FileOutputStream;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Base extends EndToEndProject {
	
	
	@Test
	public void ScreenShot() {
		
		ChromeOptions co= new ChromeOptions();
		co.setHeadless(true);
		co.addArguments("--remote-allow-origins=*");
		WebDriver driver = WebDriverManager.chromedriver().capabilities(co).create();
		
		byte[] byteArr = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
		File destfile = new File("C:\\Users\\Administrator\\eclipse-workspace\\EndToEndProject\\src\\test\\java\\com\\AutomationProject\\EndToEndProject\\ScreenShots\\shot1.jpg");

		try {
			FileOutputStream fos = new FileOutputStream(destfile);
			fos.write(byteArr);
		} catch (IOException e) {
			System.out.println("ScreenShot is not Taken");
		}
	}
}
