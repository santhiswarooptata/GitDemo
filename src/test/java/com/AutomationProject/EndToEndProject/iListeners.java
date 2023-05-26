package com.AutomationProject.EndToEndProject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import org.apache.commons.lang3.StringUtils;

import io.github.bonigarcia.wdm.WebDriverManager;

public class iListeners extends Base implements ITestListener  {
public static WebDriver driver;

@AfterMethod
	public void onTestFailure(ITestResult result) {

	ScreenShot();
		}
	}


