package com.AutomationProject.EndToEndProject;

import static org.testng.Assert.assertEquals;
import org.apache.commons.lang3.StringUtils;

import java.awt.Desktop.Action;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.*;
import java.util.concurrent.TimeUnit;

import org.apache.hc.core5.util.Asserts;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.google.inject.spi.Element;
import com.graphbuilder.curve.Point;

import dev.failsafe.internal.util.Assert;
import io.github.bonigarcia.wdm.WebDriverManager;

@Listeners(iListeners.class)
public class EndToEndProject implements ITestListener {

	public static WebDriver driver;

	@BeforeTest
	public void setup() {
		ChromeOptions co = new ChromeOptions();
		co.addArguments("--remote-allow-origins=*");
		driver = new ChromeDriver(co);
		WebDriverManager.chromedriver().setup();

		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test(priority = 0)
	public void link() {
		driver.get("https://testautomationpractice.blogspot.com");
	}

	@Test(priority = 1)
	public void GetTitle() {
		WebElement Title = driver.findElement(By.cssSelector("div[class='titlewrapper']"));
		String act = "Automation Testing Practice";
		String exp = Title.getText();
		System.out.println(Title.getText());

		assertEquals(act, exp);
		System.out.println("-------------------");
	}

	@Test(priority = 2)
	public void DoubleClick() {
		Actions action = new Actions(driver);
		WebElement Field1 = driver.findElement(By.xpath("//input[@id='field1']"));
		action.moveToElement(Field1).doubleClick().perform();
		Field1.sendKeys(Keys.chord(Keys.CONTROL, "c"));

		System.out.println(Field1.getText());

		WebElement Field2 = driver.findElement(By.xpath("//input[@id='field2']"));
		Field2.sendKeys(Keys.chord(Keys.CONTROL, "v"));

		System.out.println(Field2.getText());
	}

	@Test(priority = 3)
	public void TextEntry() {
		driver.findElement(By.cssSelector("[class='wikipedia-search-input']")).sendKeys("Selenium");
		;
	}

	@Test(priority = 4)
	public void Search() {
		WebElement Search = driver.findElement(By.cssSelector("[class='wikipedia-search-button']"));
		Search.click();

	}

	@Test(priority = 5)
	public void SelectResults() {
		WebElement SelectResults = driver.findElement(By.xpath("//div[@id='wikipedia-search-result-link'][1]"));
		SelectResults.click();
	}

	@Test(priority = 6)
	public void Alert() {
		WebElement alert = driver.findElement(By.cssSelector("button[onclick='myFunction()']"));
		alert.click();
		driver.switchTo().alert().dismiss();
		;
		WebElement alertText = driver.findElement(By.cssSelector("p[id='demo']"));
		System.out.println(alertText.getText());
		System.out.println("-------------------");
	}

	@Test(priority = 7)
	public void SelectSpeed() {

		WebElement SelectSpeed = driver.findElement(By.cssSelector("select#speed"));
		Select SpeedDropdown = new Select(SelectSpeed);
		SpeedDropdown.selectByIndex(1);
		List<WebElement> SpeedDropdownOptions = SpeedDropdown.getOptions();
		for (WebElement option : SpeedDropdownOptions) {
			System.out.println(option.getText());
		}
		System.out.println("-------------------");
	}

	@Test(priority = 8)
	public void SelectFile() {
		WebElement SelectFile = driver.findElement(By.id("files"));
		Select FileDropdown = new Select(SelectFile);
		FileDropdown.selectByIndex(1);
		List<WebElement> FileDropdownOptions = FileDropdown.getOptions();
		for (WebElement option : FileDropdownOptions) {
			System.out.println(option.getText());
		}
		System.out.println("-------------------");
	}

	@Test(priority = 9)
	public void DatePicker() throws ParseException {
		String targetDate = "23/may/2023";
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat simpledateformat = new SimpleDateFormat("dd/MMM/yyyy");
		simpledateformat.setLenient(false);
		java.util.Date formattedDates = simpledateformat.parse(targetDate);
		calendar.setTime(formattedDates);
		int targetYear = calendar.get(calendar.YEAR);
		int targetMonth = calendar.get(calendar.MONTH);

		driver.findElement(By.cssSelector("input[class='hasDatepicker']")).click();

		String currentDate = driver.findElement(By.cssSelector("div[class='ui-datepicker-title']")).getText();
		calendar.setTime(new SimpleDateFormat("MMM yyyy").parse(currentDate));
		int currentYear = calendar.get(calendar.YEAR);
		int currentMonth = calendar.get(calendar.MONTH);

		while (currentMonth < targetMonth || currentYear < targetYear) {
			driver.findElement(By.className("ui-datepicker-next")).click();
			currentDate = driver.findElement(By.cssSelector("div[class='ui-datepicker-title']")).getText();
			calendar.setTime(new SimpleDateFormat("MMM yyyy").parse(currentDate));
			currentYear = calendar.get(calendar.YEAR);
			currentMonth = calendar.get(calendar.MONTH);
		}

		while (currentMonth > targetMonth || currentYear > targetYear) {
			driver.findElement(By.className("ui-datepicker-prev")).click();
			currentDate = driver.findElement(By.cssSelector("div[class='ui-datepicker-title']")).getText();
			calendar.setTime(new SimpleDateFormat("MMM yyyy").parse(currentDate));
			currentYear = calendar.get(calendar.YEAR);
			currentMonth = calendar.get(calendar.MONTH);
		}

		if (currentMonth == targetMonth && currentYear == targetYear) {
			int day = 2;
			driver.findElement(By.xpath("//tbody/tr/td/a[text()=" + day + "]"));
		}
	}

	@Test(priority = 10)
	public void DragAndDrop() {
		WebElement DragMe = driver.findElement(By.cssSelector("div[id='draggable']"));
		WebElement DropHere = driver.findElement(By.cssSelector("div[id='droppable']"));

		Actions actions = new Actions(driver);
		actions.dragAndDrop(DragMe, DropHere).perform();
		String result = driver.findElement(By.xpath("//p[text()='Dropped!']")).getText();
		System.out.println(result);
		System.out.println("---------------");

	}

	@Test(priority = 11)
	public void Slider() {
		Actions actions = new Actions(driver);
		WebElement Slider = driver
				.findElement(By.xpath("//span[@class='ui-slider-handle ui-corner-all ui-state-default']"));
		WebElement SliderBar = driver.findElement(By.xpath("//div[@id='slider']"));
		System.out.println(SliderBar.getLocation());
		System.out.println(Slider.getLocation());
		actions.dragAndDropBy(Slider, 200, 0).perform();
		System.out.println(Slider.getLocation());
		System.out.println("---------------");
	}
	
	@Test(priority = 12)
	public void Resizable()
	{
		Actions actions = new Actions(driver);
		WebElement resizableBlock = driver.findElement(By.cssSelector("div[id='resizable']"));
		WebElement resizable = driver.findElement(By
				.cssSelector("div[class='ui-resizable-handle ui-resizable-se ui-icon ui-icon-gripsmall-diagonal-se']"));
		System.out.println(resizable.getLocation());
		System.out.println(resizableBlock.getLocation());
		actions.dragAndDropBy(resizable, 200, 10).perform();
		System.out.println(resizableBlock.getLocation());
		System.out.println("---------------");
	}

	@AfterTest
	public void TearDown() {
//		driver.quit();
	}

}
